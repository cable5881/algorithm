package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 *
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RotateArray {

    @Test
    public void test() {
        RotateArray demo = new RotateArray();

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        demo.rotate(nums, 1);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        demo.rotate(nums1, 2);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
        demo.rotate(nums2, 3);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        demo.rotate(nums3, 4);
        System.out.println(Arrays.toString(nums3));

        System.out.println("######################下面是偶数长度########################");

        int[] nums4 = {1, 2, 3, 4, 5, 6};
        demo.rotate(nums4, 2);
        System.out.println(Arrays.toString(nums4));

        int[] nums5 = {1, 2, 3, 4, 5, 6};
        demo.rotate2(nums5, 3);
        System.out.println(Arrays.toString(nums5));

        int[] nums6 = {1, 2, 3, 4, 5, 6};
        demo.rotate(nums6, 4);
        System.out.println(Arrays.toString(nums6));

        int[] nums7 = {1, 2, 3, 4, 5, 6};
        demo.rotate(nums7, 5);
        System.out.println(Arrays.toString(nums7));
    }

    @Test
    public void test2() {
        System.out.println("######################下面是偶数长度########################");
        RotateArray demo = new RotateArray();

        int[] nums4 = {1, 2, 3, 4, 5, 6};
        demo.rotate2(nums4, 2);
        System.out.println(Arrays.toString(nums4));

        //int[] nums5 = {1, 2, 3, 4, 5, 6};
        //demo.rotate2(nums5, 3);
        //System.out.println(Arrays.toString(nums5));
        //
        //int[] nums6 = {1, 2, 3, 4, 5, 6};
        //demo.rotate2(nums6, 4);
        //System.out.println(Arrays.toString(nums6));
        //
        //int[] nums7 = {1, 2, 3, 4, 5, 6};
        //demo.rotate2(nums7, 5);
        //System.out.println(Arrays.toString(nums7));
    }

    /**
     * 自己的解法：
     * ①将nums[i] 转移到nums[(i + k) % nums.length]上
     * 第一次做错了，因为没有考虑数组长度为偶数的情况，因为nextI会重复
     * 所以当我们发现nextI重复了，就让它自增
     **/
    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return;
        }

        int firstStart = 0;
        int next = 0;
        int prev = nums[0];
        for (int i = 0; i < nums.length; i++) {
            next = (next + k) % nums.length;
            int temp = nums[next];
            nums[next] = prev;
            prev = temp;

            if (next == firstStart) {
                firstStart = ++next;
                prev = nums[next];
            }
        }
    }

    /**
     * 官方解法1：使用环状。和我的解法类似，但是leetcode上跑的比我快，为啥？
     **/
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

}
