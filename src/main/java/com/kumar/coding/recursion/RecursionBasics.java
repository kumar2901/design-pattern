package com.kumar.coding.recursion;

public class RecursionBasics {

    public int factorial(int n) {

        if (n == 1 || n == 2) {
            return n;
        }

        return n * factorial(n - 1);
    }

    public int sumOfDigits(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    void main(String[] args) {

        System.out.println(factorial(5));
        System.out.println(sumOfDigits(1234));
    }
}
