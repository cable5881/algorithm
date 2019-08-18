package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets_ii {

    @Test
    public void test1() {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        ArrayList<List<Integer>> res = new ArrayList<>();
        subsetsWithDup(nums, 0, res, new LinkedList<>());
        return res;
    }

    private void subsetsWithDup(int[] nums, int cur, List<List<Integer>> res, LinkedList<Integer> buf) {

        // 条件是> 不是 >=，因为上一个递归buf添加完最后一个元素后还没有添加到res中，所以这里需要添加到res
        if (cur > nums.length) {
            return;
        }

        res.add(new ArrayList<>(buf));

        for (int i = cur; i < nums.length; i++) {
            if (i > cur && nums[i] == nums[i - 1]) {
                continue;
            }
            buf.addLast(nums[i]);
            //这里是i + 1 不是 cur + 1， 否则元素会有重复
            subsetsWithDup(nums, i + 1, res, buf);
            buf.removeLast();
        }
    }

}
