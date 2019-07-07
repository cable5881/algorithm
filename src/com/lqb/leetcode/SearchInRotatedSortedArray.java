package com.lqb.leetcode;

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
        SearchInRotatedSortedArray demo = new SearchInRotatedSortedArray();
        System.out.println(demo.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
        System.out.println(demo.search(new int[]{5, 1, 2, 3, 4}, 1));
        System.out.println(demo.search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 2));
    }

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

}
