package com.lqb.algorithm;

import org.junit.Test;

/**
 * @Description:二分查找
 * @Author:JackBauer
 * @Date:2016年9月4日 下午8:27:58
 */
public class BinarySearch {

    @Test
    public void testBinarySearch() {
        int[] a = {10, 11, 12, 12, 16, 18, 23, 29, 33, 48, 54, 57, 68, 77, 77, 77, 77, 84, 98};
        System.out.println(binarySearch(a, 48));//9
        System.out.println(binarySearch(a, 8));//-1
    }

    @Test
    public void testFindFirst() {
        int[] a = {1, 2, 3, 5, 5};
        System.out.println(findFirstEqual2(a, 1));//0
        System.out.println(findFirstEqual2(a, 2));//1
        System.out.println(findFirstEqual2(a, 4));//-1
        System.out.println(findFirstEqual2(a, -1));//-1
        System.out.println(findFirstEqual2(a, 5));//3
        System.out.println(findFirstEqual2(new int[]{1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 3));//2
    }

    @Test
    public void testFindLast() {
        int[] a = {1, 2, 3, 5, 5, 6};
        System.out.println(findLastEqual(a, 1));//0
        System.out.println(findLastEqual(a, 2));//1
        System.out.println(findLastEqual(a, 4));//-1
        System.out.println(findLastEqual(a, -1));//-1
        System.out.println(findLastEqual(a, 5));//4
        System.out.println(findLastEqual(new int[]{1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 3));//11
    }

    @Test
    public void testFindFirstGreaterEqual() {
        int[] a = {1, 2, 3, 5, 5, 6};
        System.out.println(findFirstGreaterEqual(a, 1));//0
        System.out.println(findFirstGreaterEqual(a, 2));//1
        System.out.println(findFirstGreaterEqual(a, 4));//3
        System.out.println(findFirstGreaterEqual(a, -1));//0
        System.out.println(findFirstGreaterEqual(a, 5));//3
        System.out.println(findFirstGreaterEqual(new int[]{1, 2, 3, 3}, 3));//2
    }

    //常规二分搜索, 如果没有查找到则返回-(插入的位置 + 1)
    public static int binarySearch(int[] a, int key) {

        int low = 0;
        int high = a.length - 1;
        int mid = -1;

        //注意low和high是可以相等的
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -2 - mid;
    }

    //找出第一个与key相等的元素(自己的解法)
    public static int findFirstEqual(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = 0;

        while (low < high) {
            mid = low + (high - low) / 2;
            //如果目标在前半段
            if (a[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] >= key) {
                high = mid - 1;
            } else if (a[mid] < key) {
                high = mid + 1;
            }
        }

        //注意这里是判断high而不是mid. 因为high是可以卡在第一个目标位置不动的，而mid可能在第一个位置的左边。又由于low == high已经退出循环
        //所以导致mid永远在第一个位置的左边。
        return a[high] == key ? high : -1 - high;
    }

    //找出第一个与key相等的元素解法2
    public static int findFirstEqual2(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        //注意循环条件可以相等
        //循环退出的条件是high < low
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] >= key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        //当key不在数组中，且在数组右侧时，此时low会 >= a.length，因此要判断low < a.length
        if (low < a.length && a[low] == key) {
            return low;
        } else {
            return -1;
        }
    }

    //找出最后一个与key相等的元素
    public static int findLastEqual(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            //如果目标在后半段
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (high >= 0 && a[high] == key) {
            return high;
        }
        return -1;
    }

    //找出第一个等于或者大于Key的元素{1,2,4,6,6}
    public static int findFirstGreaterEqual(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            //如果元素在前半段
            if (a[mid] >= key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }


        if (low < a.length) {
            return low;
        }

        return -1;
    }
}
