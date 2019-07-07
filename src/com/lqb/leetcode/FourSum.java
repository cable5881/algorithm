package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

public class FourSum {


    @Test
    public void test() {
        FourSum demo = new FourSum();
        System.out.println(demo.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(demo.fourSum(new int[]{1, 0, -1, 0, -2}, 0));
        System.out.println(demo.fourSum(new int[]{1, 0, -1, 0}, 0));
        System.out.println(demo.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Set<List<Integer>> res = new HashSet<>();

        int Li = 0;
        int Ri = nums.length - 1;

        Arrays.sort(nums);

        while (Ri - Li >= 2) {
            int lj = Li + 1;
            int rj = Ri - 1;

            int twoSum = nums[Li] + nums[Ri];

            while (lj < rj) {
                int fourSum = twoSum + nums[lj] + nums[rj];

                if (fourSum == target) {
                    res.add(Arrays.asList(nums[Li], nums[lj], nums[rj], nums[Ri]));
                }

                if (fourSum < target) {
                    lj++;
                } else {
                    rj--;
                }
            }

            if (twoSum < target) {
                Li++;
            } else {
                Ri--;
            }
        }

        return new ArrayList<>(res);
    }


}
