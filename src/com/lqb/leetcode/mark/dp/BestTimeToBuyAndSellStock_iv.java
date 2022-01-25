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
        System.out.println(maxProfit(1, new int[]{1}));//0
    }

    public int maxProfit(int maxSellTimes, int[] prices) {

        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int[][][] dp = new int[prices.length][maxSellTimes + 1][2];
        //因为最小值再减去1就是最大值Integer.MIN_VALUE-1=Integer.MAX_VALUE
        //所以这里除2防止越界
        int minValue = Integer.MIN_VALUE / 2;

        //第一天不动
        dp[0][0][0] = 0;
        //第一天买入，在最大交易次数只有0次的情况下是不可能的
        dp[0][0][1] = minValue;
        //第一天买入
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = minValue;
            for (int k = 1; k <= maxSellTimes; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }

        return dp[prices.length - 1][maxSellTimes][0];
    }
}
