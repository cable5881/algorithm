package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * <p>
 * The minimum path sum from top to bottom is11(i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {
    private int min = Integer.MAX_VALUE;

    public int minimumTotal1(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() < 1) {
            return 0;
        }
        minimumTotalCore1(triangle, new LinkedList<>(), 0, 0);
        return min;
    }

    public int minimumTotal2(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() < 1) {
            return 0;
        }
        minimumTotalCore2(triangle, 0, 0, 0);
        return min;
    }

    private void minimumTotalCore1(ArrayList<ArrayList<Integer>> triangle, LinkedList<Integer> path, int cur, int depth) {
        if (depth >= triangle.size()) {
            setMin(path);
            return;
        }

        path.add(triangle.get(depth).get(cur));
        minimumTotalCore1(triangle, path, cur, depth + 1);
        minimumTotalCore1(triangle, path, cur + 1, depth + 1);
        path.removeLast();
    }

    private void minimumTotalCore2(ArrayList<ArrayList<Integer>> triangle, int tempSum, int cur, int depth) {
        if (depth < triangle.size()) {
            int leftSum = triangle.get(depth).get(cur) + tempSum;
            int rightSum = triangle.get(depth).get(cur) + tempSum;
            minimumTotalCore2(triangle, leftSum, cur, depth + 1);
            minimumTotalCore2(triangle, rightSum, cur + 1, depth + 1);
        } else if (tempSum < min) {
            min = tempSum;
        }
    }

    private void setMin(LinkedList<Integer> path) {
        int sum = 0;
        for (int i : path) {
            sum += i;
        }
        if (sum < min) {
            min = sum;
        }
    }

    ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();

    @Before
    public void init() {
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

        triangle.add(depth1);
        triangle.add(depth2);
        triangle.add(depth3);
        triangle.add(depth4);
        triangle.add(depth5);
    }

    @Test
    public void test1() {
        Assert.assertEquals(17, minimumTotal1(triangle));
    }

    @Test
    public void test2() {
        Assert.assertEquals(17, minimumTotal2(triangle));
    }
}
