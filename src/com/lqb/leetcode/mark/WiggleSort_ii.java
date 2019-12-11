package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * <p>
 * 示例 1:
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * <p>
 * 示例 2:
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * <p>
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 * <p>
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-sort-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
@SuppressWarnings("Duplicates")
public class WiggleSort_ii {

    @Test
    public void test1() {
        //[1, 6, 1, 5, 1, 4]
        int[] nums = {1, 5, 1, 1, 6, 4};
        wiggleSort2(nums);
        System.out.println(Arrays.toString(nums));

        //[2, 3, 1, 3, 1, 2]
        int[] nums1 = {1, 3, 2, 2, 3, 1};
        wiggleSort2(nums1);
        System.out.println(Arrays.toString(nums1));

        //[1]
        int[] nums2 = {1};
        wiggleSort2(nums2);
        System.out.println(Arrays.toString(nums2));

        //[1, 2]
        int[] nums3 = {2, 1};
        wiggleSort2(nums3);
        System.out.println(Arrays.toString(nums3));

        //[2, 5, 1]
        int[] nums4 = {2, 1, 5};
        wiggleSort2(nums4);
        System.out.println(Arrays.toString(nums4));

        //[2, 3, 2, 3, 1, 2, 1]
        int[] nums5 = {1, 3, 2, 2, 2, 3, 1};
        wiggleSort2(nums5);
        System.out.println(Arrays.toString(nums5));

        //[5, 6, 4, 5]
        int[] nums6 = {4, 5, 5, 6};
        wiggleSort2(nums6);
        System.out.println(Arrays.toString(nums6));
    }

    /**
     * @author liqibo
     * @date 2019/11/26 19:38
     * @description 错误解法，[4,5,5,6]无法处理
     * 先排序，将后半段较大的数插到前半段较小的数中
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        //先排序
        Arrays.sort(nums);

        //确定从哪里开始就较大和较小
        int m = nums.length / 2;
        if (nums.length % 2 != 0) {
            m += 1;
        }

        for (int i = 1, j = m; j < nums.length; i += 2, j++) {
            int tmp = nums[j];
            //i到j的元素向后移动
            for (int k = j; k >= i; k--) {
                nums[k] = nums[k - 1];
            }
            nums[i] = tmp;
        }
    }

    /**
     * @author liqibo
     * @date 2019/11/26 21:02
     * @description 官方解法1，算法时间复杂度O(nlogn)，空间复杂度O(n)
     * 和上面的思想相同，关键是解决了[4,5,5,6]
     * 上面的插入规则是[a1,a(n/2+1),a2,a(n/2+2),...,an]
     * 这里改成了[a(n/2),a(n),a(n/2-1),a(n-1),...,a1,a(n/2+1)]
     */
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        //先排序
        Arrays.sort(nums);

        //确定从哪里开始就较大和较小
        int m = nums.length / 2;
        if (nums.length % 2 != 0) {
            m += 1;
        }

        int[] smaller = new int[m];
        int[] bigger = new int[nums.length - m];

        System.arraycopy(nums, 0, smaller, 0, m);
        System.arraycopy(nums, m, bigger, 0, bigger.length);

        //[a(n/2),a(n),a(n/2-1),a(n-1),...,a1,a(n/2+1)]
        for (int i = 1; i < nums.length; i += 2) {
            nums[i - 1] = smaller[m - i / 2 - 1];
            nums[i] = bigger[bigger.length - i / 2 - 1];
        }

        //如果是奇数个则需要把最后一个数填上，因为上面的循环还缺少一次迭代
        if (nums.length % 2 != 0) {
            nums[nums.length - 1] = smaller[0];
        }
    }

    /**
     * @author liqibo
     * @date 2019/11/26 21:02
     * @description 官方解法2，算法时间复杂度O(n)，空间复杂度O(n)
     * 其实我们不关注 smaller 和 bigger 中的顺序，因此可以用快速选择来将小于中位数的放在左边，大于中位数的放在右边
     * 注意和中位数相同的得放在中间
     * 然后还是用交叉插入的方式来改变数组
     */
    public void wiggleSort3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }


    }

}
