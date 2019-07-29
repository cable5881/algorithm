package com.lqb.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个整数数组和一个整数k，
 * 判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ContainsDuplicate_ii {

    @Test
    public void test() {
        System.out.println(containsNearbyDuplicate2(new int[]{1,2,3,1}, 3));
        System.out.println(containsNearbyDuplicate2(new int[]{1,0,1,1}, 1));
        System.out.println(containsNearbyDuplicate2(new int[]{1,2,3,1,2,3}, 2));
    }

    /**
     * @description 自己的解法：用Map记录最近的一次下标，然后用遍历的当前的下标减去最近的一次下标
     * 算法复杂度O(n)，空间复杂度O(n)
     * @author liqibo
     * @date 2019/7/25 10:04
     **/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }

    /**
     * @description 官方解法：滑动窗口
     * @author liqibo
     * @date 2019/7/25 10:05
     **/
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }


}
