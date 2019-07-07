package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class ProductExceptSelf {

    @Test
    public void test1() {
        ProductExceptSelf demo = new ProductExceptSelf();
        int[] result = demo.productExceptSelf(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(result));
    }


    //花费了O(n)的空间，不好
    public int[] productExceptSelf(int[] nums) {

        int len = nums.length;
        int R[] = new int[len - 1];
        int L[] = new int[len - 1];
        int result[] = new int[len];

        L[0] = nums[0];
        R[0] = nums[len - 1];

        for (int i = 1; i < len - 1; i++) {
            L[i] = L[i - 1] * nums[i];
        }

        for (int j = 1; j < len - 1; j++) {
            R[j] = R[j - 1] * nums[len - j - 1];
        }


        result[0] = R[len - 2];
        result[len - 1] = L[len - 2];
        for (int k = 1; k < len - 1; k++) {
            result[k] = L[k - 1] * R[len - k - 2];
        }


        return result;
    }

    //不需要花费空间
    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < res.length; i++) {
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }

}
