package com.kumar.design.pattern.design.pattern.strategy.sorting;

import java.util.List;

public class BubbleSort implements  SortingStrategy{
    @Override
    public void sort(List<Integer> integerList) {
        if (integerList == null || integerList.size() <= 1) {
            return;
        }
        
        int n = integerList.size();
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                if (integerList.get(j) > integerList.get(j + 1)) {
                    // Swap elements
                    int temp = integerList.get(j);
                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temp);
                    swapped = true;
                }
            }
            
            // If no swapping occurred in this pass, array is already sorted
            if (!swapped) {
                break;
            }
        }
        
        System.out.println("Bubble Sort completed");
    }
}
