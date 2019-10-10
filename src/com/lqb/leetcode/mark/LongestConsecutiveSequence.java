package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 **/
public class LongestConsecutiveSequence {

    @Test
    public void test() {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * @author liqibo
     * @date 2019/10/10 18:23
     * @description 官方解法：用Set保存数组，这样遍历数组时我们可以知道当前元素是否存在下一个连续序列
     * 时间复杂度和空间复杂度都是O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int ans = 1;

        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            int tmp = 1;
            //这个判断非常重要，可以让下面的循环中我们每次都从连续序列中最小的开始遍历
            //如100, 4, 200, 1, 3, 2中，4会被过滤，从1开始遍历，这样每个元素最多访问2次，时间复杂度维持在O(n)
            if (!set.contains(nums[i] - 1)) {
                int num = nums[i];
                while (set.contains(num + 1)) {
                    tmp++;
                    num++;
                }
                ans = Math.max(tmp, ans);
            }
        }
        return ans;
    }
}
