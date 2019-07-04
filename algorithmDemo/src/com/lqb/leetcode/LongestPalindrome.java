package com.lqb.leetcode;


import org.junit.Test;

/**
 * @Description 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @Author liqibo
 * @Date 2019/6/14 14:41
 **/
public class LongestPalindrome {

    @Test
    public void test1() {
        LongestPalindrome test = new LongestPalindrome();
        System.out.println(test.longestPalindrome("abba"));
        System.out.println(test.longestPalindrome("ab"));
        System.out.println(test.longestPalindrome("aba"));
        System.out.println(test.longestPalindrome("a"));
        System.out.println(test.longestPalindrome("abaadbcdaadc"));
    }


    /**
     * @description 自己的解法：暴力法，算法复杂度O(n³)
     * @author liqibo
     * @date 2019/7/4 9:38
     **/
    public String longestPalindrome(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        String maxStr = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for(int j = chars.length - 1; j >= i; j--){
                if (isPalindrome(chars, i, j)) {
                    String tempStr = new String(chars, i, j - i + 1);
                    if (tempStr.length() > maxStr.length()) {
                        maxStr = tempStr;
                    }
                    break;
                } else if (j - i + 1 < maxStr.length()) {
                    break;
                }
            }

            if (chars.length - i <= maxStr.length()) {
                break;
            }
        }

        return maxStr;
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        while(start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }

        return true;
    }

}
