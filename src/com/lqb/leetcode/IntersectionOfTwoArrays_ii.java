package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 *
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 进阶:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？ 使用双指针将两个列表中共同的元素抠下来，因为已经排序，所以遇到不同元素时数值小的那个列表的指针向前移动
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntersectionOfTwoArrays_ii {

    @Test
    public void test1() {
        System.out.println(Arrays.toString(intersect(new int[] {1,2,2,1}, new int[] {2,2})));
        System.out.println(Arrays.toString(intersect(new int[] {4,9,5}, new int[] {9,4,9,8,4})));
        System.out.println(Arrays.toString(intersect(new int[] {4,9,5}, new int[] {8,1,2})));
        System.out.println(Arrays.toString(intersect(new int[] {4}, new int[] {4,4})));
        System.out.println(Arrays.toString(intersect(new int[] {4,4}, new int[] {4})));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] temp;
        if (nums1.length > nums2.length) {
            temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        Map<Integer, Integer> map = new HashMap<>(nums1.length);

        for (int num : nums1) {
            Integer cnt = map.getOrDefault(num, 0) + 1;
            map.put(num, cnt);
        }

        int[] ans = new int[nums1.length];
        int size = 0;

        for (int num : nums2) {
            if (!map.containsKey(num)) {
                continue;
            }

            Integer cnt = map.get(num) - 1;
            ans[size++] = num;
            if (cnt > 0) {
                map.put(num, cnt);
            } else {
                map.remove(num);
            }
        }

        return Arrays.copyOfRange(ans, 0, size);
    }

}
