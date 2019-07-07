package com.lqb.leetcode;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidSudoku {


    /**
     * 自己的解法
     *    暴力法：对于一个空格char[i][j]，检查char[0~8][j]、char[i][0~8]、char[i/3 ~ i/3+2][j/3 ~ j/3+2]是否存在char[i][j]
     *
     * 官方的解法：
     *    创建9的行Map，9个列Map，9个大方格Map，每次遍历一个格子就去这个三个Map中查找是否存在。注意大格子定位哪个Map是(i / 3) * 3 + j / 3
     */
    public boolean isValidSudoku(char[][] board) {
        return false;
    }





}
