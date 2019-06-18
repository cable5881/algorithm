package com.lqb.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Author liqibo
 * @Date 2019/6/11 19:52
 **/
public class LengthOfLongestSubstring {

    @Test
    public void test1() {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    @Test
    public void test2() {
        String s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    @Test
    public void test3() {
        String s = "pwwkewck";
        System.out.println(lengthOfLongestSubstring3(s));
    }

    @Test
    public void test4() {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring3(s));
    }

    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //这里为何不直接取map.get(s.charAt(j)？测试用例见abba。
                //当检测到abb时，i=3，检测到abba时，如果直接用map.get(s.charAt('a')则i=1，那么下面计算j-i+1=3-1+1=3就错了
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 通过使用 HashSet 作为滑动窗口，我们可以用 O(1)的时间来完成对字符是否在当前的子字符串中的检查。
     * 滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     * 即 [i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。
     * 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。
     * 直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。
     * 如果我们对所有的 ii 这样做，就可以得到答案。
     **/
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> uniqueWords = new HashSet<>();
        char[] chars = s.toCharArray();
        int maxLen = 0;

        for (int i = 0; i < chars.length; i++) {
            int tempLen = 0;
            for (int j = i; j < chars.length; j++) {
                if (uniqueWords.contains(chars[j])) {
                    break;
                }

                uniqueWords.add(chars[j]);
                tempLen++;
            }

            if (tempLen > maxLen) {
                maxLen = tempLen;
            }
            uniqueWords.clear();
        }


        return maxLen;
    }

}
