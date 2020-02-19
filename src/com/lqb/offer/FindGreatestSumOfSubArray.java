package com.lqb.offer;

import org.junit.Test;

/**
 * {6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)
 *
 * @author:JackBauer
 * @date:2016年6月15日 下午4:25:32
 */
public class FindGreatestSumOfSubArray {

    @Test
    public void test() {
        //8
        System.out.println(findGreatestSumOfSubArray2(new int[]{6, -3, -2, 7, -15, 1, 2, 2}));

        //18
        System.out.println(findGreatestSumOfSubArray2(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));

        //-1
        System.out.println(findGreatestSumOfSubArray2(new int[]{-2, -8, -1, -5, -9}));

        //25
        System.out.println(findGreatestSumOfSubArray2(new int[]{2, 8, 1, 5, 9}));

        System.out.println(findGreatestSumOfSubArray2(null));
        System.out.println(findGreatestSumOfSubArray2(new int[]{}));
        System.out.println(findGreatestSumOfSubArray2(new int[]{1}));
    }

    public int findGreatestSumOfSubArray(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int oldSum = array[0];// 注意初值赋首元素，若赋0无法通过测试用例-2, -8, -1, -5, -9
        int nowSum = array[0];

        //注意从1开始，否则array[0]会被重复添加
        for (int i = 1; i < array.length; i++) {

            if (nowSum <= 0) {
                nowSum = array[i];
            } else {
                nowSum += array[i];
            }

            if (nowSum > oldSum) {
                oldSum = nowSum;
            }

        }

        return oldSum;
    }

    /**
     * 多年后的尝试，20200216
     */
    public int findGreatestSumOfSubArray2(int[] a) {
        if (a == null || a.length < 1) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE;
        int tmpSum = 0;

        for (int i = 0; i < a.length; i++) {
            if (tmpSum + a[i] > 0) {
                tmpSum += a[i];
                maxSum = Math.max(tmpSum, maxSum);
            } else {
                maxSum = Math.max(a[i], maxSum);
                if (a[i] > 0) {
                    tmpSum = a[i];
                } else {
                    tmpSum = 0;
                }
            }

        }

        return maxSum;
    }

}
