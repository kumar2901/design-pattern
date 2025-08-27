package com.kumar.design.pattern.design.pattern.strategy;

import java.util.Arrays;
import java.util.List;

import com.kumar.design.pattern.design.pattern.strategy.payment.CreditCardPayment;
import com.kumar.design.pattern.design.pattern.strategy.payment.ShoppingCart;
import com.kumar.design.pattern.design.pattern.strategy.payment.UPIPayment;
import com.kumar.design.pattern.design.pattern.strategy.sorting.BubbleSort;
import com.kumar.design.pattern.design.pattern.strategy.sorting.QuickSort;
import com.kumar.design.pattern.design.pattern.strategy.sorting.MergeSort;
import com.kumar.design.pattern.design.pattern.strategy.sorting.Sorter;

public class StrategyDesignDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 8, 1);
        Sorter sorter=new Sorter(new BubbleSort());
        sorter.sortNumber(numbers);

        sorter.setSortingStrategy(new QuickSort());
        sorter.sortNumber(numbers);

        sorter.setSortingStrategy(new MergeSort());
        sorter.sortNumber(numbers);

        ShoppingCart shoppingCart=new ShoppingCart(new CreditCardPayment("1234567890"));

        shoppingCart.checkout(1200);


        shoppingCart.setPaymentStrategy(new UPIPayment("kr68011@ybl"));

        shoppingCart.checkout(2000);


    }
}
