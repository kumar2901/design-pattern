package com.kumar.design.pattern.design.pattern.observer.minKafka;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    private final String name;
    private final List<PartitionLog> partitions;

    Topic(String name, int numPartitions) {
        this.name = name;
        this.partitions = new ArrayList<>(numPartitions);
        for (int i = 0; i < numPartitions; i++) partitions.add(new PartitionLog());
    }

    int numPartitions() {
        return partitions.size();
    }
}
