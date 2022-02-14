package com.lqb.leetcode.mark.dfs;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NQueens {

    @Test
    public void test() {
        // System.out.println(totalNQueens(1));
        // System.out.println(totalNQueens(2));
        // System.out.println(totalNQueens(3));
        System.out.println(totalNQueens(4));
        // System.out.println(totalNQueens(5));
        // System.out.println(totalNQueens(6));
        // System.out.println(totalNQueens(7));
        // System.out.println(totalNQueens(8));
        // System.out.println(totalNQueens(9));
    }

    private int cnt = 0;

    public int totalNQueens(int n) {
        if (n <= 1) {
            return n;
        }

        Set<Integer> rows = new HashSet<>(n);
        Set<Integer> pY = new HashSet<>(n);
        Set<Integer> nY = new HashSet<>(n);
        totalNQueens(n, 0, rows, pY, nY);
        return cnt;
    }

    private void totalNQueens(int n, int curCol, Set<Integer> rows, Set<Integer> pYs, Set<Integer> nYs) {
        if (curCol >= n) {
            cnt++;
            return;
        }
        //在固定列的情况下，遍历行
        for (int row = 0; row < n; row++) {
            //两根斜线，即y=x+a和y=-x+b存在一个公式能够唯一确定一根线
            //即y-x=a，y+x=b，行下标和列下标相加或者相减总是一个常数，这个常数能够唯一确定是哪根斜线
            int pY = curCol + row;
            int nY = curCol - row;
            if (rows.contains(row) || pYs.contains(pY) || nYs.contains(nY)) {
                continue;
            }
            rows.add(row);
            pYs.add(pY);
            nYs.add(nY);
            totalNQueens(n, curCol + 1, rows, pYs, nYs);
            rows.remove(row);
            pYs.remove(pY);
            nYs.remove(nY);
        }
    }
}
