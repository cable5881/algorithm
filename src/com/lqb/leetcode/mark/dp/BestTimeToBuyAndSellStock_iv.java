package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BestTimeToBuyAndSellStock_iv {

    @Test
    public void test() {
        System.out.println(maxProfit(2, new int[]{7, 1, 5, 3, 6, 4})); // 7
        System.out.println(maxProfit(2, new int[]{1, 2, 3, 4, 5})); // 4
        System.out.println(maxProfit(2, new int[]{7, 6, 4, 3, 1})); // 0
        System.out.println(maxProfit(2, new int[]{1, 3})); // 2
        System.out.println(maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4})); //6
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); //7
        System.out.println(maxProfit(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));//13
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int opt = k + 1;
        int[][][] dp = new int[prices.length + 1][opt][2];

        //第0天，不管有多少次操作次数，持有股票都是不可能的
        for (int i = 0; i < opt; i++) {
            dp[0][i][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j < opt; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        }

        return dp[prices.length][k][0];
    }
}
