package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SquaresOfSortedArray {

    @Test
    public void test() {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4,-1,0,3,10})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-7,-3,2,3,11})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-5,-3,-2,-1})));
    }

    /**
     * 错误解法，无法处理[-5,-3,-2,-1]，求出来是[1,9,4,25]
     * @param nums
     * @return
     */
    public int[] sortedSquares_Wrong(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return nums;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int l = Math.abs(nums[0]);
            int r = Math.abs(nums[i]);
            if (r > l) {
                nums[i] = r * r;
            } else {
                nums[0] = nums[i];
                nums[i] = l * l;
            }
        }

        return nums;
    }

    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return nums;
        }

        int[] res = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        int i = nums.length - 1;
        while (l <= r) {
            int a = nums[l] * nums[l];
            int b = nums[r] * nums[r];
            if (a > b) {
                res[i] = a;
                l++;
            } else {
                res[i] = b;
                r--;
            }
            i--;
        }
        return res;
    }

}
