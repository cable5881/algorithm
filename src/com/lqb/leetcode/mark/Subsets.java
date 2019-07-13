package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets {

    @Test
    public void test() {
        Subsets demo = new Subsets();
        System.out.println(demo.subsets(new int[] {1,2,3}));
    }

    public List<List<Integer>> subsets(int[] nums) {

        if (nums == null) {
            return Collections.EMPTY_LIST;
        }

        List<List<Integer>> ans = new ArrayList<>();

        findSubsets(nums, 0, new LinkedList<>(), ans);

        return ans;
    }

    private void findSubsets(int[] nums, int cur, LinkedList<Integer> stack, List<List<Integer>> subsets) {

        //如果这里是cur >= nums.length，那么无法覆盖到nums的最后一个数，即类似[1,2,3]/[2,3]等无法被添加到子集中
        if (cur > nums.length) {
            return;
        }

        subsets.add(new ArrayList<>(stack));

        for (int i = cur; i < nums.length; i++) {
            stack.add(nums[i]);
            //第二个参数不是cur, 因为cur并不会在循环中递增，因此会出现类似[3, 3]这种重复的
            findSubsets(nums, cur + 1, stack, subsets);
            stack.removeLast();
        }
    }

}
