package com.lqb.leetcode.mark.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 *  
 * <p>
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 * 进阶：
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Triangle {

    @Test
    public void test2() {
        ArrayList<Integer> depth1 = new ArrayList<>();
        ArrayList<Integer> depth2 = new ArrayList<>();
        ArrayList<Integer> depth3 = new ArrayList<>();
        ArrayList<Integer> depth4 = new ArrayList<>();
        ArrayList<Integer> depth5 = new ArrayList<>();

        depth1.add(2);

        depth2.add(3);
        depth2.add(4);

        depth3.add(6);
        depth3.add(5);
        depth3.add(7);

        depth4.add(4);
        depth4.add(1);
        depth4.add(8);
        depth4.add(3);

        depth5.add(9);
        depth5.add(9);
        depth5.add(9);
        depth5.add(9);
        depth5.add(1);
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
        triangle.add(depth1);
        triangle.add(depth2);
        triangle.add(depth3);
        triangle.add(depth4);
        triangle.add(depth5);
        Assert.assertEquals(17, minimumTotal(triangle));
        Assert.assertEquals(17, minimumTotal2(triangle));
        Assert.assertEquals(17, minimumTotal3(triangle));
    }

    private int min = Integer.MAX_VALUE;

    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() < 1) {
            return 0;
        }
        minimumTotalCore(triangle, 0, 0, 0);
        return min;
    }

    private void minimumTotalCore(ArrayList<ArrayList<Integer>> triangle, int tempSum, int cur, int depth) {
        if (depth < triangle.size()) {
            int leftSum = triangle.get(depth).get(cur) + tempSum;
            int rightSum = triangle.get(depth).get(cur) + tempSum;
            minimumTotalCore(triangle, leftSum, cur, depth + 1);
            minimumTotalCore(triangle, rightSum, cur + 1, depth + 1);
        } else if (tempSum < min) {
            min = tempSum;
        }
    }

    /**
     * 动态规划1，使用一个n*n的数组缓存所有距离
     */
    public int minimumTotal2(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() < 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int[][] tmpSum = new int[triangle.size()][triangle.size()];
        tmpSum[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            //这样写太麻烦了
            // for (int j = 0; j < triangle.get(i).size(); j++) {
            //     int cur = triangle.get(i).get(j);
            //     int left = tmpSum[i - 1][j > 0 ? j - 1 : 0];
            //     int right = tmpSum[i - 1][j < triangle.get(i).size() - 1 ? j : j - 1];
            //     tmpSum[i][j] += cur + Math.min(right, left);
            // }

            tmpSum[i][0] = triangle.get(i).get(0) + tmpSum[i - 1][0];
            for (int j = 1; j < i; j++) {
                tmpSum[i][j] += triangle.get(i).get(j) + Math.min(tmpSum[i - 1][j - 1], tmpSum[i - 1][j]);
            }
            tmpSum[i][i] = triangle.get(i).get(i) + tmpSum[i - 1][i - 1];
        }

        for (int sum : tmpSum[triangle.size() - 1]) {
            if (sum < min) {
                min = sum;
            }
        }

        return min;
    }

    /**
     * 动态规划2，仅使用一个n的数组缓存所有距离
     * 动态方程:f[i][j] = c[i][j] + min(f[i - 1][j - 1], f[i - 1][j])
     * 推进时从底向上
     * 2
     * 3   4
     * 6   5   7
     * 每一层的最短距离都是上一层的最短累加的
     */
    public int minimumTotal3(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        int[] mini = new int[n + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                mini[i] = triangle.get(i).get(j) + Math.min(mini[j], mini[j + 1]);
            }
        }
        return mini[0];
    }
}
