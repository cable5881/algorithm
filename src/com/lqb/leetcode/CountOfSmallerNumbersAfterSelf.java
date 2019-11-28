package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class CountOfSmallerNumbersAfterSelf {

    @Test
    public void test() {
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(countSmaller(new int[]{3, 5, 1, 0, 7, 2, 4, 9, 6, 1}));
    }

    /**
     * @author liqibo
     * @date 2019/11/28 20:52
     * @description 利用归并排序的分治法来统计“逆序对”
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return Collections.emptyList();
        }

        Integer[] res = new Integer[nums.length];
        Num[] buf = new Num[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = 0;
            buf[i] = new Num(nums[i], i);
        }

        countSmaller(buf, 0, nums.length - 1, res);

        return Arrays.asList(res);
    }

    private void countSmaller(Num[] nums, int start, int end, Integer[] res) {
        if (start >= end) {
            return;
        }

        int L1 = start;
        int R1 = (start + end) / 2;
        int L2 = R1 + 1;
        int R2 = end;

        countSmaller(nums, L1, R1, res);
        countSmaller(nums, L2, R2, res);

        Num[] tmp = new Num[end - start + 1];
        int i = R1;
        int j = R2;
        int k = tmp.length - 1;

        while (i >= L1 && j >= L2) {
            if (nums[i].val > nums[j].val) {
                //统计逆序对，L2数组中剩下的元素都是比L1当前小的
                res[nums[i].idx] += (j - L2 + 1);
                tmp[k--] = nums[i--];
            } else {
                tmp[k--] = nums[j--];
            }
        }

        while (i >= L1) {
            tmp[k--] = nums[i--];
        }
        while (j >= L2) {
            tmp[k--] = nums[j--];
        }

        System.arraycopy(tmp, 0, nums, start, tmp.length);
    }

    /**
     * @author liqibo
     * @date 2019/11/28 20:52
     * @description 用一个包装类来存储实际值和对应的索引，Map不行，因为碰到值相同的会Key覆盖
     */
    private static class Num {
        int val;
        int idx;
        public Num(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

}
