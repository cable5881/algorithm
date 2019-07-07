package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 *
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 *
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SetMatrixZeroes_1 {

    public void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    @Test
    public void test() {
        SetMatrixZeroes_1 demo = new SetMatrixZeroes_1();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        demo.setZeroes3(matrix);
        print(matrix);
    }

    @Test
    public void test2() {
        SetMatrixZeroes_1 demo = new SetMatrixZeroes_1();
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        demo.setZeroes3(matrix);
        print(matrix);
    }

    /**
     * 自己的解法1, 空间复杂度O(m + n)
     */
    public void setZeroes(int[][] matrix) {
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //注意这里不是matrix[i][j] == 0 && !rows.contains(i) && !cols.contains(j)
                //因为如果一行有2个0, 那么第二个0就不会加入到cols中了, 导致结果错误
                //另外就是也不能在这个if条件中完成清0的操作, 这会让本来是0的元素在下次无法扫描时无法添加到待清0的行列set中
                //实际上可以直接matrix[i][j] == 0, 毕竟set有去重的功能
                if (matrix[i][j] == 0 && (!rows.contains(i) || !cols.contains(j))) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        setZeroes(matrix, rows, cols);
    }

    /**
     * 包含了两种清0方式, 显然第二种(官网的)更好
     */
    private void setZeroes(int[][] matrix, Set<Integer> rows, Set<Integer> cols) {
        //rows.forEach(row -> {
        //    for (int i = 0; i < matrix[row].length; i++) {
        //        matrix[row][i] = 0;
        //    }
        //});
        //
        //cols.forEach(col -> {
        //    for (int i = 0; i < matrix.length; i++) {
        //        matrix[i][col] = 0;
        //    }
        //});

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    /**
     * 自己的解法2, 空间复杂度O(1), 但是要求原始数组中的元素不为-1. 另外就是时间复杂度相比自己的解法1更差, 最差的情况为O(n³)
     * 但是已经接近最佳解法了, 不高效的地方在于我们会重复对同一行或者一列赋零。我们可以推迟对行和列赋零的操作。
     */
    public void setZeroes2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    setZeroes2(matrix, i, j);
                }
            }
        }
    }

    private void setZeroes2(int[][] matrix, int row, int col) {
        matrix[row][col] = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] != -1) {
                matrix[row][i] = 0;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] != -1) {
                matrix[i][col] = 0;
            }
        }
    }

    /**
     * 官方解法
     */
    public void setZeroes3(int[][] matrix) {
        boolean isCol = false;

        //判断第一列是否要全部置0, 如果需要则isCol为true
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                isCol = true;
            }
        }

        //判断第一行是否要全部置0, 如果需要则matrix[0][0]为0
        if (matrix[0][0] != 0) {
            for (int i = 1; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    matrix[0][0] = 0;
                }
            }
        }

        //判断数组中是否存在matrix[i][j] = 0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //将列置为0
        //for (int i = 1; i < matrix[0].length; i++) {
        //    if (matrix[0][i] == 0) {
        //        for (int j = 1; j < matrix.length; j ++) {
        //            matrix[j][i] = 0;
        //        }
        //    }
        //}

        //将行置为0
        //for (int i = 1; i < matrix.length; i++) {
        //    if (matrix[i][0] == 0) {
        //        for (int j = 1; j < matrix[0].length; j ++) {
        //            matrix[i][j] = 0;
        //        }
        //    }
        //}

        //一个循环置为0, 比上面的分行列要好
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //将第一行置为0
        if (matrix[0][0] == 0) {
            for (int i = 1; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        //将第一列置为0
        if (isCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
