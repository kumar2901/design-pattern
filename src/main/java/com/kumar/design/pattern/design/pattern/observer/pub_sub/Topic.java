package com.kumar.design.pattern.design.pattern.observer.pub_sub;

public interface Topic {
    void subscribe(Subscriber s);

    void unsubscribe(Subscriber s);

    void notifySubscribers(String message);

    String getName();
}
