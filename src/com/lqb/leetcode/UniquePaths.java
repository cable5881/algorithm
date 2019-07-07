package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
public class UniquePaths {
    private int count = 0;

    public int uniquePaths(int x, int y) {
        uniquePathsCore(x, y, 1, 1);
        return count;
    }

    private void uniquePathsCore(int x, int y, int curX, int curY) {
        if (curX == x && curY == y) {
            count++;
        }

        if (curX <= x) {
            uniquePathsCore(x, y, curX + 1, curY);
        }

        if (curY <= y) {
            uniquePathsCore(x, y, curX, curY + 1);
        }
    }

    /**
     * dp[1][1] = dp[1][0] + dp[0][1]
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }

    @Test
    public void test1_1() {
        Assert.assertEquals(1, uniquePaths(1, 1));
    }

    @Test
    public void test2_2() {
        Assert.assertEquals(2, uniquePaths(2, 2));
    }

    @Test
    public void test3_3() {
        Assert.assertEquals(6, uniquePaths(3, 3));
    }

    @Test
    public void test3_7() {
        Assert.assertEquals(28, uniquePaths(3, 7));
    }

    @Test
    public void test100_100() {
        Assert.assertEquals(28, uniquePaths(100, 100));
    }
}
