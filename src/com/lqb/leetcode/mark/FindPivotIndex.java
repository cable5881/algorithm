package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 示例 1:
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 *
 * 示例 2:
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 *
 * 说明:
 * nums 的长度范围为 [0, 10000]。
 * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindPivotIndex {

    @Test
    public void test() {
        System.out.println(pivotIndex3(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(pivotIndex3(new int[]{1, 2, 3}));
        System.out.println(pivotIndex3(new int[]{2, 2, 1, 4}));
        System.out.println(pivotIndex3(new int[]{-1, -1, -1, -1, -1, 0}));
        System.out.println(pivotIndex3(new int[]{-1, -1, -1, 0, 1, 1}));
    }

    /**
     * 错误解法，因为可能含有负数，左边比右边小并不一定是左边应该向右累加，反而这样是越来越小了
     * 如{-1,-1,-1,-1,0}
     */
    public int pivotIndex(int[] nums) {

        if (nums == null || nums.length < 3) {
            return -1;
        }

        int leftCount = nums[0];
        int rightCount = nums[nums.length - 1];
        int l = 1;
        int r = nums.length - 2;

        while (l < r) {
            if (leftCount < rightCount) {
                leftCount += nums[l++];
            } else {
                rightCount += nums[r--];
            }
        }

        if (leftCount == rightCount) {
            return l;
        }

        return -1;
    }


    /**
     * 暴力法，时间复杂度O(n ^ 2)
     */
    public int pivotIndex2(int[] nums) {

        if (nums == null || nums.length < 1) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            int leftCount = 0;
            int rightCount = 0;

            for (int j = 0; j < i; j++) {
                leftCount += nums[j];
            }

            for (int j = nums.length - 1; j > i; j--) {
                rightCount += nums[j];
            }

            if (leftCount == rightCount) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 动态规划，时间复杂度O(n)，空间复杂度O(n)
     */
    public int pivotIndex3(int[] nums) {

        if (nums == null || nums.length < 1) {
            return -1;
        }

        int[] leftCount = new int[nums.length];
        int[] rightCount = new int[nums.length];

        leftCount[0] = nums[0];
        rightCount[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            leftCount[i] = leftCount[i - 1] + nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            rightCount[i] = rightCount[i + 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int l = i - 1 < 0 ? 0 : leftCount[i - 1];
            int r = i + 1 >= nums.length ? 0 : rightCount[i + 1];
            if (l == r) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 双指针法，时间复杂度O(n), 空间复杂度O(1)
     * 先计算一个总数，然后遍历数组，左边从0开始累加，右边则开始递减
     */
    public int pivotIndex4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int r = 0;
        for (int num : nums) {
            r += num;
        }
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            r -= nums[i];
            if (l == r) {
                return i;
            }
            l += nums[i];
        }
        return -1;
    }

}
