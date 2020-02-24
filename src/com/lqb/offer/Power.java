package com.lqb.offer;

public class Power {

    public static void main(String[] args) {
        System.out.println(power2(-2, -2));
        System.out.println(power2(0.00000000001, -2));
        System.out.println(power2(4, 0));
        System.out.println(power2(2, 2));
        System.out.println(power2(2, 1));
        System.out.println(power2(2, -1));
        System.out.println(power2(2, -2));
    }

    public static double power(double base, int exponent) {

        double num = 1;
        boolean isNegative = exponent < 0 ? true : false;

        if (base - 0.0 > -0.000001 && base - 0.0 < 0.000001) {
            if (exponent == 0) {
                return 1;
            } else {
                return 0;
            }

        }

        while (exponent > 0) {
            num = num * base;
            exponent--;
        }

        while (exponent < 0) {
            num = num * base;
            exponent++;
        }

        if (isNegative) {
            num = 1 / num;
        }

        return num;
    }

    /**
     * 一样的解法，只是很久没写了，试试看
     * @param base
     * @param exponent
     * @return
     */
    public static double power2(double base, int exponent) {
        if (base == 0 || base == 1) {
            return base;
        }

        if (exponent == 0) {
            return 1;
        }

        if (exponent < 0) {
            exponent *= -1;
            base = 1 / base;
        }

        double res = 1;
        while (exponent-- > 0) {
            res *= base;
        }

        return res;
    }
}
