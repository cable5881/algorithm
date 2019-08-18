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
        System.out.println(subsetsWithDup2(new int[]{1,2,2}));
    }

    /**
     * 先排序，如果当前元素和上一个相同，则不进行递归
     * @param nums
     * @return
     */
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

    /**
     * 官方解答：迭代法
     * 每一次循环遍历上一次循环添加的元素，在上一次的基础上继续添加元素
     * 如上一次为[],[1]
     * 则下一次为[2],[1,2]
     * 合并为[],[1],[2],[1,2]
     *
     * 接下来就是考虑如何剔除重复子集
     * [],[1]是[]基础上产生的(start=0)
     * [],[1],[2],[1,2]是[],[1]基础上产生的(start=1)
     * 此时下一个数也是2，此时在上一个基础上需要剔除掉[],[1]，因为他们在上一轮已经被2用过了，但是[2],[1,2]还没用过
     * 因此以[2],[1,2]为基础，添加为[2，2],[1,2,2] (start=2)
     *
     * https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());// 初始化空数组
        Arrays.sort(nums);

        int start = 1; //保存新解的开始位置
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();

            // 遍历之前的所有结果
            for (int j = 0; j < ans.size(); j++) {
                List<Integer> list = ans.get(j);
                //如果出现重复数字，就跳过所有旧解
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }

                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]); // 加入新增数字
                ans_tmp.add(tmp);
            }

            start = ans.size(); //更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
    }

}
