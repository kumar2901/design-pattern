package com.kumar.design.pattern.design.pattern.observer.minKafka;

import java.util.List;

public interface Partition {
    long append(Record r);

    List<Record> readFrom(long from, int max);

    long endOffset();
}
