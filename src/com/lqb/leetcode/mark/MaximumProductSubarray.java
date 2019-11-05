package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MaximumProductSubarray {

    @Test
    public void test() {
        System.out.println(maxProduct3(new int[]{2, 3, -2, 4}));//6
        System.out.println(maxProduct3(new int[]{-2, 0, -1}));//0
        System.out.println(maxProduct3(new int[]{-2, -2, -1}));//4
        System.out.println(maxProduct3(new int[]{-2, -2, -2, -2}));//16

        //最后一个数比之前的子序列乘积都要大
        System.out.println(maxProduct3(new int[]{12, 2, -3, 28}));//28

        System.out.println(maxProduct3(new int[]{-3, 0, 1, -2}));//1
        System.out.println(maxProduct3(new int[]{-2, 1, 0, -3}));//1

        //负数数量为奇数
        System.out.println(maxProduct3(new int[]{2, -5, -2, -4, 3}));//24

        //虽然负数为正，但是中间有0隔开
        System.out.println(maxProduct3(new int[]{-1, 0, -1, 2, -3, 1, 2, 3, -2}));//72
    }

    /**
     * @author liqibo
     * @date 2019/11/4 21:16
     * @description 暴力法
     * 时间复杂度 O(n^2)
     */
    public int maxProduct(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int res = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp > res) {
                res = temp;
            }
            for (int j = i + 1; j < nums.length; j++) {
                temp *= nums[j];
                if (temp > res) {
                    res = temp;
                }
            }

        }

        return res;
    }

    /**
     * 错误的解法：尝试根据负数的个数来推断子序列，如负数的个数为偶数则可以一直乘下去
     */
    public int maxProduct2(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int negative = 0;
        int firstNeg = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negative++;
                if (firstNeg < 0) {
                    firstNeg = i;
                }
            }
        }

        int res = getMax(nums, 0, negative);
        if (negative > 1 && (negative & 1) != 0) {
            res = Math.max(res, getMax(nums, firstNeg + 1, negative - 1));
        }

        return res;
    }

    private int getMax(int[] nums, int start, int negative) {
        int res = nums[start];
        int temp = 1;
        for (int i = start; i < nums.length; i++) {
            temp *= nums[i];
            res = Math.max(temp, res);
            if (nums[i] < 0) {
                negative--;
            }
            if (temp < 0) {
                if (negative <= 0) {
                    temp = 1;
                }
                if (nums[i] > res) {
                    res = nums[i];
                }
            } else if (temp == 0) {
                temp = 1;
            }
        }

        return res;
    }


    /***
     * 标签：动态规划
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。
     * 因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
     */
    public int maxProduct3(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;

        for (int num : nums) {
            if (num < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            //如果num为负数
            //① 如果上一个迭代imax为正数，imax为正数，因为imax >= imin，交换后乘以num，imax还是大于等于imin
            //② 如果上一个迭代imax为负数，imin也必然为负数，交换后|imax| >= |imin|，所以乘以num后，imax还是大于等于imin
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            max = Math.max(imax, max);
        }

        return max;
    }


    /**
     * 和解法二思想是类似的，只需要理解这种解法应该考虑到几种情况
     * 1.如果负数个数是偶数，那么一直乘就完事
     * 2.如果负数个数是奇数，那么最长子序列可能是第一个负数后面的所有数字（少掉第一个负数后负数个数就为偶数了）
     * 或者是最后一个负数前的所有数字
     * 3.如果出现0的情况，只需要重置即可
     *
     * 针对1和2的情况，正序和倒序遍历就可以解决
     * 针对3的情况，只需要将临时乘积重置为1接着往下乘即可
     */
    public int maxProduct4(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }

        }
        max = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }

        return res;
    }

}
