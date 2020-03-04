package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
@SuppressWarnings("ALL")
public class CoinChange {

    @Test
    public void test() {
        System.out.println(coinChange3(new int[] {1, 2, 5}, 11));//3
        System.out.println(coinChange3(new int[] {1, 2, 5}, 12));//3
        System.out.println(coinChange3(new int[] {1, 2, 5}, 13));//4
        System.out.println(coinChange3(new int[] {1, 2, 15}, 13));//7
        System.out.println(coinChange3(new int[] {1, 13, 11, 7}, 100));//8
        System.out.println(coinChange3(new int[] {1}, 11));//11
        System.out.println(coinChange3(new int[] {2}, 11));//-1
        System.out.println(coinChange3(new int[] {5}, 10));//2
        System.out.println(coinChange3(new int[] {5, 6, 7}, 4));//-1
        System.out.println(coinChange3(new int[]{186, 419, 83, 408}, 6249));//20
        System.out.println(coinChange3(new int[]{227, 99, 328, 299, 42, 322}, 9847));//31
        System.out.println(coinChange3(new int[]{336,288,378,16,319,146}, 9212));//26
    }

    /**
     * 错误的解法：先排序，由于硬币数 = n * coins[i] + m * coins[j] + p * coins[k] + ...(i > j > k, 所以coins[i] > coins[j] > coins[k])
     * 如果要最少的硬币，则先用最大的硬币去换，然后再用次大的硬币换，以此类推。
     * 这是错误的，当硬币值越大，p * coins[k] 的p也可以很大，此时就不是最少数量的组合了。
     **/

    /**
     * @description 暴力法
     * @author liqibo
     * @date 2019/7/11 14:55
     **/
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length < 1 || amount == 0) {
            return 0;
        }

        int count = coinChange(coins, 0, amount);
        return count > 0 ? count : -1;
    }

    private int coinChange(int[] coins, int cur, int amount) {

        if (cur >= coins.length) {
            return 0;
        }

        int minCoinsCount = Integer.MAX_VALUE;
        for (int i = cur; i < coins.length; i++) {
            int coinsCount = 0;
            int tempAmount;
            while ((tempAmount = coinsCount * coins[cur]) <= amount) {
                if (tempAmount == amount) {
                    if (coinsCount < minCoinsCount ) {
                        minCoinsCount = coinsCount;
                        //System.out.println("#####" + coinsCount + " * " + coins[cur] + " = " + amount);
                    }
                    break;
                }
                int tempCoinsCount = coinChange(coins, i + 1, amount - tempAmount);
                if (tempCoinsCount > 0) {
                    tempCoinsCount += coinsCount;
                    if (tempCoinsCount < minCoinsCount) {
                        //System.out.println(String.format("=====amount=%d, coinsCount=%d, tempAmount=%d, coinChange(%d, %d)", amount, coinsCount, tempAmount, i + 1, amount - tempAmount));
                        minCoinsCount = tempCoinsCount;
                    }
                }
                coinsCount++;
            }
        }

        if (minCoinsCount == Integer.MAX_VALUE) {
            return 0;
        } else {
            //System.out.println(String.format("cur=%d, coinsCount=%d, amount=%d", cur, minCoinsCount, amount));
            return minCoinsCount;
        }
    }

    /**
     * @description {186, 419, 83, 408}, 6249 就开始超时了
     * @author liqibo
     * @date 2019/7/15 15:00
     **/
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length <= 0 || amount <= 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];

        for (int coin : coins) {
            if (coin < dp.length) {
                dp[coin] = 1;
            }
        }

        return coinChange2(coins, amount, dp);
    }

    private int coinChange2(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        if (dp[amount] > 0) {
            return dp[amount];
        }

        int min = Integer.MAX_VALUE;
        boolean canExchange = false;
        for (int coin : coins) {
            int tmpAmout = coinChange2(coins, amount - coin, dp);
            if (tmpAmout >= 0) {
                canExchange = true;
                min = Math.min(tmpAmout + 1, min);
            }
        }

        if (!canExchange) {
            min = -1;
        }
        dp[amount] = min;
        return min;
    }


    /**
     * 官方解法一：动态规划-自上而下.
     * 时间复杂度：O(Sn) 拿下面这个例子来说，f(11)需要访问n = 3次for，每个for中还要另外访问amount ≈ 10次的递归
     * 空间复杂度：O(S) 开辟了amount个栈空间 同时还有一个amount长度的数组
     *
     * 用一个数组存储所有可能出现的面值的最小所需硬币数量，所以该数组的长度为amount
     * 拿硬币{1, 2, 5},和amount=11举例
     * f(11) = Min[{f(10) + 1个1元硬币}, {f(9) + 1个2元硬币}, {f(6) + 1个5元硬币}]
     * f(10) = Min[{f(9) + 1个1元硬币}, {f(8) + 1个2元硬币}, {f(5) + 1个5元硬币}]
     * ……
     * f(3) = Min[{f(2) + 1个1元硬币}, {f(1) + 1个2元硬币}] = 2
     * f(2) = Min[{f(1) + 1个1元硬币}, {f(0) + 1个2元硬币}] = 1
     * f(1) = Min[{f(0) + 1个1元硬币}] = 1
     * f(0) = 0
     *
     * 这里需要讨论一个事实，为什么f(11) = Min[{f(10) + 1个1元硬币}, {f(9) + 1个2元硬币}, {f(6) + 1个5元硬币}]
     * 而不是f(11) = Min[{f(10) + 1个1元硬币}, {f(9) + 1个2元硬币}, {f(8) + 1个1元硬币 + 1个2元硬币}....]
     * 或者f(11) = Min[{f(10) + 1个1元硬币}, {f(9) + 1个2元硬币}, {f(8) + f(3)}....]呢
     * 这里是假设f(11)至少包含1个1元硬币，那么f(11)就是f(10) + 1个1元硬币; 同理f(11)至少包含1个2元硬币，那么f(11)就是f(9) + 1个2元硬币
     * 以此类推就能得出我们的结论。
     * 再来看{f(8) + 1个1元硬币 + 1个2元硬币}，其实它是{f(9) + 1个2元硬币}的直接子问题，而不是f(11)的。
     * 动态规划的本质就是构造一个最优的子结构
     **/
    public int coinChange3(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange3(coins, amount, new int[amount]);
    }

    private int coinChange3(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange3(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * 自己模仿官方解法一
     **/
    private int coinChange3ByMyself(int[] coins, int rem, int[] count) {

        if (rem < 0) {
            return -1;
        }

        if (rem == 0) {
            return 0;
        }

        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int tempCoinsCount = coinChange3ByMyself(coins, rem - coin, count);
            if (tempCoinsCount >= 0 && tempCoinsCount < minCoins) {
                minCoins = tempCoinsCount + 1;
            }
        }

        return minCoins == Integer.MAX_VALUE ? -1 : (count[rem - 1] = minCoins);
    }


    /**
     * 官方解法：动态规划-自下而上
     **/
    public int coinChange4(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }
}
