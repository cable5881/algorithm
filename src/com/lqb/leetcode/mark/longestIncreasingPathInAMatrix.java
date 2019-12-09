package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2:
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class longestIncreasingPathInAMatrix {

    @Test
    public void test1() {
        //4
        System.out.println(new longestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        }));

        //4
        System.out.println(new longestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        }));

        //4
        System.out.println(new longestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{
                {7, 7, 5},
                {2, 4, 6},
                {8, 2, 0}
        }));
    }

    //自己的解法，动态规划，使用数组保存到过的地方的最路径，后续访问到了直接返回其路径
    //递归算法会不会最终绕回原点？不会，因为路径肯定是一个比一个大的，回到原点的前一个路径值肯定比原点的大

    //左移29位
    //private final int OFFSET = 29;
    //表示已经访问过
    //private final int VISITED_FLAG = 1 << OFFSET;

    int longestPath = Integer.MIN_VALUE;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //注意这里最后一位传的是Integer.MIN_VALUE而不是0，因为很有可能matrix[row][col]就是0，导致后面判断错误
                fun(matrix, dp, i, j, Integer.MIN_VALUE);
            }
        }
        return longestPath;
    }

    private int fun(int[][] matrix, int[][] dp, int row, int col, int last) {
        if (col >= matrix[0].length || col < 0) {
            return 0;
        }
        if (row >= matrix.length || row < 0) {
            return 0;
        }
        //if (isVisit(dp, row, col)) {
        //    return 0;
        //}
        if (matrix[row][col] <= last) {
            return 0;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int pathLen = 1;

        //toggleVisited(dp, row, col);
        int rightPathLen = fun(matrix, dp, row, col + 1, matrix[row][col]);
        int downPathLen = fun(matrix, dp, row + 1, col, matrix[row][col]);
        int leftPathLen = fun(matrix, dp, row, col - 1, matrix[row][col]);
        int upPathLen = fun(matrix, dp, row - 1, col, matrix[row][col]);
        //toggleVisited(dp, row, col);

        pathLen += max(rightPathLen, downPathLen, leftPathLen, upPathLen);
        longestPath = Math.max(longestPath, pathLen);
        dp[row][col] = pathLen;
        return pathLen;
    }

    private int max(int rightPathLen, int downPathLen, int leftPathLen, int upPathLen) {
        return Math.max(Math.max(Math.max(rightPathLen, downPathLen), leftPathLen), upPathLen);
    }

    //private void toggleVisited(int[][] dp, int row, int col) {
    //    //与第30位做异或
    //    dp[row][col] ^= VISITED_FLAG;
    //}
    //
    //private boolean isVisit(int[][] dp, int row, int col) {
    //    return ((dp[row][col] >> OFFSET) & 1) != 0;
    //}

}
