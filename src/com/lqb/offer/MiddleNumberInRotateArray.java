package com.lqb.offer;

import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的中位数。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的中位数为3。
 */
public class MiddleNumberInRotateArray {

    @Test
    public void test() {
        System.out.println(midNumberInRotateArray2(new int[]{1}));//1
        System.out.println(midNumberInRotateArray2(new int[]{1, 2}));//1
        System.out.println(midNumberInRotateArray2(new int[]{2, 1}));//1
        System.out.println(midNumberInRotateArray2(new int[]{1, 2, 3, 4, 5}));//3
        System.out.println(midNumberInRotateArray2(new int[]{4, 5, 1, 2, 3}));//3
        System.out.println(midNumberInRotateArray2(new int[]{5, 1, 2, 3, 4}));//3
        System.out.println(midNumberInRotateArray2(new int[]{3, 4, 5, 1, 2}));//3
        System.out.println(midNumberInRotateArray2(new int[]{3, 4, 1, 2}));//(2+3)/2
        System.out.println(midNumberInRotateArray2(new int[]{5, 6, 1, 2, 3, 5}));//(3+5)/2=4
    }

    public int midNumberInRotateArray(int[] a) {

        if (a == null || a.length <= 0) {
            return -1;
        }

        int l = 0;
        int r = a.length - 1;

        //先找出旋转数组的最小值(不考虑重复数字)
        while (l < r) {
            int mid = (r + l) / 2;
            if (a[mid] <= a[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        //此时 r 是 旋转数组的最小值的数组下标
        //注意越界问题
        int mid = ((a.length / 2) + r) % a.length;
        //如果长度是偶数则中位数是两者的中间值,同样也要注意mid-1可以越界的问题
        return (a.length & 1) == 0 ? (a[mid] + a[(mid - 1) < 0 ? a.length - 1 : mid - 1]) / 2 : a[mid];
    }

    public int midNumberInRotateArray2(int[] a) {

        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] < a[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        int delta = (a.length % 2 == 0 ? a.length - 1 : a.length) / 2;
        return a[(l + delta) % a.length];
    }
}
