package com.kumar.design.pattern.design.pattern.observer.minKafka;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class GroupCoordinator implements Coordinator {

    private final String groupId;
    // current members
    private final Set<String> members = new LinkedHashSet<>();
    // assignment: TP -> memberId
    private final Map<TopicPartition, String> assignment = new HashMap<>();
    // offsets: TP -> offset
    private final Map<TopicPartition, AtomicLong> committed = new ConcurrentHashMap<>();


    public GroupCoordinator(String groupId) {
        this.groupId = groupId;
    }


    @Override
    public synchronized void join(String memberId, Collection<TopicPartition> subscribable) {
        members.add(memberId);
        rebalance(subscribable);
        System.out.printf("[Group %s] member JOIN: %s%n", groupId, memberId);
    }

    @Override
    public synchronized void leave(String memberId, Collection<TopicPartition> subscribable) {
        members.remove(memberId);
        assignment.values().removeIf(m -> m.equals(memberId));
        rebalance(subscribable);
        System.out.printf("[Group %s] member LEAVE: %s%n", groupId, memberId);
    }


    @Override
    public synchronized void commitSync(String memberId, TopicPartition tp, long offset) {
        committed.computeIfAbsent(tp, k -> new AtomicLong(-1)).set(offset);
    }

    @Override
    public long committedOffset(TopicPartition tp) {
        return committed.getOrDefault(tp, new AtomicLong(-1)).get();
    }


    @Override
    public synchronized Map<TopicPartition, String> currentAssignment() {
        return new HashMap<>(assignment);
    }


    private void rebalance(Collection<TopicPartition> tps) {
        assignment.clear();
        if (members.isEmpty()) return;
        List<String> mem = new ArrayList<>(members);
        int i = 0;
        for (TopicPartition tp : tps) {
            String m = mem.get(i % mem.size());
            assignment.put(tp, m);
            i++;
        }
        System.out.printf("[Group %s] Rebalanced. Assignment: %s%n", groupId, assignment);
    }
}
