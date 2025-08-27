package com.uber.mvp.demo_uber.design.pattern.strategy;

import java.util.List;

public class InsertionSort implements  SortingStrategy{
    @Override
    public void sort(List<Integer> integerList) {
        if (integerList == null || integerList.size() <= 1) {
            return;
        }
        
        for (int i = 1; i < integerList.size(); i++) {
            int key = integerList.get(i);
            int j = i - 1;
            
            // Move elements of integerList[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && integerList.get(j) > key) {
                integerList.set(j + 1, integerList.get(j));
                j = j - 1;
            }
            integerList.set(j + 1, key);
        }
        
        System.out.println("Insertion Sort completed");
    }
}
