package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MoveZeroes {

    @Test
    public void test1() {
        int[] ints = {0, 1, 0, 3, 12};
        moveZeroes3(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test2() {
        int[] ints = {0, 0, 0, 0, 12};
        moveZeroes3(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test3() {
        int[] ints = {1, 2, 3};
        moveZeroes3(ints);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 自己的解法：所有非0的元素往前移动，统计0的个数，最后末尾补0
     * 缺陷：元素不能为负数
     **/
    public void moveZeroes(int[] nums) {

        if(nums == null) {
            return;
        }

        int next = -1;
        int zeroCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
                if (next < 0) {
                    next = i;
                }
            } else if (next >= 0) {
                nums[next++] = nums[i];
            }
        }

        for (int i = nums.length - 1; zeroCnt > 0; i--, zeroCnt--) {
            nums[i] = 0;
        }
    }

    /**
     * 官方的解法， 和我的解法思想一样，但是更加简洁
     **/
    public void moveZeroes2(int[] nums) {
        if(nums == null) {
            return;
        }

        //next是不为0的元素移动的下标，同时也统计了0的个数
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[next++] = nums[i];
            }
        }

        for (int i = next; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 官方最优解：利用交换可以直接将0换到末尾，这样可以避免上一种解法的[0,0,0,0,1]需要遍历完全遍历2次的缺陷
     **/
    public void moveZeroes3(int[] nums) {
        if(nums == null) {
            return;
        }

        //next是不为0的元素移动的下标
        for (int i = 0, next = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, next++, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
