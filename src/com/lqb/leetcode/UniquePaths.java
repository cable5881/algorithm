package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniquePaths {

    @Test
    public void test1_1() {
        Assert.assertEquals(1, uniquePaths(1, 1));
        Assert.assertEquals(1, uniquePaths2(1, 1));
    }

    @Test
    public void test2_2() {
        Assert.assertEquals(2, uniquePaths(2, 2));
        Assert.assertEquals(2, uniquePaths2(2, 2));
    }

    @Test
    public void test3_3() {
        Assert.assertEquals(6, uniquePaths(3, 3));
        Assert.assertEquals(6, uniquePaths2(3, 3));
    }

    @Test
    public void test3_7() {
        Assert.assertEquals(28, uniquePaths(3, 7));
        Assert.assertEquals(28, uniquePaths2(3, 7));
    }

    /**
     * dfs
     **/
    public int uniquePaths(int x, int y) {
        return uniquePathsCore(x, y, 1, 1);
    }

    private int uniquePathsCore(int x, int y, int curX, int curY) {

        if (curX > x || curY > y) {
            return 0;
        }

        if (curX == x && curY == y) {
            return 1;
        }

        return uniquePathsCore(x, y, curX + 1, curY) + uniquePathsCore(x, y, curX, curY + 1);
    }

    /**
     * 动态规划， 算法复杂度O(xy)，空间复杂度O(xy)
     * 规定f(1,1) = 1
     * f(2,2) = f(1,2) + f(2,1) = 2
     * f(3,2) = f(2,2) + f(3,1) = 3
     * f(3,3) = f(3,2) + f(2,3) = f(3,2) + f(1,3) + f(2,2) = 3 + 1 + 2 = 6
     * ...
     * f(x,y) = f(x-1,y) + f(x,y-1)
     */
    public int uniquePaths2(int x, int y) {

        if (x < 1 || y < 1) {
            return 0;
        }

        int[][] dp = new int[x][y];

        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                if (i == x - 1 || j == y - 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }

}
