package com.lqb.leetcode;

import org.junit.Test;

/**
 * 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *  
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumSizeSubarraySum {

    @Test
    public void test() {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1,}));
        System.out.println(minSubArrayLen(1, new int[]{1, 1, 1, 1, 1, 1, 1,}));
        System.out.println(minSubArrayLen(7, new int[]{1, 1, 1, 1, 1, 1, 1,}));
        System.out.println(minSubArrayLen(8, new int[]{1}));
        System.out.println(minSubArrayLen(1, new int[]{1}));
    }

    public int minSubArrayLen(int target, int[] nums) {

        int sum = nums[0];
        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;

        while (l < nums.length && r < nums.length) {
            if (sum >= target) {
                minLen = Math.min(minLen, r - l + 1);

                sum -= nums[l];
                l++;
            } else {
                r++;
                if (r < nums.length) {
                    sum += nums[r];
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
