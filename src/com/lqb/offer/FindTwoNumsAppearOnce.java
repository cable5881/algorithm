package com.lqb.offer;

import org.junit.Test;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 * @author:JackBauer
 * @date:2016年6月18日 下午8:24:32
 */
public class FindTwoNumsAppearOnce {

    @Test
    public void test() {
        int[] array = {1, 1, 2, 33, 33, 44, 44, 66, 88, 66, 88, 3};
//		int[] array = {2,4,3,6,3,2,5,5};
        int num1[] = {0};
        int num2[] = {0};

        FindNumsAppearOnce2(array, num1, num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }

    @Test
    public void test1() {
        int i = 1021;
        while (i != 0) {
            System.out.println(i);
            i = i >> 1;
        }
    }

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length <= 1) {
            return;
        }

        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }

        int leastBitOf1 = findLeastBitOf1(temp);

        for (int i = 0; i < array.length; i++) {
            if (KthBitIs1(array[i], leastBitOf1)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }

    }

    public int findLeastBitOf1(int num) {

        int indexOf1 = 0;

        while ((num & 1) == 0 && indexOf1 < Integer.SIZE * 8) {
            num >>= 1;
            indexOf1++;
        }
        return indexOf1;
    }

    public boolean KthBitIs1(int num, int k) {
        if (((num >> k) & 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 20200219尝试
     */
    public void FindNumsAppearOnce2(int[] a, int num1[], int num2[]) {
        //先获得异或的结果
        int res = 0;
        for (int i : a) {
            res ^= i;
        }

        //找到该结果哪一位是1
        int bitOfDiff = 1;
        while ((bitOfDiff & res) == 0) {
            bitOfDiff = bitOfDiff << 1;
        }

        int res1 = 0;
        int res2 = 0;

        for (int num : a) {
            //将数组分成两半
            if ((num & bitOfDiff) != 0) {
                res1 ^= num;
            } else {
                res2 ^= num;
            }
        }

        num1[0] = res1;
        num2[0] = res2;
    }

}
