package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors_1 {

    @Test
    public void test() {
        SortColors_1 demo = new SortColors_1();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        demo.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {

        if (nums == null || nums.length <= 0) {
            return;
        }

        int numR = 0, numW = 0, numB = 0;

        for (int num : nums) {
            switch (num) {
                case 0:
                    numR++;
                    break;
                case 1:
                    numW++;
                    break;
                case 2:
                    numB++;
                    break;
            }
        }

        fillColors(nums, 0, numR, 0);
        fillColors(nums, 1, numW, numR);
        fillColors(nums, 2, numB, numR + numW);
    }

    private void fillColors(int[] nums, int color, int colorNum, int start) {
        for (int i = 0; i < colorNum; i++) {
            nums[start++] = color;
        }
    }

    /**
     * 官方题解:荷兰三色旗问题解
     *
     * p0指向存0的位置, 也就是靠前的
     * p2指向存2的位置, 也就是靠后的
     * cur指向当前遍历的位置
     *
     * cur从0开始, 如果nums[cur]是0则放到p0的位置, p0++
     * 如果nums[cur]是2则放到p2的位置, p2--
     * 最后cur++
     *
     */
    public void sortColors2(int[] nums) {
        int p0 = 0, curr = 0;
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else {
                curr++;
            }
        }
    }

}
