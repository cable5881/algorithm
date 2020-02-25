package com.lqb.offer.mark;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。 例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * @author:JackBauer
 * @date:2016年6月17日 下午9:41:50
 */
public class UglyNumber {

    @Test
    public void test() {
        // System.out.println(GetUglyNumber_Solution(0));
        // System.out.println(GetUglyNumber_Solution(1));
        // System.out.println(GetUglyNumber_Solution(2));
        // System.out.println(GetUglyNumber_Solution(3));
        // System.out.println(GetUglyNumber_Solution(4));
        // System.out.println(GetUglyNumber_Solution(5));
        // System.out.println(GetUglyNumber_Solution(6));
        // System.out.println(GetUglyNumber_Solution(7));
        // System.out.println(GetUglyNumber_Solution(8));
        // System.out.println(GetUglyNumber_Solution(9));
        // System.out.println(GetUglyNumber_Solution(10));
        // System.out.println(GetUglyNumber_Solution(11));
        // System.out.println(GetUglyNumber_Solution(12));
        System.out.println(GetUglyNumber_Solution(1500));
        // System.out.println(GetUglyNumber_Solution2(1500));
    }

    public int GetUglyNumber_Solution(int index) {

        if (index < 1) {
            return 0;
        } else if (index == 1) {
            return 1;
        }

        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;

        int multiplyStart_1 = 0;
        int multiplyStart_2 = 0;
        int multiplyStart_3 = 0;

        int numOfUglyNumber = 1;
        int lastSmallest = uglyNumbers[0];

        while (numOfUglyNumber < index) {
            int smallest = smallestOfThreeNum(uglyNumbers[multiplyStart_1] * 2,
                    uglyNumbers[multiplyStart_2] * 3,
                    uglyNumbers[multiplyStart_3] * 5);

            uglyNumbers[numOfUglyNumber] = smallest;
            numOfUglyNumber++;
            lastSmallest = smallest;

            while (uglyNumbers[multiplyStart_1] * 2 <= smallest) {
                multiplyStart_1++;
            }
            while (uglyNumbers[multiplyStart_2] * 3 <= smallest) {
                multiplyStart_2++;
            }
            while (uglyNumbers[multiplyStart_3] * 5 <= smallest) {
                multiplyStart_3++;
            }
        }

        return lastSmallest;
    }

    public int smallestOfThreeNum(int num1, int num2, int num3) {
        int min = num1 < num2 ? num1 : num2;
        return min < num3 ? min : num3;
    }

    /**
     * @date 2020/2/25 16:23
     * 难点在于想到用一个数组保存已经算出来的丑数，然后3个指针向右移动
     */
    public int GetUglyNumber_Solution2(int n) {
        ArrayList<Integer> res = new ArrayList<>(n);

        if (n <= 0) {
            return 0;
        }

        res.add(1);

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        int i = 1;

        while (i < n) {
            final int num1 = res.get(p1) * 2;
            final int num2 = res.get(p2) * 3;
            final int num3 = res.get(p3) * 5;

            int big = smallestOfThreeNum(num1, num2, num3);
            //因为有可能计算重复导致小于当前最大的丑数，因此要判断；
            // 否则不能添加进数组，且不能算一次有效的计算，即i不能累加
            if (res.get(res.size() - 1) < big) {
                res.add(big);
                i++;
            }

            if (big == num1) {
                p1++;
            } else if (big == num2) {
                p2++;
            } else {
                p3++;
            }
        }

        return res.get(res.size() - 1);
    }

}
