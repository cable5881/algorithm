package com.lqb.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。
 * 所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。
 * 如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，
 * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 *
 * 示例 2:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出: 1
 *
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 *
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class FriendCircles {

    @Test
    public void test() {
        //1
        System.out.println(findCircleNum2(new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1},
        }));

        //2
        System.out.println(findCircleNum2(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        }));
    }

    /**
     * @author liqibo
     * @date 2019/10/11 16:23
     * @description 深度优先算法，每个格子访问一次，算法复杂度O(col * row)，空间复杂度O(row)
     */
    public int findCircleNum(int[][] M) {

        int[] f = new int[M.length];

        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (f[i] != 0) {
                continue;
            }
            findF(M, f, i);
            count++;
        }

        return count;
    }

    private void findF(int[][] M, int[] f, int i) {
        if (f[i] != 0) {
            return;
        }

        f[i] = -1;
        for (int j = 0; j < M[i].length; j++) {
            if (i == j || M[i][j] == 0) {
                continue;
            }

            findF(M, f, j);
        }

    }

    /**
     * @author liqibo
     * @date 2019/10/11 16:29
     * @description 广度优先搜索, 算法复杂度O(n²)，空间复杂度O(row * 2)
     */
    public int findCircleNum2(int[][] M) {

        int[] f = new int[M.length];
        LinkedList<Integer> q = new LinkedList<>();

        int count = 0;
        int i = 0;

        while (i < M.length) {
            if (q.isEmpty()) {
                while (i < f.length && f[i] == -1) {
                    i++;
                }

                if (i >= M.length) {
                    return count;
                }
                count++;
                q.addLast(i++);
            }

            int next = q.removeLast();
            f[next] = -1;
            for (int j = 0; j < M.length; j++) {
                if (j == next || M[next][j] == 0 || f[j] == -1) {
                    continue;
                }
                q.addLast(j);
            }

        }

        return count;
    }

    /**
     * @author liqibo
     * @date 2019/10/11 17:54
     * @description 官网的广度优先搜索
     */
    public int findCircleNum3(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue< Integer > queue = new LinkedList < > ();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0) {
                            queue.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

}
