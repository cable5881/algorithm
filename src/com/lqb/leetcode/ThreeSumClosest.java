package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ThreeSumClosest {


    @Test
    public void test1() {
        ThreeSumClosest demo = new ThreeSumClosest();
        System.out.println(demo.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(demo.threeSumClosest(new int[]{-16, -15, -9, -6, -4, -3, -1, 0, 2, 3, 6, 7, 9, 10, 15}, 25));
        System.out.println(demo.threeSumClosest(new int[]{1,1,-1,-1,3}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {

        int nearestSum = Integer.MAX_VALUE;
        int nearestDelta = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            while (i > 0 && i < nums.length - 2 && nums[i] == nums[i - 1]) {
                i++;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int delta = Math.abs(target - sum);

                if (delta == 0) {
                    return sum;
                } else if (delta < nearestDelta) {
                    nearestSum = sum;
                    nearestDelta = delta;
                }

                if (sum < target) {
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                }
            }
        }


        return nearestSum;
    }

}
