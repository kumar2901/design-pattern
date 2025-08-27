package com.kumar.design.pattern.design.pattern.strategy.payment;

public class DebitCardPayment implements PaymentStrategy {

    private final String cardNumber;

    public DebitCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid with debit card: " + amount + " with card number: " + cardNumber);
    }

}
