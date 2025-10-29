package com.kumar.coding.recursion;

public class RecursionBasics {

    public int factorial(int n) {

        if (n == 1 || n == 2) {
            return n;
        }

        return n * factorial(n - 1);
    }

    public int sumOfDigits(int n) {
        if (n == 0 || n % 10 == n) {
            return n;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    public int reverseNumber(int num, int sum) {
        if (num < 1) {
            return sum;
        }
        int r = num % 10;
        return reverseNumber(num / 10, sum * 10 + r);

    }

    public boolean isPalindrome(String str, int l, int r) {
        if (l >= r) {
            return true;
        }
        if (str.charAt(l) != str.charAt(r)) {
            return false;
        }

        return isPalindrome(str, l + 1, r - 1);


    }

    void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(sumOfDigits(1234));
        System.out.println(reverseNumber(1024, 0));
        System.out.println(isPalindrome("madam", 0, 4));
        System.out.println(isPalindrome("hello", 0, 4));
    }
}
