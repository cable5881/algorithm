package com.lqb.leetcode.mark.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingSubsequence {

    @Test
    public void test() {
        //4
        //6
        //5
        //1
        //1
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18, 3, 4, 5, 6, 7}));
        System.out.println(lengthOfLIS(new int[]{2, 15, 3, 7, 8, 6, 18}));
        System.out.println(lengthOfLIS(new int[]{10}));
        System.out.println(lengthOfLIS(new int[]{10, 9}));
    }

    @Test
    public void test2() {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    /**
     * 动态规划，时间复杂度O(nlogn)
     * 设dp[i]是从0到i的最长子序列(子序列中必须要包含a[i]，所以dp[i]可能不是全局最长的子序列)
     * 那么dp[i] = Max(dp[0], dp[1], ..., dp[i - 1]) + 1
     * 且需要满足a[i] > {a[0], a[i], ...., a[i] - 1}
     */
    public int lengthOfLIS(int[] a) {

        int maxLen = 1;
        int[] dp = new int[a.length];
        dp[0] = 1;
        for (int i = 1; i < a.length; i++) {
            dp[i] = 1;
            // 这种找最近的比a[i]小的dp+1是不对的
            // 考虑这种情况a=[7, 8, 6, 18]
            // 使用下面的判断dp=[1,2,1,2]
            // 实际上dp应该为[1,2,1,3]
            // for (int j = i - 1; j >= 0; j--) {
            //     if (a[i] > a[j]) {
            //         dp[i] = dp[j] + 1;
            //         break;
            //     }
            // }
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    /**
     * 官方解法: 用一个数组dp存储上升子序列, 算法复杂度O(nlogn), 空间复杂度O(n)
     * 每遍历一个元素, 如果元素比dp中都小(二分查找), 则替换dp[0]；如果比dp中都大，则追加, 且最大子序列长度加1
     * 如果元素不在dp中但是又在dp的最小和最大范围内, 则替换到它应该插入的位置
     *
     * 难点:
     *  - 二分查找元素如果不存在, 则返回它应该插入的位置
     *  - 需要承认一个事实, 用dp来存储上升子序列, 该子序列可能不是一个有效的最长子序列(后面可能有比dp[0]更小的,
     *  替换后就不是一个完整的最长子序列了), 但是长度是一定对的.
     *
     * 用[10, 9, 2, 5, 3, 7, 101, 18] 举例看dp和len的变化
     * [10], 1
     * [9], 1
     * [2], 1
     * [2, 5], 2
     * [2, 3], 2
     * [2, 3, 7], 3
     * [2, 3, 7, 101], 4
     * [2, 3, 7, 18], 4
     */
    public int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                //让i 变成正数, 也就是可以插入的位置
                i = -(i + 1);
            }
            dp[i] = num;

            //如果是追加到dp, 则len自增; 如果不是追加, 只是替换, 则i <= len - 1,不会进到if条件中
            if (i == len) {
                len++;
            }
        }

        return len;
    }

}
