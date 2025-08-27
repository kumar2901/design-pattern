package com.kumar.design.pattern.design.pattern.observer.minKafka;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {

    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final Map<String, GroupCoordinator> groups = new ConcurrentHashMap<>();

    private static int partitionFor(String key, int numPartitions) {
        int h = (key == null) ? 0 : Math.abs(key.hashCode());
        return h % numPartitions;
    }

    public synchronized void createTopic(String name, int partitions) {
        if (topics.containsKey(name)) throw new IllegalStateException("Topic exists: " + name);
        topics.put(name, new Topic(name, partitions));
        System.out.printf("[Broker] Created topic '%s' with %d partitions%n", name, partitions);
    }

    public void publish(Record r) {
        Topic t = topics.get(r.getTopic());
        if (t == null) throw new IllegalArgumentException("No such topic: " + r.getTopic());
        int p = partitionFor(r.getKey(), t.numPartitions());
        long offset = t.getPartitions().get(p).append(r);
        System.out.printf("[Broker] APPEND %s p=%d off=%d key=%s value=%s%n", r.getTopic(), p, offset, r.getKey(), r.getValue());
    }

    public List<Record> poll(String groupId, String memberId, TopicPartition tp, int max) {
        Topic t = topics.get(tp.getTopic());
        if (t == null) throw new IllegalArgumentException("No such topic: " + tp.getTopic());
        if (tp.getPartition() < 0 || tp.getPartition() >= t.numPartitions())
            throw new IllegalArgumentException("Bad partition");


        GroupCoordinator gc = groups.computeIfAbsent(groupId, GroupCoordinator::new);
        Map<TopicPartition, String> asn = gc.currentAssignment();
        String owner = asn.get(tp);
        if (owner == null || !owner.equals(memberId)) return Collections.emptyList();
        long from = gc.committedOffset(tp) + 1; // next offset after committed
        return t.getPartitions().get(tp.getPartition()).readFrom(from, max);
    }

    public void commit(String groupId, String memberId, TopicPartition tp, long lastConsumedOffset) {
        GroupCoordinator gc = groups.computeIfAbsent(groupId, GroupCoordinator::new);
        gc.commitSync(memberId, tp, lastConsumedOffset);
    }

    public void joinGroup(String groupId, String memberId, Set<String> topicsToSubscribe) {
        GroupCoordinator gc = groups.computeIfAbsent(groupId, GroupCoordinator::new);
        Collection<TopicPartition> allTps = new ArrayList<>();
        for (String t : topicsToSubscribe) {
            Topic topic = topics.get(t);
            if (topic == null) throw new IllegalArgumentException("No such topic: " + t);
            for (int p = 0; p < topic.numPartitions(); p++) allTps.add(new TopicPartition(t, p));
        }
        gc.join(memberId, allTps);
    }

    public void leaveGroup(String groupId, String memberId, Set<String> topicsToSubscribe) {
        GroupCoordinator gc = groups.get(groupId);
        if (gc == null) return;
        Collection<TopicPartition> allTps = new ArrayList<>();
        for (String t : topicsToSubscribe) {
            Topic topic = topics.get(t);
            if (topic == null) continue;
            for (int p = 0; p < topic.numPartitions(); p++) allTps.add(new TopicPartition(t, p));
        }
        gc.leave(memberId, allTps);
    }

    public long endOffset(TopicPartition tp) {
        Topic t = topics.get(tp.getTopic());
        return t.getPartitions().get(tp.getPartition()).endOffset();
    }

}
