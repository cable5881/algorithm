package com.lqb.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations {

    @Test
    public void test() {
        System.out.println(permute(new int[]{1,2,3}));
    }

    public List<List<Integer>> permute(int[] nums) {

        if (nums == null) {
            return Collections.emptyList();
        }

        LinkedList<List<Integer>> res = new LinkedList<>();
        permute(nums, 0, res, new LinkedList<>());
        return res;
    }

    private void permute(int[] nums, int cur, List<List<Integer>> res, LinkedList<Integer> temp) {

        if (cur >= nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = cur; i < nums.length; i++) {
            swap(nums, cur, i);
            temp.addLast(nums[cur]);
            permute(nums, cur + 1, res, temp);
            temp.removeLast();
            swap(nums, cur, i);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
