package com.lqb.offer;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 利用快速排序返回中位数即可
 *
 * @author:JackBauer
 * @date:2016年6月14日 下午1:40:52
 */
public class MoreThanHalfNumOfArray {

    public static void main(String[] args) {
        // int[] array = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };
//		int[] array = { 1, 1, 5, 7, 9, 2, 1, 1, 1 };
//		int[] array = {1,2,2};
//		int[] array = {2,2,2,2,2,1,3,4,5};
        int[] array = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        System.out.println(MoreThanHalfNum_Solution(array));
    }

    public static int MoreThanHalfNum_Solution(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int point = QSort(array, 0, array.length - 1);
        int result = array[point];

        return isNumMoreThanHalf(array, result) ? result : 0;
    }

    public static int QSort(int[] array, int start, int end) {

        int point;

        if (start < end) {
            point = partition(array, start, end);
            int mid = array.length / 2;
            if (point == mid) {
                return point;
            } else if (point < mid) {
                return QSort(array, point + 1, end);
            } else {
                return QSort(array, start, point - 1);
            }
        }

        return start;//注意返回start
    }

    public static int partition(int[] array, int start, int end) {

        int temp = array[start];
        int l = start;
        int r = end;

        while (l < r) {
            if (temp <= array[r]) {
                r--;
            } else {
                swap(array, r, l);
                l++;
                while (l < r) {
                    if (temp >= array[l]) {
                        l++;
                    } else {
                        swap(array, r, l);
                        r--;
                        break;
                    }
                }
            }
        }

        return l;
    }

    public static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    public static boolean isNumMoreThanHalf(int[] array, int num) {

        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                times++;
            }
        }

        return times > (array.length / 2);
    }

}
