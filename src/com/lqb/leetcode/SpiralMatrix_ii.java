package com.lqb.leetcode;

import org.junit.Test;

/**
 * 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 输入：n = 1
 * 输出：[[1]]
 */
public class SpiralMatrix_ii {

    @Test
    public void test() {
        print(generateMatrix(1));
        print(generateMatrix(2));
        print(generateMatrix(3));
        print(generateMatrix(4));
        print(generateMatrix(5));
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int start = 1;
        for (int i = 0; i < n; i++) {
            int len = n - i * 2;
            //长度为1特殊处理
            if (len == 1) {
                res[i][i] = start;
                break;
            }
            //每次只遍历len - 1
            start = right(res, i, i, len - 1, start);
            start = down(res, i + len - 1, i, len - 1, start);
            start = left(res, i + len - 1, i + len - 1, len - 1, start);
            start = up(res, i, i + len - 1, len - 1, start);
        }
        return res;
    }

    public int right(int[][] res, int x, int y, int n, int start) {
        for (int i = 0; i < n; i++) {
            res[y][x + i] = start++;
        }
        return start;
    }

    public int down(int[][] res, int x, int y, int n, int start) {
        for (int i = 0; i < n; i++) {
            res[y + i][x] = start++;
        }
        return start;
    }

    public int left(int[][] res, int x, int y, int n, int start) {
        for (int i = 0; i < n; i++) {
            res[y][x - i] = start++;
        }
        return start;
    }

    public int up(int[][] res, int x, int y, int n, int start) {
        for (int i = 0; i < n; i++) {
            res[y - i][x] = start++;
        }
        return start;
    }

    private void print(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            System.out.print("[");
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j]+",");
            }
            System.out.print("],");
        }
        System.out.println();
    }
}
