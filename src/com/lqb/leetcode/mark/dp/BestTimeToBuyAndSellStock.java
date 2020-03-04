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
        System.out.println(demo.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(demo.maxProfit2(new int[]{7, 6, 4, 3, 1}));
        System.out.println(demo.maxProfit2(new int[]{1, 2}));
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
     * dp[i][k][0] 表示第i天，且还有K次操作机会的情况下，没有持有股票
     * dp[i][k][1] 表示第i天，且还有K次操作机会的情况下，持有股票
     *
     * 当前没有持有股票
     * 昨天也没持有或者昨天持有但是今天卖了（没有买，k不变）
     * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
     *
     * 当前持有股票
     * 昨天也持有今天没操作或者昨天没持有，但是今天买了（k-1）
     * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
     *
     */
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int[][][] dp = new int[prices.length + 1][2][2];

        //第0天，在没有操作次数的情况下，没有操作收益是0
        //第0天，在有操作次数的情况下，没有操作收益是0
        dp[0][0][0] = 0;
        dp[0][1][0] = 0;

        //第0天，在没有操作次数的情况下，持有股票是不可能的
        //第0天，在有操作次数的情况下，持有股票是不可能的
        dp[0][0][1] = Integer.MIN_VALUE;
        dp[0][1][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
        }

        return dp[prices.length][1][0];
    }



}
