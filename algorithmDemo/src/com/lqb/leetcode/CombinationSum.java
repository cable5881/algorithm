package com.lqb.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * <p>
 * <p>
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * <p>
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@SuppressWarnings("Duplicates")
public class CombinationSum {

    @Test
    public void test() {
        CombinationSum demo = new CombinationSum();
        System.out.println(demo.combinationSumV1(new int[]{2, 3, 5}, 8));
        System.out.println(demo.combinationSumV1(new int[]{2, 3, 6, 7}, 7));
        System.out.println(demo.combinationSumV1(new int[]{2, 3, 5}, 7));
        System.out.println("====================================================");
        System.out.println(demo.combinationSumV2(new int[]{2, 3, 5}, 8));
        System.out.println(demo.combinationSumV2(new int[]{2, 3, 6, 7}, 7));
        System.out.println(demo.combinationSumV2(new int[]{2, 3, 5}, 7));
    }


    /**
     * 思路：target = n * nums[i] + m * nums[j] + q * nums[k] + ......
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {

        ArrayList<List<Integer>> ans = new ArrayList<>();

        if (candidates == null || candidates.length < 1) {
            return ans;
        }

        Arrays.sort(candidates);

        if (candidates[0] > target) {
            return ans;
        }


        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            int sum = 0;
            for (int j = 1; j <= target / candidates[i]; j++) {
                queue.add(candidates[i]);
                sum += candidates[i];
                if (sum > target) {
                    break;
                } else if (sum == target) {
                    ans.add(new ArrayList<>(queue));
                    break;
                }
                funV1(candidates, target, i + 1, sum, ans, queue);
            }
            queue.clear();
        }

        return ans;
    }

    private void funV1(int[] candidates, int target, int cur, int sum, ArrayList<List<Integer>> ans, LinkedList<Integer> queue) {

        if (cur > candidates.length - 1) {
            return;
        }

        LinkedList<Integer> tempQ;
        for (int i = cur; i < candidates.length; i++) {
            tempQ = new LinkedList<>(queue);
            int loop = (target - sum) / candidates[i];
            int tempSum = sum;
            for (int j = 0; j < loop; j++) {
                tempSum += candidates[i];
                if (tempSum > target) {
                    break;
                }
                tempQ.add(candidates[i]);
                if (tempSum == target) {
                    ans.add(new ArrayList<>(tempQ));
                    break;
                }
                funV1(candidates, target, i + 1, tempSum, ans, tempQ);
            }
        }

    }


    /**
     * 思路和V1是一样的，就是合并了相同逻辑，以及队列不每次new而是remove
     */
    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {

        ArrayList<List<Integer>> ans = new ArrayList<>();

        if (candidates == null || candidates.length < 1) {
            return ans;
        }

        Arrays.sort(candidates);

        if (candidates[0] > target) {
            return ans;
        }

        funV2(candidates, target, 0, 0, ans, new LinkedList<>());

        return ans;
    }

    private void funV2(int[] candidates, int target, int cur, int sum, ArrayList<List<Integer>> ans, LinkedList<Integer> queue) {

        if (cur > candidates.length - 1) {
            return;
        }

        LinkedList<Integer> tempQ = new LinkedList<>(queue);
        for (int i = cur; i < candidates.length; i++) {
            int loop = (target - sum) / candidates[i];
            int tempSum = sum;
            int delete = 0;
            for (int j = 0; j < loop; j++) {
                tempSum += candidates[i];
                if (tempSum > target) {
                    break;
                }
                tempQ.addLast(candidates[i]);
                delete++;
                if (tempSum == target) {
                    ans.add(new ArrayList<>(tempQ));
                    break;
                }
                funV2(candidates, target, i + 1, tempSum, ans, tempQ);
            }
            remove(tempQ, delete);
        }
    }

    private void remove(LinkedList<Integer> queue, int delete) {
        while (delete-- > 0) {
            queue.removeLast();
        }
    }
}
