package com.lqb.offer;

import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @Author:JackBauer
 * @Date:2016年7月16日 上午10:30:51
 */
@SuppressWarnings("Duplicates")
public class MinNumberInRotateArray {

    @Test
    public static void main(String[] args) {
        System.out.println(minNumberInRotateArray3(new int[]{1, 2, 3, 4, 5}));
        System.out.println(minNumberInRotateArray3(new int[]{4, 5, 1, 2, 3}));
        System.out.println(minNumberInRotateArray3(new int[]{5, 1, 2, 3, 4}));
        System.out.println(minNumberInRotateArray3(new int[]{3, 4, 5, 1, 2}));
        System.out.println(minNumberInRotateArray3(new int[]{2, 1, 2, 2, 2}));
        System.out.println(minNumberInRotateArray3(new int[]{2, 2, 2, 1, 2}));
    }

    public static int minNumberInRotateArray(int[] array) {

        if (array == null || array.length == 0) {
            throw new RuntimeException("null");
        } else if (array.length == 1) {
            return array[0];
        }

        int start = 0;
        int end = array.length - 1;
        int mid = 0;

        while (array[start] >= array[end]) {

            if (end - start == 1) {
                mid = end;
                break;
            }

            mid = (start + end) / 2;

            if (array[start] == array[end] && array[start] == array[mid]) {
                return getMinByInorder(array, start, end);
            }

            if (array[mid] <= array[start]) {
                end = mid;
            } else if (array[mid] >= array[end]) {
                start = mid;
            }

        }

        return array[mid];
    }

    public static int getMinByInorder(int[] arr, int start, int end) {

        int min = arr[0];

        while (start <= end) {
            if (arr[start] < min) {
                min = arr[start];
            }
            start++;
        }

        return min;
    }

    //can work
    public static int myMinNumberInRotateArray(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int start = 0;
        int end = a.length - 1;
        int mid = (start + end) / 2;

        while (a[start] >= a[end]) {
            if (end - start <= 1) {
                return a[end];
            }

            if (a[start] == a[end] && a[start] == a[mid]) {
                return minInorder(a, start, end);
            }

            if (a[mid] > a[end]) {
                start = mid;
            } else {
                end = mid;
            }

            mid = (start + end) / 2;
        }

        return a[start];
    }

    private static int minInorder(int[] a, int start, int end) {
        int min = a[start];

        for (int num : a) {
            if (num < min) {
                return num;
            }
        }

        return min;
    }

    /**
     * 不支持元素重复的情况，如[2,2,2,1,2]，会返回2
     */
    public static int minNumberInRotateArray2(int[] a) {
        if (a == null || a.length <= 0) {
            return 0;
        }

        int l = 0;
        int r = a.length - 1;

        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] <= a[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return a[l];
    }

    public static int minNumberInRotateArray3(int[] a) {
        if (a == null || a.length <= 0) {
            return 0;
        }

        int l = 0;
        int r = a.length - 1;

        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] < a[r]) {//中间的小于右边，那自然最小的在左边
                r = m;
            } else if (a[m] == a[r]) {//中间的=右边，分两种情况
                if (a[l] == a[m]) {//如果三边都相等，那么最小的可以存在于[l, r]范围内任何一个位置，需要一个个查看
                    return getMinByInorder(a, l, r);
                } else {//无论左边是比中间大还是比中间小，最小的一定在左边
                    r = m;
                }
            } else {
                l = m + 1;
            }
        }

        return a[l];
    }
}
