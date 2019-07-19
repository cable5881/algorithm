package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumSubarray {


    @Test
    public void test1() {
        MaximumSubarray demo = new MaximumSubarray();
        System.out.println(demo.maxSubArray2(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(demo.maxSubArray2(new int[] {-2,-3,-3,-1,-5}));
        System.out.println(demo.maxSubArray2(new int[] {-2,-3,6,-3,-1,-5}));
        System.out.println(demo.maxSubArray2(new int[] {1}));
        System.out.println(demo.maxSubArray2(new int[] {-1}));
        System.out.println(demo.maxSubArray2(new int[] {-1, 0}));
    }

    /**
     * 自己的解法: 算法复杂度O(n), 空间复杂度O(1)
     */
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int tempSum = 0;

        for (int num : nums) {
            if (tempSum + num <= 0) {
                if (maxSum < 0 && num > maxSum) {
                    maxSum = num;
                }
                tempSum = 0;
                continue;
            }

            tempSum += num;
            if (tempSum > maxSum) {
                maxSum = tempSum;
            }
        }

        return maxSum;
    }

    /**
     * 官网的解法, 流程有优化.  算法复杂度O(n), 空间复杂度O(1)
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int tempSum = 0;

        for (int num : nums) {
            if (tempSum > 0) {
                //即使tempSum 加上 num 变为负数也没事, 此时tempSum 肯定 没有maxSum大, 并不会赋值给maxSum
                tempSum += num;
            } else {
                //如果之前的子序列和已经小于0了, 那么此时num 如果 比 maxSum大, 那么tempSum = num, 后面会赋值给maxSum
                //反之如果num < maxSum, 然后tempSum = num, tempSum会比maxSum, 那么后面就不赋值给maxSum
                tempSum = num;
            }

            maxSum = Math.max(tempSum, maxSum);
        }

        return maxSum;
    }

}
