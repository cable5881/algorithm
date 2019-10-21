package com.lqb.leetcode;

import org.junit.Test;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
 * 这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ImplementStrstr {

    @Test
    public void test() {
        System.out.println(strStr2("hello", "ll"));
        System.out.println(strStr2("aaaaa", "bba"));
        System.out.println(strStr2("aaaaa", ""));
    }

    /**
     * @author liqibo
     * @date 2019/10/18 16:22
     * @description 暴力法，时间复杂度O(n * m)
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        if (needle.equals("")) {
            return 0;
        }

        int index = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }

            int j = i + 1;
            int k = 1;
            while (k < needle.length() && j < haystack.length()) {
                if (haystack.charAt(j) != needle.charAt(k)) {
                    break;
                }
                j++;
                k++;
            }

            if (k == needle.length()) {
                return i;
            }

            if (haystack.length() - i <= needle.length()) {
                return index;
            }
        }

        return index;
    }

    /**
     * @author liqibo
     * @date 2019/10/18 20:49
     * @description KMP算法，时间复杂度O(N)，空间复杂度O(256M)
     * https://leetcode-cn.com/problems/implement-strstr/solution/kmp-suan-fa-xiang-jie-by-labuladong/
     *
     * 如果我们能知道下一次从哪里开始匹配就不用每次都从头开始匹配了
     * 如文本串“ABABABC”，模式串“ABABC”，到第5位不相同，
     * A B A B A B C
     * A B A B C
     *
     * 那么如果文本串第5位和模式串的第3位相同，那么就可以一起匹配第6位了
     * A B A B A B C
     *     A B A B C
     *
     * 否则需要从匹配串第1位开始从新匹配
     * A B A B D B C
     *           A B A B C
     *
     * KMP本质上是一个动态规划
     */
    public int strStr2(String s, String tar) {
        if (s == null || tar == null || tar.length() > s.length()) {
            return -1;
        }

        if (tar.equals("")) {
            return 0;
        }

        int[][] dp = getDP(tar);

        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            //下一次从tar的第K位开始匹配
            k = dp[k][s.charAt(i)];
            if (k == tar.length()) {
                return i + 1 - k;
            }
        }

        return -1;
    }

    /**
     * 获取“下一次从哪里开始匹配”的动态规划数组
     * 构建DP的过程是自己跟自己匹配的过程
     * 如“ABABC”
     * “第1个和第2个” 和 “第3个和第4个” 都具有相同的前缀“AB”
     *  那么当第5个不匹配时，应该看上一个AB的处理方式，上一个AB遇到A会到第3的位置，遇到B会回到0。
     *
     * dp[0]['A'] = 1, dp[0]['B'] = 0, dp[0]['C'] = 0, X=0
     * dp[1]['A'] = 1, dp[1]['B'] = 2, dp[1]['C'] = 0, X=0
     * dp[2]['A'] = 3, dp[2]['B'] = 0, dp[2]['C'] = 0, X=1
     * dp[3]['A'] = 1, dp[3]['B'] = 4, dp[3]['C'] = 0, X=2
     * dp[4]['A'] = 3, dp[4]['B'] = 0, dp[4]['C'] = 5, X=0
     */
    private int[][] getDP(String tar) {
        int[][] dp = new int[tar.length()][256];
        dp[0][tar.charAt(0)] = 1;
        int X = 0;

        for (int i = 1; i < tar.length(); i++) {
            for (int j = 0; j < 256; j++) {
                if (j == tar.charAt(i)) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[X][j];
                }
            }
            X = dp[X][tar.charAt(i)];
        }
        return dp;
    }
}
