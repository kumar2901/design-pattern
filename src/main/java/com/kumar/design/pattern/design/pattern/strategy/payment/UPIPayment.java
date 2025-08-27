package com.kumar.design.pattern.design.pattern.strategy.payment;

import lombok.Getter;

@Getter
public class UPIPayment implements PaymentStrategy{

    private String upiId;
     public UPIPayment(String upiId){
         this.upiId=upiId;
     }

    @Override
    public void pay(int amount) {
        System.out.println("Paid with UPI: " + amount+" upiID "+upiId);
    }
}
