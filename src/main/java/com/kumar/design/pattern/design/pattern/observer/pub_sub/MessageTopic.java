package com.kumar.design.pattern.design.pattern.observer.pub_sub;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject (Topic / Channel)
public class MessageTopic implements Topic {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private final String name;

    public MessageTopic(String name) {
        this.name = name;
    }

    public void subscribe(Subscriber s) {
        subscribers.add(s);
        System.out.println(s + " subscribed to " + name);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
        System.out.println(s + " unsubscribed from " + name);
    }

    public void notifySubscribers(String message) {
        for (Subscriber s : subscribers) {
            s.update(name, message);
        }
    }

    public String getName() {
        return name;
    }
}
