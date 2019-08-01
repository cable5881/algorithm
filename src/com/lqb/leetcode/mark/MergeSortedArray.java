package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MergeSortedArray {

    @Test
    public void test1() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2,5,6};
        merge2(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 自己的解法：时间复杂度O(n + m)， 空间复杂度O(m)
     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] merge = new int[nums1.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merge[k++] = nums1[i++];
            } else {
                merge[k++] = nums2[j++];
            }
        }

        while (i < m) {
            merge[k++] = nums1[i++];
        }

        while (j < n) {
            merge[k++] = nums2[j++];
        }

        for (int l = 0; l < k; l++) {
            nums1[l] = merge[l];
        }
    }

    /**
     * 官方的解法：时间复杂度O(n + m)， 空间复杂度O(1)
     * 从后开始遍历，最后只要将nums2复制到nums1就行了。
     * 为什么只需要复制nums2的呢？
     * 假设nums1还有剩下的，nums2已经复制完了，说明nums2[0] > nums1[p1 + 1]，剩下的p1 + 1个nums1元素已经在nums1里面了
     **/
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }

        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

}
