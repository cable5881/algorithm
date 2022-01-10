package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MaximumProductSubarray {

    @Test
    public void test() {
        System.out.println(maxProduct4(new int[]{2, 3, -2, 4}));//6
        System.out.println(maxProduct4(new int[]{-2, 0, -1}));//0
        System.out.println(maxProduct4(new int[]{-2, -2, -1}));//4
        System.out.println(maxProduct4(new int[]{-2, -2, -2, -2}));//16

        //最后一个数比之前的子序列乘积都要大
        System.out.println(maxProduct4(new int[]{12, 2, -3, 28}));//28

        System.out.println(maxProduct4(new int[]{-3, 0, 1, -2}));//1
        System.out.println(maxProduct4(new int[]{-2, 1, 0, -3}));//1

        //负数数量为奇数
        System.out.println(maxProduct4(new int[]{2, -5, -2, -4, 3}));//24

        //虽然负数为正，但是中间有0隔开
        System.out.println(maxProduct4(new int[]{-1, 0, -1, 2, -3, 1, 2, 3, -2}));//72

        //2, 3, -2, 4, -5,6,7,-100
    }

    /**
     * @author liqibo
     * @date 2019/11/4 21:16
     * @description 暴力法
     * 时间复杂度 O(n^2)
     */
    public int maxProduct(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int res = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp > res) {
                res = temp;
            }
            for (int j = i + 1; j < nums.length; j++) {
                temp *= nums[j];
                if (temp > res) {
                    res = temp;
                }
            }

        }

        return res;
    }

    /**
     * 错误的解法：无法解决这种情况：2, -5, -2, -4, 3
     * 只能得到最大20，即2*(-5)*(-2)
     */
    public int maxProduct2(int[] nums) {

        int max = nums[0];
        //表示一直乘下去的sum
        int sum_1 = max;
        //表示一个子序列的sum
        int sum_2 = max;
        for (int i = 1; i < nums.length; i++) {
            sum_1 *= nums[i];
            sum_2 = Math.max(sum_2 * nums[i], nums[i]);
            max = Math.max(max, Math.max(sum_1, sum_2));
        }

        return max;
    }


    /**
     * 动态规划1：完整的动态规划
     * 需要理解的一个点是：由于存在负数，那么会导致最大的变最小的，最小的变最大的
     */
    public int maxProduct4(int[] nums) {
        // 状态转移方程初步可以定为 dp[i] = dp[i - 1] * nums[i]，
        // 但是nums[i]可能会小于0，因此这个动态转移方程需要一个二维的
        // if nums[i] > 0
        //  dp[i][max] = max(dp[i - 1][max]*nums[i], dp[i - 1][max], nums[i])
        // 即i的最大乘积子序列可能是上一个位置的最大乘积子序列、上一个位置的最大乘积子序列乘当前值nums[i]、或者当前值nums[i]
        // 同理得i的最小乘积子序列：
        // if nums[i] < 0
        //  dp[i][max] = max(dp[i - 1][min]*nums[i], dp[i - 1][min], nums[i])
        int[][] dp = new int[nums.length][2];
        final int maxIdx = 0;
        final int minIdx = 1;
        dp[0][maxIdx] = nums[0];
        dp[0][minIdx] = nums[0];
        //某子序列乘积，在遍历中不断更新，不是nums[i]就是nums[i]*iMax
        int iMax = nums[0];
        int iMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = iMax;
                iMax = iMin;
                iMin = tmp;
            }
            iMax = Math.max(nums[i], iMax * nums[i]);
            iMin = Math.min(nums[i], iMin * nums[i]);
            dp[i][maxIdx] = Math.max(dp[i - 1][maxIdx], iMax);
            dp[i][minIdx] = Math.min(dp[i - 1][minIdx], iMin);
        }

        return dp[nums.length - 1][maxIdx];
    }

    /**
     * 动态规划2：在动态规划1的基础上，其实我们无需记录所有位置的最大值和最小值，只需要记录上一次的即可
     * 也可以这么解释：
     * 遍历数组时计算当前最大值，不断更新
     * 令 dpMax 为当前最大值，则当前最大值为 dpMax = max(dpMax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。
     * 因此还需要维护当前最小值 dpMin，dpMin = min(dpMin * nums[i], nums[i])
     * 当负数出现时则 dpMax 与 dpMin 进行交换再进行下一步计算
     */
    public int maxProduct5(int[] nums) {

        int res = nums[0];
        int dpMax = nums[0];
        int dpMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // if (nums[i] > 0) {
            //     dpMax = Math.max(dpMax * nums[i], nums[i]);
            //     dpMin = Math.min(dpMin * nums[i], nums[i]);
            // } else {
            //     dpMax = Math.max(dpMin * nums[i], nums[i]);
            //     这种写法不可以，因为dpMax已经在上一步被赋值了，这里已经不是原来的dpMax了
            //     dpMin = Math.min(dpMax * nums[i], nums[i]);
            // }

            if (nums[i] < 0) {
                int tmp = dpMax;
                dpMax = dpMin;
                dpMin = tmp;
            }
            dpMax = Math.max(dpMax * nums[i], nums[i]);
            dpMin = Math.min(dpMin * nums[i], nums[i]);
            res = Math.max(res, dpMax);
        }

        return res;
    }


    /**
     * 和解法二思想是类似的，只需要理解这种解法应该考虑到几种情况
     * 1.如果负数个数是偶数，那么一直乘就完事
     * 2.如果负数个数是奇数，那么最长子序列可能是第一个负数后面的所有数字（少掉第一个负数后负数个数就为偶数了）
     * 或者是最后一个负数前的所有数字
     * 3.如果出现0的情况，只需要重置即可
     *
     * 针对1和2的情况，正序和倒序遍历就可以解决
     * 针对3的情况，只需要将临时乘积重置为1接着往下乘即可
     */
    public int maxProduct7(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }

        }
        max = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }

        return res;
    }



}
