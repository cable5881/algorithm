package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class NumberOfIslands {

    @Test
    public void test1() {
        NumberOfIslands demo = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println(demo.numIslands(grid));
    }

    @Test
    public void test2() {
        NumberOfIslands demo = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
        System.out.println(demo.numIslands(grid));
    }

    /**
     * 深度优先搜索 dfs
     * 时间复杂度 : O(M×N)，其中 MM 和 NN 分别为行数和列数。
     * 空间复杂度 : O(M×N), 包括一个M×N的数组和递归栈空间
     *
     * 可以把grid[i][j]标记为0表示访问过, 这样可以不需要一个数组来记录访问，空间复杂度只剩下递归的栈开辟
     **/
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        boolean[][] lands = new boolean[grid.length][grid[0].length];
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !lands[i][j]) {
                    numIslandsCore(grid, i, j, lands);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private void numIslandsCore(char[][] grid, int row, int col, boolean[][] lands) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }

        if (grid[row][col] != '1' || lands[row][col]) {
            return;
        }

        lands[row][col] = true;

        numIslandsCore(grid, row, col + 1, lands);
        numIslandsCore(grid, row + 1, col, lands);
        //注意下面两个遍历， 还是有必要向左和向上的（容易忘记）
        numIslandsCore(grid, row, col - 1, lands);
        numIslandsCore(grid, row - 1, col, lands);
    }

    /**
     * 广度优先搜索 bfs
     * 时间复杂度 : O(M×N)，其中 MM 和 NN 分别为行数和列数。
     * 空间复杂度 : O(min(M,N))，在最坏的情况下（全部为陆地），队列的大小可以达到 min(M，N)。
     *
     * 用了 i * col + j 来唯一标记二维数组的某一个单元格，其中 i 是某行，j是某列，col是总列数
     **/
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

}
