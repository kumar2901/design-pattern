package com.uber.mvp.demo_uber.design.pattern.strategy;


import java.util.List;


public class Sorter {
    private  SortingStrategy sortingStrategy;

    Sorter(SortingStrategy sortingStrategy){
        this.sortingStrategy=sortingStrategy;
    }

    public void setSortingStrategy(SortingStrategy sortingStrategy){
        this.sortingStrategy=sortingStrategy;
    }
    public void sortNumber(List<Integer> numbers){
        sortingStrategy.sort(numbers);
    }


}
