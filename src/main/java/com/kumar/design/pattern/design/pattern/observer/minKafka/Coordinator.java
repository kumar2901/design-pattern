package com.kumar.design.pattern.design.pattern.observer.minKafka;

import java.util.Collection;
import java.util.Map;

public interface Coordinator {

    void join(String memberId, Collection<TopicPartition> subscribable);

    void leave(String memberId, Collection<TopicPartition> subscribable);

    void commitSync(String memberId, TopicPartition tp, long offset);

    long committedOffset(TopicPartition tp);

    Map<TopicPartition, String> currentAssignment();
}
