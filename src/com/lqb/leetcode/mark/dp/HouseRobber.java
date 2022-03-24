package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * <p>
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class HouseRobber {

    @Test
    public void test() {
        System.out.println(rob3(new int[]{1, 2, 3, 1}));
        System.out.println(rob3(new int[]{2, 7, 9, 3, 1}));
    }


    /**
     * 动态规划
     */
    public int rob3(int[] nums) {

        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    /**
     * @description 自己的解法，还有优化的空间，不需要使用额外的空间
     * @author liqibo
     * @date 2019/7/2 10:32
     **/
    public int rob(int[] nums) {
        int len = nums.length;
        if (nums == null || len < 1) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        int[] max = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int next1 = i + 2;
            int next2 = i + 3;
            int nextV1 = nums[i];
            int nextV2 = nums[i];

            if (next1 <= len - 1) {
                nextV1 = nums[i] + max[next1];
            }

            if (next2 <= len - 1) {
                nextV2 = nums[i] + max[next2];
            }

            max[i] = nextV1 > nextV2 ? nextV1 : nextV2;
        }

        return max[0] > max[1] ? max[0] : max[1];
    }

    /**
     * 官方解法：
     * 设置两个变量，sumOdd 和 sumEven 分别对数组的奇数和偶数元素求和。
     * 最后比较这两个和谁更大，谁就是最优解。
     *
     * 接下来要解决的就是最优解不是纯奇数和或者偶数和的情况。
     * 这种情况下，最优解可能前半段出现在这边，后半段出现在另一边。
     * 那么只要找到一个时机，当这一段的最优解没有另一边好时，就复制对面的最优解过来。
     *
     **/
    public int rob2(int[] nums) {
        int len = nums.length;
        if (nums == null || len < 1) {
            return 0;
        }

        int odd = 0;
        int even = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                odd += nums[i];
                odd = odd > even ? odd : even;
            } else {
                even += nums[i];
                even = even > odd ? even : odd;
            }
        }

        return odd > even ? odd : even;

    }


}
