package com.lqb.leetcode.mark.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class TwoSum {

    @Test
    public void test() {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum2(new int[]{-16, -15, -9, -6, -4, -3, -1, 0, 2, 3, 6, 7, 9, 10, 15}, 9)));
        System.out.println(Arrays.toString(twoSum.twoSum2(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }

    /**
     * @description 注意是返回原始索引，因此不能排序，排序会改变原始索引
     * @author liqibo
     * @date 2019/6/27 11:51
     **/
    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length <= 1) {
            return null;
        }

        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numMap.put(nums[i], i);
        }

        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (numMap.containsKey(j) && numMap.get(j) != i) {
                ans[0] = i;
                ans[1] = numMap.get(j);
                break;
            }
        }

        return ans;
    }

    /**
     * @description 一遍哈希
     * @author liqibo
     * @date 2019/6/27 11:51
     **/
    public int[] twoSum2(int[] nums, int target) {

        if (nums == null || nums.length <= 1) {
            return null;
        }

        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                res[0] = map.get(key);
                res[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        return res;
    }


}
