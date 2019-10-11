package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * <p>
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。
 * 注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * <p>
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MaxAreaOfIsland {

    @Test
    public void test() {
        //System.out.println(maxAreaOfIsland(new int[][]{
        //        {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}
        //}));
        System.out.println(maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        }));
    }

    private void print(int[][] grids) {
        for (int[] grid : grids) {
            System.out.println(Arrays.toString(grid));
        }
        System.out.println();
    }

    /**
     * @author liqibo
     * @date 2019/10/11 15:58
     * @description 深度优先搜索，时间复杂度O(m * n)，每个格子遍历一次。空间复杂度O(1)
     */
    public int maxAreaOfIsland(int[][] grid) {

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int size = maxAreaOfIsland(grid, i, j, 0);
                    max = Math.max(size, max);
                }
            }
        }

        return max;
    }

    private int maxAreaOfIsland(int[][] grid, int row, int col, int size) {
        if (row >= grid.length || row < 0
                || col >= grid[0].length || col < 0
                || grid[row][col] != 1) {
            return size;
        }

        grid[row][col] = -1;
        int right = size + 1;
        int down = maxAreaOfIsland(grid, row, col + 1, right);
        int left = maxAreaOfIsland(grid, row + 1, col, down);
        int up = maxAreaOfIsland(grid, row, col - 1, left);
        return maxAreaOfIsland(grid, row - 1, col, up);
    }
}
