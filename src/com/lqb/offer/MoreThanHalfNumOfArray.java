package com.lqb.offer;

import org.junit.Test;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 利用快速排序返回中位数即可
 *
 * @author:JackBauer
 * @date:2016年6月14日 下午1:40:52
 */
public class MoreThanHalfNumOfArray {

    @Test
    public void test() {
        System.out.println(MoreThanHalfNum_Solution(new int[]{1, 2, 3, 2, 4, 2, 5, 2, 3}));//0
        System.out.println(MoreThanHalfNum_Solution(new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8}));//0
        System.out.println(MoreThanHalfNum_Solution(new int[]{1, 1, 5, 7, 9, 2, 1, 1, 1}));//1
        System.out.println(MoreThanHalfNum_Solution(new int[]{2, 1, 2}));//2
        System.out.println(MoreThanHalfNum_Solution(new int[]{2, 1, 2, 1}));//0
        System.out.println(MoreThanHalfNum_Solution(new int[]{2, 2, 2, 2, 2, 1, 3, 4, 5}));//2
    }

    public int MoreThanHalfNum_Solution(int[] a) {

        if (a == null || a.length == 0) {
            return 0;
        }

        //注意设置start和end一步步缩小范围
        //而不是每次partition(a, 0, i)或partition(a, i, a.length - 1)
        int start = 0;
        int end = a.length - 1;
        int mid = a.length >> 1;
        int i = partition(a, start, end);
        while (i != mid) {
            if (i > mid) {
                end = i - 1;
            } else {
                start = i + 1;
            }
            i = partition(a, start, end);
        }

        return isNumMoreThanHalf(a, a[i]) ? a[i] : 0;
    }

    public int partition(int[] a, int start, int end) {
        int l = start;
        int r = end;
        int num = a[l];
        while (l < r) {
            while (l < r && num < a[r]) {
                r--;
            }
            if (l < r) {
                a[l++] = a[r];
            }

            while (l < r && num > a[l]) {
                l++;
            }
            if (l < r) {
                a[r--] = a[l];
            }
        }

        a[l] = num;
        return l;
    }

    public boolean isNumMoreThanHalf(int[] array, int num) {

        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                times++;
            }
        }

        return times > (array.length / 2);
    }

    public int MoreThanHalfNum_Solution2(int[] a) {
        if (a == null || a.length <= 0) {
            return 0;
        }

        int num = a[0];
        int delta = 1;

        for (int i = 1; i < a.length; i++) {
            if (a[i] == num) {
                delta++;
            } else if (delta == 0) {
                num = a[i];
                delta = 1;
            } else {
                delta--;
            }
        }

        return delta > 0 ? (isNumMoreThanHalf(a, num) ? num : 0) : 0;
    }
}
