package com.kumar.design.pattern.design.pattern.strategy.sorting;


import java.util.List;


public class Sorter {
    private  SortingStrategy sortingStrategy;

    public Sorter(SortingStrategy sortingStrategy){
        this.sortingStrategy=sortingStrategy;
    }

    public void setSortingStrategy(SortingStrategy sortingStrategy){
        this.sortingStrategy=sortingStrategy;
    }
    public void sortNumber(List<Integer> numbers){
        sortingStrategy.sort(numbers);
    }


}
