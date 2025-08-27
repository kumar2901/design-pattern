package com.kumar.design.pattern.design.pattern.observer;

import com.kumar.design.pattern.design.pattern.observer.minKafka.Broker;
import com.kumar.design.pattern.design.pattern.observer.minKafka.Consumer;
import com.kumar.design.pattern.design.pattern.observer.minKafka.Producer;

import java.util.List;

public class MiniKafkaDemo {
    public static void main(String[] args) throws Exception {

        Broker broker = new Broker();
        broker.createTopic("orders", 3);


        Producer producer = new Producer(broker);


        Consumer c1 = new Consumer(broker, "cg-orders").subscribe(List.of("orders"));
        Consumer c2 = new Consumer(broker, "cg-orders").subscribe(List.of("orders"));


        c1.startPolling((tp, r) -> System.out.printf("[C1] %s <- %s%n", tp, r.getValue()));
        c2.startPolling((tp, r) -> System.out.printf("[C2] %s <- %s%n", tp, r.getValue()));


// Produce a burst of messages with keys to spread across partitions
        for (int i = 0; i < 20; i++) {
            String key = "user-" + (i % 5); // same keys go to same partition
            producer.send("orders", key, "order#" + i + " for " + key);
        }


// Demonstrate scaling: add a 3rd consumer later → triggers rebalance
        Thread.sleep(1000);
        Consumer c3 = new Consumer(broker, "cg-orders").subscribe(List.of("orders"));
        c3.startPolling((tp, r) -> System.out.printf("[C3] %s <- %s%n", tp, r.getValue()));


// More traffic
        for (int i = 20; i < 35; i++) {
            String key = "user-" + (i % 5);
            producer.send("orders", key, "order#" + i + " for " + key);
        }


// Let it run briefly
        Thread.sleep(2000);


// Graceful shutdown
        c1.close();
        c2.close();
        c3.close();
        System.out.println("\nDemo complete.");

    }


}
