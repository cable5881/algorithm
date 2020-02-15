package com.lqb.offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * @author:JackBauer
 * @date:2016年6月14日 下午1:38:42
 */
@SuppressWarnings("Duplicates")
public class GetNthLeastNumbers {

    @Test
    public void test() {
        int[] array = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println(getLeastNumbers(array, 4));
    }


    public ArrayList<Integer> getLeastNumbers(int[] array, int k) {

        ArrayList<Integer> leastNumbers = new ArrayList<>();

        if (array == null || array.length == 0 || k <= 0 || k > array.length) {
            return leastNumbers;
        }

        getLeastNumbers(array, 0, array.length - 1, k - 1);

        for (int i = 0; i < k; i++) {
            leastNumbers.add(array[i]);
        }

        return leastNumbers;
    }

    public void getLeastNumbers(int[] array, int start, int end, int k) {

        int point;

        if (start < end) {
            point = partition(array, start, end);

            if (point == k) {
                return;
            } else if (point < k){
                getLeastNumbers(array, point + 1, end, k);
            } else if (point > k) {
                getLeastNumbers(array, start, point - 1, k);
            }
        }

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
