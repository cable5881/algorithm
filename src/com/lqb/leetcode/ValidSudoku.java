package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidSudoku {

    @Test
    public void test() {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assert.assertEquals(true, isValidSudoku2(board));

        board = new char[][]{
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        Assert.assertEquals(false, isValidSudoku2(board));
    }

    /**
     * 使用set的方式进行剪枝
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> cols = new HashSet<>(9);
        HashSet<String> rows = new HashSet<>(9);
        HashSet<String> blocks = new HashSet<>(9);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '.') {
                    continue;
                }
                String rowV = row + "_" + board[row][col];
                String colV = col + "_" + board[row][col];
                String blockV = (row / 3) + "_" + (col / 3) + "_" + board[row][col];
                if (rows.contains(rowV) || cols.contains(colV) || blocks.contains(blockV)) {
                    return false;
                }
                rows.add(rowV);
                cols.add(colV);
                blocks.add(blockV);
            }
        }
        return isValidSudoku(board, 0, cols, rows, blocks);
    }

    private boolean isValidSudoku(char[][] board, int col, Set<String> cols, Set<String> rows, Set<String> blocks) {
        if (col >= 9) {
            return true;
        }

        //标记一列已经全满了
        boolean isColFull = true;

        for (int row = 0; row < 9; row++) {
            if (board[row][col] != '.') {
                continue;
            }
            isColFull = false;
            for (char i = '1'; i <= '9'; i++) {
                String rowV = row + "_" + i;
                String colV = col + "_" + i;
                String blockV = (row / 3) + "_" + (col / 3) + "_" + i;
                if (rows.contains(rowV)) {
                    continue;
                }
                if (cols.contains(colV)) {
                    continue;
                }
                if (blocks.contains(blockV)) {
                    continue;
                }
                rows.add(rowV);
                cols.add(colV);
                blocks.add(blockV);
                if (isValidSudoku(board, col + 1, cols, rows, blocks)) {
                    return true;
                }
                rows.remove(rowV);
                cols.remove(colV);
                blocks.remove(blockV);
            }
        }

        if (isColFull) {
            return isValidSudoku(board, col + 1, cols, rows, blocks);
        }

        return false;
    }

    /**
     * 使用数组进行剪枝
     */
    public boolean isValidSudoku2(char[][] board) {
        int[][] cols = new int[9][9];
        int[][] rows = new int[9][9];
        int[][][] blocks = new int[3][3][9];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '.') {
                    continue;
                }
                int c = board[row][col] - '0' - 1;
                if (rows[row][c] > 0 || cols[col][c] > 0 || blocks[row / 3][col / 3][c] > 0) {
                    return false;
                }
                rows[row][c]++;
                cols[col][c]++;
                blocks[row / 3][col / 3][c]++;
            }
        }
        return isValidSudoku2(board, 0, cols, rows, blocks);
    }

    private boolean isValidSudoku2(char[][] board, int col, int[][] cols, int[][] rows, int[][][] blocks) {
        if (col >= 9) {
            return true;
        }

        boolean isColFull = true;

        for (int row = 0; row < 9; row++) {
            if (board[row][col] != '.') {
                continue;
            }
            isColFull = false;
            for (char i = '1'; i <= '9'; i++) {
                int c = i - '0' - 1;
                if (rows[row][c] > 0 || cols[col][c] > 0 || blocks[row / 3][col / 3][c] > 0) {
                    continue;
                }
                rows[row][c]++;
                cols[col][c]++;
                blocks[row / 3][col / 3][c]++;

                if (isValidSudoku2(board, col + 1, cols, rows, blocks)) {
                    return true;
                }
                rows[row][c]--;
                cols[col][c]--;
                blocks[row / 3][col / 3][c]--;
            }
        }

        if (isColFull) {
            return isValidSudoku2(board, col + 1, cols, rows, blocks);
        }

        return false;
    }

}
