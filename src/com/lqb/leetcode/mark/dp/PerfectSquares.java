package com.lqb.leetcode.mark.dp;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PerfectSquares {

    @Test
    public void test() {
        System.out.println(numSquares2(12));
        System.out.println(numSquares2(13));
    }

    private static Set<Integer> squares = new HashSet<>();

    static {
        //利用恒等式： 1+3+5+7+....+(2*n-1)=n^2
        int sum = 0;
        int maxSqrt = Double.valueOf(Math.sqrt(Integer.MAX_VALUE)).intValue();
        int max = maxSqrt * maxSqrt;
        int maxAdd = Integer.MAX_VALUE - max;
        for (int i = 1; sum <= max && i <= maxAdd; i += 2) {
            sum += i;
            squares.add(sum);
        }
    }

    /**
     * @author liqibo
     * @date 2019/12/4 16:58
     * @description 自己的解法：利用dp数组存储遍历过数
     */
    public int numSquares(int n) {

        if (n == 0) {
            return n;
        }

        int[] dp = new int[n + 1];
        numSquares(n, dp);

        return dp[n];
    }

    private int numSquares(int n, int[] dp) {

        if (n == 0) {
            return 0;
        }

        if (dp[n] != 0) {
            return dp[n];
        } else if (squares.contains(n)) {
            dp[n] = 1;
            return 1;
        }

        int mid = n / 2;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= mid; i++) {
            int tmp = numSquares(n - i, dp) + numSquares(i, dp);
            min = Math.min(min, tmp);
        }

        dp[n] = min;
        return min;
    }

   /**
    * 官方解法：需要承认一个事实，要使得dp[n]最小，则dp[n]=dp[n - i*i] + 1，即至少使用一个平方数
    * 首先初始化长度为n+1的数组dp，每个位置都为0
    * 如果n为0，则结果为0
    * 对数组进行遍历，下标为i，每次都将当前数字先更新为最大的结果，即dp[i]=i，比如i=4，最坏结果为4=1+1+1+1即为4个数字
    * 动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
    * 时间复杂度：O(n*sqrt(n))，sqrt为平方根
    */
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最坏的情况就是每次+1（先给dp[i]设置一个最大值来进行迭代的比较）
            dp[i] = i;
            for (int j = 1; i >= j * j; j++) {
                // 动态转移方程
                // dp[4] = min(dp[4], dp[4 - 2*2] + 1) = 1
                // dp[5] = min(dp[5], dp[5 - 2*2] + 1) = 2
                // ...
                // dp[12] = min(dp[12], dp[12 - 2*2] + 1) = 3
                //        = min(dp[12], dp[12 - 3*3] + 1) = 4
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}

