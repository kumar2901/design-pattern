package com.uber.mvp.demo_uber.design.pattern.strategy;

import java.util.Arrays;
import java.util.List;

public class StrategyDesignDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 8, 1);
        Sorter sorter=new Sorter(new BubbleSort());
        sorter.sortNumber(numbers);

        sorter.setSortingStrategy(new QuickSort());
        sorter.sortNumber(numbers);

        sorter.setSortingStrategy(new MergeSort());
        sorter.sortNumber(numbers);
    }
}
