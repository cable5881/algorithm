package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class WordSearch {

    @Test
    public void test() {
        WordSearch demo = new WordSearch();
        char[][] b1 = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(demo.exist(b1, "ABCCED"));
        System.out.println(demo.exist(b1, "SEE"));
        System.out.println(demo.exist(b1, "ABCB"));
        System.out.println(demo.exist(b1, "BFDAES"));
        System.out.println(demo.exist(b1, "EESC"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        if (word == null || word.length() == 0) {
            return false;
        }

        return exist(board, new char[board.length][board[0].length], word.toCharArray(), 0, 0, 0);
    }

    private boolean exist(char[][] board, char[][] visit, char[] chars, int row, int col, int cur) {
        if (cur >= chars.length) {
            return true;
        }

        if (col >= board[0].length) {
            return exist(board, visit, chars, row + 1, 0, cur);
        }

        if (row >= board.length) {
            return false;
        }

        visit[row][col] = 0;

        if (chars[cur] == board[row][col]) {
            visit[row][col] = 1;
            if (existOnLeftOrUp(board, visit, chars, row, col, cur + 1)) {
                return exist(board, visit, chars, row, col + 2, cur + 2);
            } else {
                return exist(board, visit, chars, row, col + 1, cur + 1);
            }
        } else if (existOnLeftOrUp(board, visit, chars, row, col, cur)) {
            return exist(board, visit, chars, row, col + 1, cur + 1);
        }

        return exist(board, visit, chars, row, col + 1, cur);
    }

    private boolean existOnLeftOrUp(char[][] board, char[][] visit, char[] chars, int row, int col, int cur) {

        if (cur >= chars.length) {
            return true;
        }

        int aCol = col - 1;
        if (aCol >= 0 && board[row][aCol] == chars[cur] && visit[row][aCol] == 0) {
            return true;
        }

        int aRow = row - 1;
        if (aRow >= 0 && board[aRow][col] == chars[cur] && visit[aRow][col] == 0) {
            return true;
        }

        return false;
    }


    enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public boolean exist2(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        if (word == null || word.length() == 0) {
            return false;
        }

        return false;
    }

    private boolean back(char[][] board, char[][] visit, char[] chars, int row, int col, int cur, Direction direction) {
        if (cur >= chars.length) {
            return true;
        }

        if (direction == Direction.UP) {
            int nextRow = row - 1;
            if (checkBoundary(board, nextRow, col) && checkVisit(board, visit, chars, nextRow, col, cur)) {
                visit(visit, nextRow, col);
                if(back(board, visit, chars, nextRow, col, cur + 1, Direction.UP)) {
                    return true;
                }
                reset(visit, nextRow, col);
            }
        }

        if (direction == Direction.RIGHT) {
            int nextCol = col + 1;
            if (checkBoundary(board, row, nextCol) && checkVisit(board, visit, chars, row, nextCol, cur)) {
                visit(visit, row, nextCol);
                if(back(board, visit, chars, row, nextCol, cur + 1, Direction.RIGHT)) {
                    return true;
                }
                reset(visit, row, nextCol);
            }
        }

        if (col >= board[0].length || row >= board.length) {
            return false;
        }

        return false;
    }

    private void visit(char[][] visit, int row, int col) {
        visit[row][col] = 1;
    }

    private void reset(char[][] visit, int row, int col) {
        visit[row][col] = 0;
    }

    private boolean checkBoundary(char[][] board, int row, int col) {
        int rows = board.length;
        int cols = board[0].length;

        return (row >= 0 && row < rows) && (col >= 0 && col <= cols);
    }

    private boolean checkVisit(char[][] board, char[][] visit, char[] chars, int row, int col, int cur) {
        return visit[row][col] == 0 && board[row][col] == chars[cur];
    }
}
