package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= x^n <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PowXN {

    @Test
    public void test() {
        System.out.println(myPow(2, 2));
        System.out.println(myPow2(2, 2));
        System.out.println(myPow3(2, 2));
        System.out.println(myPow3_best_practice(2, 2));
        System.out.println(myPow4(2, 2));
        System.out.println("=================");
        System.out.println(myPow(2, -2));
        System.out.println(myPow2(2, -2));
        System.out.println(myPow3(2, -2));
        System.out.println(myPow3_best_practice(2, -2));
        System.out.println(myPow4(2, -2));
        System.out.println("=================");
        System.out.println(myPow(1 / 2.0, -2));
        System.out.println(myPow2(1 / 2.0, -2));
        System.out.println(myPow3(1 / 2.0, -2));
        System.out.println(myPow3_best_practice(1 / 2.0, -2));
        System.out.println(myPow4(1 / 2.0, -2));
    }

    /**
     * 迭代暴力法
     */
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //减少计算
        if (x == 1) {
            return 1;
        }
        double res = 1;
        double y = x;
        if (n < 0) {
            y = 1 / x;
        }
        //由于n可能小于0，因为这里n要取绝对值
        for (int i = 0; i < Math.abs(n); i++) {
            res *= y;
        }
        return res;
    }

    /**
     * 递归暴力法
     */
    public double myPow2(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //减少计算
        if (x == 1) {
            return 1;
        }
        double y = x;
        if (n < 0) {
            y = 1 / x;
        }
        return myPow2Core(y, Math.abs(n));
    }

    private double myPow2Core(double x, int n) {
        if (n < 1) {
            return 1;
        }
        return x * myPow2Core(x, n - 1);
    }

    /**
     * 递归分治法
     */
    public double myPow3(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //减少计算
        if (x == 1) {
            return 1;
        }

        return n < 0 ? 1 / myPow3Core(x, n) : myPow3Core(x, n);
    }

    private double myPow3Core(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = myPow3Core(x, n / 2);
        return n % 2 == 0 ? (res * res) : (x * res * res);
    }

    /**
     * 递归分治法的简洁代码
     */
    public double myPow3_best_practice(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //减少计算
        if (x == 1) {
            return 1;
        }

        if (n < 0) {
            return 1 / myPow3_best_practice(x, -n);
        }

        //x^n = (x^2)^(n/2)
        if (n % 2 == 0) {
            return myPow3_best_practice(x * x, n / 2);
        } else {
            return x * myPow3_best_practice(x, n - 1);
        }
    }

    /**
     * 迭代分治法
     */
    public double myPow4(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //减少计算
        if (x == 1) {
            return 1;
        }

        int exponent = Math.abs(n);
        double res = x;
        while (exponent > 1) {
            res *= res;
            exponent /= 2;
        }
        if (n % 2 != 0) {
            res *= x;
        }
        return n > 0 ? res : 1 / res;
    }
}
