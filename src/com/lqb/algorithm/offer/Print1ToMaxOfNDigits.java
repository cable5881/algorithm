package com.lqb.algorithm.offer;

public class Print1ToMaxOfNDigits {


    void print1ToMaxOfNDigits(int n) {
        if (n < 1) {
            return;
        }

        int[] a = new int[n];

        print1ToMaxOfNDigitsCore(a, 0);
    }

    private void print1ToMaxOfNDigitsCore(int[] a, int start) {
        if(start >= a.length) {
            return;
        }

        for (int i = start; i < a.length; i++) {
            print1ToMaxOfNDigitsCore(a, start + 1);
        }
    }
}
