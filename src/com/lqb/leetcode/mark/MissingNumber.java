package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MissingNumber {

    @Test
    public void test1() {
        System.out.println(missingNumber2(new int[]{3, 0, 1}));
        System.out.println(missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    public int missingNumber(int[] nums) {
        if(nums == null) {
            return 0;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i && nums[i] < nums.length) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }

        return nums.length;
    }

    /**
     * 官方解法：异或
     **/
    public int missingNumber2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        //如果一定缺某个数，那么nums中一定存在一个元素等于nums.length
        int miss = nums.length;

        for (int i = 0; i < nums.length; i++) {
            miss ^= i ^ nums[i];
        }

        return miss;
    }

    /**
     * 官方解法： 高斯求和公式
     * 我们可以用 高斯求和公式 求出 [0..n][0..n] 的和，减去数组中所有数的和，就得到了缺失的数字
     **/
    public int missingNumber3(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;

    }
}
