package com.kumar.design.pattern.design.pattern.strategy.payment;

public class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid with PayPal: " + amount + " with email: " + email);
    }
}
