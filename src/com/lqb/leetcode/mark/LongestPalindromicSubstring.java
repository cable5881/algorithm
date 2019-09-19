package com.lqb.leetcode.mark;


import org.junit.Test;

/**
 * @Description 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * @Author liqibo
 * @Date 2019/6/14 14:41
 **/
public class LongestPalindromicSubstring {

    @Test
    public void test1() {
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome("abba"));
        System.out.println(test.longestPalindrome("ab"));
        System.out.println(test.longestPalindrome("aba"));
        System.out.println(test.longestPalindrome("a"));
        System.out.println(test.longestPalindrome("abaadbcdaadc"));

        System.out.println(test.longestPalindrome3("abaadbcdaadc"));
    }


    /**
     * @description 自己的解法：暴力法，时间复杂度O(n²)，空间复杂度O(1)
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
            for (int j = chars.length - 1; j >= i; j--) {
                int tempLen = j - i + 1;
                if (tempLen > maxStr.length() && isPalindrome(chars, i, j)) {
                    String tempStr = new String(chars, i, tempLen);
                    maxStr = tempStr;
                    break;
                } else if (tempLen < maxStr.length()) {
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
        while (start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 官方解法：中心扩展算法
     * 事实上，只需使用恒定的空间，我们就可以在 O(n^2)的时间内解决这个问题。
     * 我们观察到回文中心的两侧互为镜像。因此，回文可以从它的中心展开，并且只有 2n−1 个这样的中心。
     * 你可能会问，为什么会是 2n−1 个，而不是 n 个中心？
     * 原因在于所含字母数为偶数的回文的中心可以处于两字母之间（例如 abba 的中心在两个之间）。
     **/
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * @description 模仿中心扩展算法
     * @author liqibo
     * @date 2019/7/4 15:16
     **/
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int len1 = getPalindromeLength(chars, i, i);
            int len2 = getPalindromeLength(chars, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > (end - start + 1)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return new String(chars, start, end - start + 1);
    }

    public int getPalindromeLength(char[] chars, int left, int right) {

        //曾经每一次迭代用count++，最后返回count，这是错误的，因为长度每次递增2
        while (left >= 0 && right <= chars.length - 1 && chars[left] == chars[right]) {
            left--;
            right++;
        }

        //注意这不是right - left + 1.
        //因为循环退出时,left和right肯定是偏大的
        return right - left - 1;
    }

}
