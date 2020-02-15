package com.lqb.algorithm;

import org.junit.Test;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class QuickSort {

    @Test
    public void test() {
        int[] a = {3, 5, 8, 1, 2, 9, 4, 5, 7, 6};
        qSort2(a);
        System.out.println(Arrays.toString(a));

        int[] b = new int[]{3, 5, 8, 1, 2, 9, 4, 5, 7, 3};
        qSort2(b);
        System.out.println(Arrays.toString(b));

        int[] c = new int[]{3, 3, 1, 3, 3};
        qSort2(c);
        System.out.println(Arrays.toString(c));
    }

    public void qSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        qSort(a, 0, a.length - 1);
    }

    private void qSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int num = a[start];
        int l = start;
        int r = end;

        while (l < r) {
            //先从右边开始遍历，找到一个比num小的放到左边来
            //⭐⭐⭐⭐⭐⭐  注意l不能等于r，如[2,1,3]最终会变成[1,1,3]  ⭐⭐⭐⭐⭐⭐⭐⭐
            //a[r] = num 或者下面 a[l] = num 的判断是有必要的，如果都不包含，那么当遇到相同元素会死循环
            while (l < r && a[r] > num) {
                r--;
            }
            //满足条件说明a[r] < num
            //a[r] == num也会被换到左边来
            if (l < r) {
                a[l++] = a[r];
            }

            //接着开始从左边开始遍历，找到一个比num大的放到右边去
            while (l < r && a[l] < num) {
                l++;
            }
            if (l < r) {
                a[r--] = a[l];
            }
        }

        a[l] = num;

        //此时num已经到了正确的位置，即num的左边是小于num的，右边是大于num的，所以不用考虑l或者r的位置了
        //因此下一次排序的范围不要包括l或者r
        qSort(a, start, l - 1);
        qSort(a, l + 1, end);
    }

    public void qSort2(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        qSort2(a, 0, a.length - 1);
    }

    /**
     * 采用另外一种递归方式
     */
    private void qSort2(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = partition(a, start, end);
        qSort2(a, start, i - 1);
        qSort2(a, i + 1, end);
    }

    private int partition(int[] a, int start, int end) {
        int num = a[start];
        int l = start;
        int r = end;

        while (l < r) {
            while (l < r && a[r] > num) {
                r--;
            }
            if (l < r) {
                a[l++] = a[r];
            }

            while (l < r && a[l] < num) {
                l++;
            }
            if (l < r) {
                a[r--] = a[l];
            }
        }

        a[l] = num;
        return l;
    }
}
