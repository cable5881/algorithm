package com.lqb.leetcode;

import org.junit.Test;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargestElementInAnArray {

    @Test
    public void test() {
        KthLargestElementInAnArray demo = new KthLargestElementInAnArray();
        System.out.println(demo.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(demo.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(demo.findKthLargest(new int[]{3}, 1));
        System.out.println(demo.findKthLargest(new int[]{3, 2}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return -1;
        }

        return findKthLargest(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findKthLargest(int[] nums, int k, int start, int end) {

        int next = k;
        int curVal = nums[k];
        int left = start;
        int right = end;
        while (left < right) {
            //注意不是left < right. 假设 curVal 是最大的数, 则nums[left] <= curVal衡成立, 那么left会一直加到大于等于right, 循环就结束了
            while (left < next && nums[left] <= curVal) {
                left++;
            }
            if (left < next) {
                nums[next] = nums[left];
                next = left;
            }

            while (next < right && nums[right] >= curVal) {
                right--;
            }
            if (next < right) {
                nums[next] = nums[right];
                next = right;
            }
        }

        if (next == k) {
            return curVal;
        }

        return next < k ? findKthLargest(nums, k, next + 1, end) : findKthLargest(nums, k, start, next - 1);

    }


}
