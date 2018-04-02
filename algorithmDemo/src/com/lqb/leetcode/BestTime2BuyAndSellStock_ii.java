package com.lqb.leetcode;

/**
 * Say you have an array for which the i th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p>
 * 思路:
 * 如果买了,则选择股价涨了时才卖,否则死也不卖
 */
public class BestTime2BuyAndSellStock_ii {

    public static void main(String[] args) {
        int[] prices = {1, 3, 7, 9, 6, 4, 5, 8, 7, 5, 5, 4, 6};
        BestTime2BuyAndSellStock_ii test = new BestTime2BuyAndSellStock_ii();
        System.out.println(test.maxProfit(prices));
    }


    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        return getMaxProfitByRecursion(prices, 0, true, -1);
    }

    private int getMaxProfitByRecursion(int[] prices, int day, boolean isStockBuyable, int dayOfBuy) {
        if (day >= prices.length) {
            return 0;
        }

        int balanceAfterBuy = 0;
        int balanceAfterSell = 0;

        if (isStockBuyable) {
            balanceAfterBuy = getMaxProfitByRecursion(prices, day + 1, false, day) - prices[day];
        } else if (prices[day] > prices[dayOfBuy]) {
            balanceAfterSell = getMaxProfitByRecursion(prices, day + 1, true, -1) + prices[day];
        }

        int balanceWithoutAct = getMaxProfitByRecursion(prices, day + 1, isStockBuyable, dayOfBuy);

        int maxBalance = balanceAfterBuy > balanceAfterSell ? balanceAfterBuy : balanceAfterSell;
        maxBalance = maxBalance > balanceWithoutAct ? maxBalance : balanceWithoutAct;

        return maxBalance;
    }
}
