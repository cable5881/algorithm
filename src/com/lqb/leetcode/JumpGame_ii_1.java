package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class JumpGame_ii_1 {

    @Test
    public void test() {
        JumpGame_ii_1 demo = new JumpGame_ii_1();
        //System.out.println(demo.jump2(new int[]{2}));
        //System.out.println(demo.jump2(new int[]{2, 3}));
        //System.out.println(demo.jump2(new int[]{2, 3, 1}));
        //System.out.println(demo.jump2(new int[]{2, 3, 1, 1, 4}));
        //System.out.println(demo.jump2(new int[]{2, 3, 1, 1, 4, 1, 1, 3, 2, 1, 4}));
        System.out.println(demo.jump3(new int[]{1, 1, 1, 100, 4, 1, 1, 3, 2, 1, 4, 1}));
    }

    //自己的解法O(n²) 超时了
    public int jump(int[] nums) {

        int len = nums.length;
        int[] steps = new int[len];

        for (int i = len - 2; i >= 0; i--) {
            int minStep = 0;
            for (int j = nums[i]; j >= 0; j--) {
                int next = i + j;
                if (next >= len - 1) {
                    minStep = 1;
                    break;
                }

                if (steps[next] == 0) {
                    continue;
                }

                int tempStep = steps[next] + 1;
                if (tempStep < minStep || minStep == 0) {
                    minStep = tempStep;
                }
            }
            steps[i] = minStep;
        }

        return steps[0];
    }

    //需要注意的是总能到达最后一个位置,也就是说不存在0

    //官方解法一  O(n)
    public int jump2(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找到最远能到达的位置，这个位置可能是当前能跳的最远的或者是上一次能跳到最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);

            //到达了上一次能跳到的最远的位置, 步数 + 1 (其实达到了下一个能到达的最远位置的起点也需要步数 + 1)
            //保存下一次能跳到的最远位置
            if( i == end){
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    //官方解法二  时间复杂度O(n²)  空间复杂度：O（1）
    public int jump3(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

}
