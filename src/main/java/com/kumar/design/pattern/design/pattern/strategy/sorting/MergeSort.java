package com.kumar.design.pattern.design.pattern.strategy.sorting;

import java.util.List;

public class MergeSort implements  SortingStrategy{
    @Override
    public void sort(List<Integer> integerList) {
        if (integerList == null || integerList.size() <= 1) {
            return;
        }
        
        mergeSort(integerList, 0, integerList.size() - 1);
        System.out.println("Merge Sort completed");
    }
    
    private void mergeSort(List<Integer> list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }
    
    private void merge(List<Integer> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = list.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = list.get(mid + 1 + j);
        }
        
        // Merge the temporary arrays back into the original list
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                list.set(k, leftArray[i]);
                i++;
            } else {
                list.set(k, rightArray[j]);
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray if any
        while (i < n1) {
            list.set(k, leftArray[i]);
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray if any
        while (j < n2) {
            list.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}
