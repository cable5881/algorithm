package com.lqb.leetcode;

import org.junit.Test;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SearchA2dMatrix_ii {

    @Test
    public void test() {
        SearchA2dMatrix_ii demo = new SearchA2dMatrix_ii();
        int[][] matrix = new int[][]{
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(demo.searchMatrix(new int[][]{}, 13));
        System.out.println(demo.searchMatrix(matrix, 13));
        System.out.println(demo.searchMatrix(matrix, 18));
        System.out.println(demo.searchMatrix(matrix, 30));
        System.out.println(demo.searchMatrix(matrix, 2));
        System.out.println(demo.searchMatrix(matrix, 20));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length < 1) {
            return false;
        }

        int i = matrix[0].length - 1;
        int j = 0;

        while (i >= 0 && j < matrix.length) {
            if (matrix[j][i] > target) {
                i--;
            } else if (matrix[j][i] < target) {
                j++;
            } else {
                return true;
            }
        }

        return false;
    }

}
