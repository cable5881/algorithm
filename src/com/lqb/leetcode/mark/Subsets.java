package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.*;

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
        System.out.println(demo.subsets2(new int[] {1,2,3}));
    }

    /**
     * 递归法
     */
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
            //第二个参数不是cur + 1, 因为cur + 1并不会在循环中递增，因此会出现类似[3, 3]这种重复的
            findSubsets(nums, i + 1, stack, subsets);
            stack.removeLast();
        }
    }

    /**
     * 迭代法
     *
     * 每一次循环遍历上一次循环添加的元素，在上一次的基础上继续添加元素
     *      * 如上一次结果集中为[],[1]
     *      * 则下一次在上一次的结果集遍历，分别添加2，即[2],[1,2]
     *      * 最后合并上一次和这一次的结果，为[],[1],[2],[1,2]
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());// 初始化空数组

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();

            // 遍历之前的所有结果
            for (int j = 0; j < ans.size(); j++) {
                List<Integer> list = ans.get(j);
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]); // 加入新增数字
                ans_tmp.add(tmp);
            }

            ans.addAll(ans_tmp);
        }
        return ans;
    }

}
