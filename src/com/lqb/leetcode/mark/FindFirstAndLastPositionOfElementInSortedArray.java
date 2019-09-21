package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * <p>
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    @Test
    public void test() {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 5)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{2, 2}, 2)));
    }

    /**
     * 自己的解法：需要多次二次查找，时间复杂度最差为nlog(n)
     */
    public int[] searchRange(int[] nums, int target) {

        int[] ans = new int[]{-1, -1};

        if (nums == null || nums.length < 1) {
            return ans;
        }

        //找出某个目标ti
        int tMid = findNextTarget(nums, target, 0, nums.length - 1);
        if (tMid == -1) {
            return ans;
        }

        ans[0] = tMid;
        ans[1] = tMid;

        //找出t0
        int tLeft = tMid;
        while ((tLeft = findNextTarget(nums, target, 0, tLeft - 1)) != -1) {
            ans[0] = tLeft;
        }

        //找出tn
        //判断条件曾经写成 ： (tRight = findNextTarget(nums, target, tMid + 1, tRight)) != -1
        int tRight = tMid;
        while ((tRight = findNextTarget(nums, target, tRight + 1, nums.length - 1)) != -1) {
            ans[1] = tRight;
        }

        return ans;
    }

    private int findNextTarget(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 官方解法，只需要调用两次二分查找
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        //没有找到
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) -1;

        return targetRange;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                //这个加1很关键，即使找到了target，还能继续向右查找。去掉 + 1会死循环
                lo = mid+1;
            }
        }

        return lo;
    }

}
