package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class CanJump_1 {

    @Test
    public void test() {
        CanJump_1 demo = new CanJump_1();
        System.out.println(demo.canJump3(new int[]{2, 3, 1, 1, 4}));
        System.out.println(demo.canJump3(new int[]{3, 2, 1, 0, 4}));
    }

    public boolean canJump(int[] nums) {
        return canJump(nums, 0, new int[nums.length]);
    }

    private boolean canJump(int[] nums, int cur, int[] visit) {
        if (cur == nums.length - 1) {
            return true;
        }

        if (cur > nums.length - 1) {
            return false;
        }

        for (int i = nums[cur]; i >= 1; i--) {
            if (visit[cur] != -1 && canJump(nums, cur + i, visit)) {
                return true;
            }
        }

        visit[cur] = -1;
        return false;
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    /**
     * 官方解法1
     **/
    public boolean canJump2(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    /**
     * 模仿官方解法1
     **/
    public boolean canJump2_1(int[] nums) {

        int[] memo = new int[nums.length];
        memo[nums.length - 1] = 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(nums[i] + i, nums.length -1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == 1) {
                    memo[i] = 1;
                    break;
                }
            }
        }

        return memo[0] == 1;
    }

    /**
     * 官方最优解法
     **/
    public boolean canJump3(int[] nums) {

        int len = nums.length;
        int lastJumpIndex = len - 1;

        for (int i = len - 2; i >= 0; i--) {
            if (i + nums[i] >= lastJumpIndex) {
                lastJumpIndex = i;
            }
        }

        return lastJumpIndex == 0;
    }


}
