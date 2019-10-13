package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4},[6,4},[6,7},[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RussianDollEnvelopes {

    @Test
    public void test() {
        //3
        System.out.println(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));

        //1
        System.out.println(maxEnvelopes(new int[][]{{5,1},{5,2},{5,3}}));

        //3
        System.out.println(maxEnvelopes(new int[][]{{6,7},{6,4},{5,4},{5,2},{1,8},{2,3}}));
    }

    /**
     * 官方解法：先将信封按照宽度升序、高度倒序的方式排序，然后问题即转化为了求h的最长递增子序列
     * 高度倒序是有必要的，如{5,1},{5,2},{5,3}，如果不按照高度倒序结果是3，即相同宽度的信封都被计算在内了
     * 高度倒序保证了相同宽度的信封只取1个参与计算
     * 时间复杂度O(nlogn)，空间复杂度O(n)
     * @see  LongestIncreasingSubsequence 最长递增子序列
     */
    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes == null || envelopes.length <= 0 || envelopes[0].length <= 1) {
            return 0;
        }

        //转化为{1,8},{2,3},{5,4},{5,2},{6,7},{6,4}
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        //[8,3,4,2,7,4]
        int[] heights = new int[envelopes.length];

        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        return maxEnvelopes(heights);
    }

    private int maxEnvelopes(int[] heights) {

        int[] dp = new int[heights.length];
        dp[0] = heights[0];
        int cnt = 1;

        for (int i = 1; i < heights.length; i++) {
            int j = Arrays.binarySearch(dp, 0, cnt, heights[i]);
            if (j < 0) {
                int insert = -(j + 1);
                if (insert == cnt) {
                    cnt++;
                }
                dp[insert] = heights[i];
            }
        }

        return cnt;
    }

}
