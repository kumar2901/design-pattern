package com.kumar.design.pattern.design.pattern.strategy.sorting;

import java.util.List;

public class QuickSort implements SortingStrategy {
    @Override
    public void sort(List<Integer> integerList) {
        if (integerList == null || integerList.size() <= 1) {
            return;
        }

        quickSort(integerList, 0, integerList.size() - 1);
        System.out.println("Quick Sort completed");
    }

    private void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
