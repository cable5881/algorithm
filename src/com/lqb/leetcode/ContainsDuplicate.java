package com.lqb.leetcode;

import org.junit.Test;

import java.util.HashSet;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ContainsDuplicate {

    @Test
    public void test() {
        System.out.println(containsDuplicate(new int[]{1,2,3,4}));
        System.out.println(containsDuplicate(new int[]{}));
        System.out.println(containsDuplicate(new int[]{1,2,3,1}));
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
        }

        return false;
    }

}
