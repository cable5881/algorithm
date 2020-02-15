package com.lqb.offer;

import org.junit.Test;

/**
 * 输入数字n，按顺序打印出从1最大的n位十进制数。
 * 比如输入3，则打印出1、2、3一直到最大的3位数即999
 */
public class Print1ToMaxOfNDigits {

    @Test
    public void test() {
        print1ToMaxOfNDigits(3);
    }

    public void print1ToMaxOfNDigits(int n) {
        if (n < 1) {
            return;
        }

        char[] num = new char[n];
        for (int i = 0; i < num.length; i++) {
            num[i] = '0';
        }

        int bitIndex = n - 1;
        //记录当前数字的最高位，因为输出数组要从最高位开始，而最高位是变化的
        int maxBitIndex = n - 1;
        while (bitIndex >= 0) {
            bitIndex = add(num);
            if (bitIndex >= 0) {
                //如果返回的最高位比此前记录的要大，则更新
                if (bitIndex < maxBitIndex) {
                    maxBitIndex = bitIndex;
                }
                for (int i = maxBitIndex; i < num.length; i++) {
                    System.out.print(num[i]);
                }
                System.out.println();
            }
        }
    }

    /**
     * 给num数组加一，返回加完后的最高位
     * 如果超出了数组长度则返回-1，说明已经到达了最大溢出了
     */
    private int add(char[] num) {
        for (int i = num.length - 1; i >= 0; i--) {
            if (num[i] + 1 <= '9') {
                num[i] += 1;
                return i;
            } else {
                num[i] = '0';
            }
        }

        return -1;
    }
}
