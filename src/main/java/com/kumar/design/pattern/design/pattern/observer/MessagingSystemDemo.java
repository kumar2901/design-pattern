package com.kumar.design.pattern.design.pattern.observer;

import com.kumar.design.pattern.design.pattern.observer.pub_sub.MessageTopic;
import com.kumar.design.pattern.design.pattern.observer.pub_sub.User;

public class MessagingSystemDemo {
    public static void main(String[] args) {

        MessageTopic sports = new MessageTopic("Sports");

        MessageTopic tech = new MessageTopic("Tech");

        User u1 = new User("Alice");
        User u2 = new User("Bob");
        User u3 = new User("Charlie");

        sports.subscribe(u1);
        sports.subscribe(u2);

        tech.subscribe(u2);
        tech.subscribe(u3);

        sports.notifySubscribers("India won the cricket match!");
        tech.notifySubscribers("New Java version released!");

    }


}
