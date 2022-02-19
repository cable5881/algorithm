package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 字节跳动面试题：
 * 从起点开始接下来有 100 个方块，相邻方块间的距离都为 1，每个方块上有增加体力的食用蘑菇或减少体力的毒蘑菇，
 * 蘑菇带来的体力改变是已知的。一个人初始体力为 m，每次可以往前跳任意个方块，体力耗尽就会死掉。
 *
 * 1.每跳一次消耗的体力与跳的距离成正比，比例为1。问这个人能否跳到终点，如果能，求可能剩余的最大体力。
 * 2.每跳一次消耗的体力是跳的距离的平方。问这个人能否跳到终点，如果能，求可能剩余的最大体力。
 */
public class Mushroom {

    @Test
    public void test() {
        System.out.println(func(new int[]{-1, -1, -1, -1, -1}, 5));
        System.out.println(func(new int[]{-1, -1, -1, -1, 1}, 5));
        System.out.println(func(new int[]{-1, -1, -1, -1, 100}, 5));
        System.out.println(func(new int[]{-1, -1, -1, 1, 1}, 5));
        System.out.println(func(new int[]{-1, -1, -1, 1, 1}, 6));
        System.out.println(func(new int[]{-1, -1, 2, -1, 1}, 6));
    }

    /**
     * 动态规划: dp[i] = max(dp[i - 1]-1, dp[i - 2]-2, ...., dp[i - n] - n) + nums[i]
     */
    public int func (int[] nums, int m) {
        int[] dp = new int[nums.length + 1];
        //初始化
        dp[0] = m;
        for(int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
            //是否能够跳到第i格
            for(int j = 0; j < i; j++) {
                //在不吃第i格的蘑菇情况下还剩下多少体力
                int jumpCost = i - j;
                int curM = dp[j] - jumpCost;
                dp[i] = Math.max(dp[i], curM);
            }
            //如果从第1个到第i-1格都跳不到第i格，那么就终止循环
            if(dp[i] <= 0) {
                return -1;
            }
            dp[i] += nums[i - 1];
        }
        return dp[nums.length];
    }

    /**
     * 第二问
     */
    public int func2(int[] nums, int m) {
        int[] dp = new int[nums.length + 1];
        dp[0] = m;
        for(int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
            for(int j = 0; j < i; j++) {
                //每跳一次消耗的体力是跳的距离的平方
                int jumpCost = (i - j) * (i - j);
                int curM = dp[j] - jumpCost;
                dp[i] = Math.max(dp[i], curM);
            }
            if(dp[i] <= 0) {
                return -1;
            }
            dp[i] += nums[i - 1];
        }
        return dp[nums.length];
    }


}
