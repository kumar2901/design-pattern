package com.kumar.design.pattern.design.pattern.observer.minKafka;

public class Producer {

    private final Broker broker;

    public Producer(Broker broker) {
        this.broker = broker;
    }

    public void send(String topic, String key, String value) {
        broker.publish(new Record(topic, key, value));
    }
}
