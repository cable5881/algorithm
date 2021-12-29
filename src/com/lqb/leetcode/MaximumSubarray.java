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
        System.out.println(demo.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));  //6
        System.out.println(demo.maxSubArray(new int[] {-2,-3,-3,-1,-5}));         //-1
        System.out.println(demo.maxSubArray(new int[] {-2,-3,6,-3,-1,-5}));       //6
        System.out.println(demo.maxSubArray(new int[] {1}));                      //1
        System.out.println(demo.maxSubArray(new int[] {-1}));                     //-1
        System.out.println(demo.maxSubArray(new int[] {-1, 0}));                  //0
    }

    /**
     * @description O(n)的迭代法(也可以认为是动态规划)
     * @author liqibo
     * @date 2021/12/29
     **/
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return Integer.MIN_VALUE;
        }

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > sum) {
                sum = nums[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        //为啥可以认为是动态规划？
        //假设dp[i]是数组某个下标时的最大连续和，那么应该满足dp[i]=max(dp[i - 1] + nums[i], nums[i])
        //因此可以用一个数组保存每个位置下的最大连续和
        //但由于我们只需要求一个最大的，因此就是上面这种写法，不需要记录历史每个时刻的连续和。
        //用上面的解法，sum就是dp[i]

        return maxSum;
    }
}
