package com.kumar.design.pattern.design.pattern.observer.minKafka;

import lombok.Data;

import java.util.Objects;

@Data
public class TopicPartition {

    private final String topic;
    private final int partition;

    public TopicPartition(String topic, int partition) {
        this.topic = topic;
        this.partition = partition;
    }

    @Override
    public String toString() {
        return topic + ":" + partition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicPartition topicPartition)) return false;
        return partition == topicPartition.partition && Objects.equals(topic, topicPartition.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, partition);
    }
}
