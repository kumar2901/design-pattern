package com.kumar.design.pattern.design.pattern.observer.minKafka;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PartitionLog implements Partition {

    private final List<Record> log = new ArrayList<>();

    @Override
    public synchronized long append(Record r) {
        long offset = log.size();
        log.add(r);
        return offset;
    }

    @Override
    public synchronized List<Record> readFrom(long from, int max) {
        int start = (int) Math.min(Math.max(0, from), log.size());
        int end = Math.min(log.size(), start + max);
        return new ArrayList<>(log.subList(start, end));
    }

    @Override
    public synchronized long endOffset() {
        return log.size();
    }
}
