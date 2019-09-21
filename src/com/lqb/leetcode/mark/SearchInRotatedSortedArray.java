package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInRotatedSortedArray {

    @Test
    public void test() {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
        System.out.println(search(new int[]{5, 1, 2, 3, 4}, 1));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 2));
    }

    /**
     * 自己的解法，情况太多了，不好描述
     */
    public int search(int[] a, int target) {

        if (a == null || a.length <= 0) {
            return -1;
        }

        int start = 0;
        int end = a.length - 1;
        int mid = (start + end) / 2;

        while (start <= end) {

            if (a[mid] == target) {
                return mid;
            }

            if (a[start] > a[end]) {
                if (target >= a[start]) {
                    if (target > a[mid] && a[mid] > a[start]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                } else if (target < a[mid] && a[mid] < a[end]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (a[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
        }

        return -1;
    }

    /**
     * 网友解法：
     *
     * 题目要求O（logN）的时间复杂度，基本可以断定本题是需要使用二分查找，怎么分是关键
     * 由于题目说数字了无重复，举个例子
     * 1 2 3 4 5 6 7 可以大致分为两类,
     * 第一类 2 3 4 5 6 7 1 这种，也就是nums[start] <= nums[mid]。此例子中就是2 <= 5
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]。则在前半部分找，否则去后半部分找。
     *
     * 第二类 6 7 1 2 3 4 5 这种，也就是nums[start] > nums[mid]。此例子中就是6 > 2
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。
     *
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-bai-liao-9983de-javayong-hu-by-reedfan/
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start <= end) {
            mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            //前半部分有序,注意此处用小于等于，否则不能通过用例[3,1]，1。 输出了-1，实际应该是1
            if (nums[start] <= nums[mid]) {
                //target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;

    }
}
