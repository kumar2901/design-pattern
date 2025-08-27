package com.kumar.design.pattern.design.pattern.observer.minKafka;


import lombok.Data;

@Data
public class Record {

    private final String topic;
    private final String key;
    private final String value;
    private final long timestamp;

    public Record(String topic, String key, String value) {
        this.topic = topic;
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }


    @Override
    public String toString() {
        return "Record{" +
                "topic='" + topic + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", ts=" + timestamp +
                '}';
    }
}
