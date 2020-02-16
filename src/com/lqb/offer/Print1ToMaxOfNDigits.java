package com.lqb.offer;

import org.junit.Test;

/**
 * 输入数字n，按顺序打印出从1最大的n位十进制数。
 * 比如输入3，则打印出1、2、3一直到最大的3位数即999
 */
public class Print1ToMaxOfNDigits {

    @Test
    public void test() {
        print1ToMaxOfNDigits2(2);
    }

    /**
     * 解法一：老老实实循环给字符数组的低位加1，然后打印
     * @param n
     */
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

    /**
     * 解法二：n位所有十进制数其实就是n个从0到9的全排列
     */
    public void print1ToMaxOfNDigits2(int n) {
        if (n < 1) {
            return;
        }

        char[] num = new char[n];
        for (int i = 0; i < num.length; i++) {
            num[i] = '0';
        }

        print1ToMaxOfNDigits2(num, 0);
    }

    public void print1ToMaxOfNDigits2(char[] num, int start) {
        if (start >= num.length) {
            print(num);
            return;
        }

        //注意不是i < num.length，这里是每一位都要循环10次加1的。
        for (int i = 1; i <= 10; i++) {
            print1ToMaxOfNDigits2(num, start + 1);
            if (num[start] + 1 <= '9') {
                num[start] += 1;
            } else {
                num[start] = '0';
            }
        }
    }

    private void print(char[] num) {
        int i = 0;
        while (i < num.length) {
            if (num[i] != '0') {
                break;
            }
            i++;
        }

        while (i < num.length) {
            System.out.print(num[i++]);
            if (i == num.length) {
                System.out.println();
            }
        }

    }
}
