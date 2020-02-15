package com.lqb.offer;

public class NumberOf1 {

    public static void main(String[] args) {
        System.out.println(numberOf1(-1));

    }

    public static int numberOf1(int n) {

        int count = 0;

        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }

        return count;
    }

}
