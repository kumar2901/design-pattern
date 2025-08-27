package com.kumar.design.pattern.design.pattern.strategy.payment;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid with credit card: " + amount + " with card number: " + cardNumber);
    }
}
