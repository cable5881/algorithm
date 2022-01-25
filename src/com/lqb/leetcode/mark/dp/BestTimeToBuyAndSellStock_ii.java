package com.lqb.leetcode.mark.dp;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 */
public class BestTimeToBuyAndSellStock_ii {

    @Test
    public void test() {
        System.out.println(maxProfit3(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit3(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maxProfit3(new int[]{7, 6, 4, 3, 1}));
    }

    /**
     * @description 自己的解法：贪心算法
     * 只要第i天比第i-1的价格高则直接卖，然后接着买第i天的。如果i + 1天比i天价格高当然好，
     * 如果低那我们可以撤销第i天的购买记录，买第i + 1天的
     * @author liqibo
     * @date 2019/9/19 19:17
     **/
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int p = 0;
        int buy = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy) {
                p += (prices[i] - buy);
            }

            //不管明天的价格是高还低,买了再说.如果明天低,那我就买明天的,今天的就相当于撤销了
            buy = prices[i];
        }

        return p;
    }

    /**
     * @description 同解法三，有微弱的优化
     * @author liqibo
     * @date 2019/9/19 19:22
     **/
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            //直接通过i - 1就索引到昨天的价格了，不需要用另外的变量保存
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    /**
     * @author liqibo
     * @date 2020/3/4 14:28
     * @description 状态转移框架
     * 因为交易次数没有限制，所以k不作为变量
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

}
