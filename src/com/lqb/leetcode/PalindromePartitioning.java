package com.lqb.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PalindromePartitioning {

    @Test
    public void test() {
        System.out.println(partition2("aab"));
    }

    /**
     * @author liqibo
     * @date 2019/12/10 15:07
     * @description 暴力法
     * 时间复杂度：O(n ^ 3)
     * 空间复杂度：O(n)
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.length() <= 0) {
            return Collections.emptyList();
        }

        ArrayList<List<String>> res = new ArrayList<>();
        partition(s.toCharArray(), 0, new ArrayList<>(), res);
        return res;
    }

    private void partition(char[] str, int start, List<String> buf, List<List<String>> res) {
        if (start >= str.length) {
            res.add(new ArrayList<>(buf));
            return;
        }

        for (int i = start; i < str.length; i++) {
            if (isPalindrome(str, start, i)) {
                buf.add(new String(str, start, i - start + 1));
                partition(str, i + 1, buf, res);
                buf.remove(buf.size() - 1);
            }
        }
    }

    private boolean isPalindrome(char[] str, int i, int j) {
        int left = i;
        int right = j;

        while (left < right) {
            if (str[left++] != str[right--]) {
                return false;
            }
        }

        return true;
    }

    /**
     * @author liqibo
     * @date 2019/12/10 15:13
     * @description 动态规划，将是回文串的地方记录下来
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(n + n^2)
     */
    public List<List<String>> partition2(String s) {
        if (s == null || s.length() <= 0) {
            return Collections.emptyList();
        }

        ArrayList<List<String>> res = new ArrayList<>();
        partition(s.toCharArray(), 0, new ArrayList<>(), res, new Boolean[s.length()][s.length()]);
        return res;
    }

    private void partition(char[] str, int start, List<String> buf, List<List<String>> res, Boolean[][] dp) {
        if (start >= str.length) {
            res.add(new ArrayList<>(buf));
            return;
        }

        for (int i = start; i < str.length; i++) {
            if (isPalindrome(str, start, i, dp)) {
                buf.add(new String(str, start, i - start + 1));
                partition(str, i + 1, buf, res, dp);
                buf.remove(buf.size() - 1);
            }
        }
    }

    private boolean isPalindrome(char[] str, int i, int j, Boolean[][] dp) {
        Boolean flag = dp[i][j];
        if (flag != null) {
            return flag;
        }

        int left = i;
        int right = j;

        while (left < right) {
            if (str[left++] != str[right--]) {
                dp[i][j] = false;
                return false;
            }
        }

        dp[i][j] = true;
        return true;
    }
}
