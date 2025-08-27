package com.kumar.design.pattern.design.pattern.observer.pub_sub;

/**
 * The Observer pattern is a behavioral design pattern that establishes a one-to-many relationship between objects.
 * When the subject (the object being observed) changes its state,
 * It notifies all observers automatically.
 * This is also called Publish–Subscribe (Pub-Sub).
 * <p>
 * Messaging Systems & Observer Pattern
 * In a messaging system (like Kafka, RabbitMQ, WhatsApp groups, Slack channels):
 * 1. Publisher (Subject): The message producer / topic / channel.
 * 2. Subscribers (Observers): The consumers / users who have subscribed.
 * 3. Notification Mechanism: Whenever a new message is published, all subscribers are notified.
 * <p>
 * This directly maps to the Observer pattern:
 * 1. One publisher → Many subscribers
 * 2. Subscribers can join/leave dynamically
 * 3. Publishers don’t need to know subscribers individually
 */

//Observer
public interface Subscriber {
    void update(String topic, String message);
}
