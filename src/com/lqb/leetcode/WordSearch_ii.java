package com.lqb.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class WordSearch_ii {

    @Test
    public void test() {
        char[][] b1 = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(findWords(b1, new String[]{"ABCCED", "EESC", "SEE"}));
        System.out.println(findWords(b1, new String[]{"ABCB"}));
        System.out.println(findWords(b1, new String[]{"BFDAES"}));
    }

    /**
     * 方法一：dfs
     */
    public List<String> findWords(char[][] board, String[] words) {
       HashSet<String> res = new HashSet<>();
        if (words == null || words.length <= 0) {
            return Collections.emptyList();
        }

        int[][] visit = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (String word : words) {
                    if (res.size() == words.length) {
                        return new ArrayList<>(res);
                    }
                    if (res.contains(word)) {
                        continue;
                    }
                    if (exist(board, visit, j, i, word.toCharArray(), 0)) {
                        res.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean exist(char[][] board, int[][] visit, int col, int row, char[] word, int start) {
        if (start >= word.length) {
            return true;
        }

        if (col >= board[0].length || col < 0) {
            return false;
        }

        if (row >= board.length || row < 0) {
            return false;
        }

        if (board[row][col] != word[start] || visit[row][col] == 1) {
            return false;
        }

        visit[row][col] = 1;
        boolean up = exist(board, visit, col, row - 1, word, start + 1);
        boolean down = exist(board, visit, col, row + 1, word, start + 1);
        boolean left = exist(board, visit, col - 1, row, word, start + 1);
        boolean right = exist(board, visit, col + 1, row, word, start + 1);
        visit[row][col] = 0;
        if (up || down || left || right) {
            return true;
        }

        return false;
    }

}
