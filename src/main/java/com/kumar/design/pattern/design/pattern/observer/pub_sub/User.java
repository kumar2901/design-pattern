package com.kumar.design.pattern.design.pattern.observer.pub_sub;

public class User implements Subscriber {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update(String topic, String message) {
        System.out.println(username + " received on " + topic + ": " + message);
    }

    public String toString() {
        return username;
    }
}
