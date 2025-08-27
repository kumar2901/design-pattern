package com.kumar.design.pattern.design.pattern.observer.minKafka;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements AutoCloseable {
    private final Broker broker;
    private final String groupId;
    private final String memberId = UUID.randomUUID().toString().substring(0, 8);
    private final Set<String> topics = new LinkedHashSet<>();
    private final ExecutorService loop = Executors.newSingleThreadExecutor();
    private volatile boolean running = false;

    public Consumer(Broker broker, String groupId) {
        this.broker = broker;
        this.groupId = groupId;
    }

    public Consumer subscribe(Collection<String> topics) {
        this.topics.addAll(topics);
        broker.joinGroup(groupId, memberId, this.topics);
        return this;
    }

    public void startPolling(java.util.function.BiConsumer<TopicPartition, Record> handler) {
        if (running) return;
        running = true;
        loop.submit(() -> {
            Map<TopicPartition, Long> lastOffsets = new HashMap<>();
            while (running) {
                try {
// For each subscribed topic/partition, try to poll a few records
                    for (String t : topics) {
// discover TP count via endOffset probing
// (in real Kafka, assignment is known; here we probe 0..N until empty)
                    }
// We can obtain current assignment by scanning partitions until no more.
// Simpler: assume max 32 partitions per topic for demo.
                    for (String t : topics) {
                        for (int p = 0; p < 32; p++) {
                            TopicPartition tp = new TopicPartition(t, p);
                            try {
                                List<Record> batch = broker.poll(groupId, memberId, tp, 10);
                                if (batch.isEmpty()) continue;
                                for (Record r : batch) {
                                    handler.accept(tp, r);
                                    long off = lastOffsets.getOrDefault(tp, -1L) + 1;
// infer offset by index order; we can update off using endOffset - remaining. For simplicity, we track sequentially.
                                    off = off + 1; // next offset consumed
                                    lastOffsets.put(tp, off);
                                }
// Commit last known offset for TP
                                long committed = lastOffsets.getOrDefault(tp, -1L);
                                if (committed >= 0) broker.commit(groupId, memberId, tp, committed);
                            } catch (IllegalArgumentException ignored) { /* partition out of range for topic */ }
                        }
                    }
                    Thread.sleep(100); // poll interval
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    System.err.println("[Consumer " + memberId + "] error: " + e);
                }
            }
        });
    }

    @Override
    public void close() throws Exception {
        running = false;
        loop.shutdownNow();
        unsubscribe();
    }

    public void unsubscribe() {
        broker.leaveGroup(groupId, memberId, topics);
    }
}
