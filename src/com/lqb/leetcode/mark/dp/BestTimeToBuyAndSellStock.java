package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BestTimeToBuyAndSellStock {

    @Test
    public void test() {
        BestTimeToBuyAndSellStock demo = new BestTimeToBuyAndSellStock();
        System.out.println(demo.maxProfit3(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(demo.maxProfit3(new int[]{7, 6, 4, 3, 1}));
        System.out.println(demo.maxProfit3(new int[]{1, 2}));
    }

    /**
     * @description 官方解法：只需要找出最小的价格，然后其他比这个大的价格都去减去最小的价格，这个差比以往的大则收益最大
     * @author liqibo
     * @date 2019/6/28 11:33
     * @update 2019/9/19 17:31 今天做的时候已经可以用这种做法了
     **/
    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if ((prices[i] > minPrice)) {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    /**
     * @author liqibo
     * @date 2020/3/4 12:24
     * @description 万能解法，利用状态转移方程
     *
     * dp[i][k][0] 表示第i天，且最大K次交易次数的情况下，没有持有股票
     * dp[i][k][1] 表示第i天，且最大K次交易次数的情况下，持有股票
     *
     * 当前没有持有股票
     * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
     * 1、我昨天就没有持有，且截至昨天最大交易次数限制为 k；然后我今天选择保持原样，所以我今天还是没有持有，最大交易次数限制依然为k。
     * 2、我昨天持有股票，且截至昨天最大交易次数限制为k；但是今天我卖了，所以我今天没有持有股票了，最大交易次数限制依然为k。
     *
     * 当前持有股票
     * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
     * 1、我昨天就持有着股票，且截至昨天最大交易次数限制为 k；然后今天选择保持原样，所以我今天还持有着股票，最大交易次数限制依然为 k。
     * 2、我昨天本没有持有，且截至昨天最大交易次数限制为 k - 1；但今天我选择买入，所以今天我就持有股票了，最大交易次数限制为 k。
     *
     * 详细见：https://github.com/labuladong/fucking-algorithm/blob/master/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97/%E5%9B%A2%E7%81%AD%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98.md
     */
    public int maxProfit2(int[] prices) {

        int days = prices.length;
        int haveStock = 2;
        int maxSellTimes = 1;
        int[][][] dp = new int[days][maxSellTimes + 1][haveStock];
        //第一天休息
        dp[0][0][0] = 0;
        //第一天最大只能买卖0次，则不允许买入
        dp[0][0][1] = Integer.MIN_VALUE;
        //第一天最大只能买卖1次，可以不买，也可以买入
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //无操作，不持有。
            dp[i][0][0] = 0;
            //第一天最大只能买卖0次，则不允许买入
            dp[i][0][1] = Integer.MIN_VALUE;
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
        }

        return Math.max(dp[days - 1][0][0], dp[days - 1][1][0]);
    }


    /**
     * 继续maxProfit2的算法，可以做一些简单优化
     */
    public int maxProfit3(int[] prices) {
        int days = prices.length;
        int haveStock = 2;
        int[][] dp = new int[days][haveStock];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        }

        return dp[days - 1][0];
    }
}
