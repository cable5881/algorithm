package com.lqb.leetcode.mark;

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

    /**
     * 官方解法二：优化的滑动窗口
     * 官方解法一最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。
     * 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
     * 当我们找到重复的字符时，我们可以立即跳过该窗口。
     * 也就是说，如果 s[j] 在 [i, j)范围内有与 j' 重复的字符，我们不需要逐渐增加 i 。
     * 我们可以直接跳过 [i，j'] 范围内的所有元素，并将 i 变为 j' + 1
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //这里为何不直接取map.get(s.charAt(j)？测试用例见abba。
                //当检测到abb时，i=3，检测到abba时，如果直接用map.get(s.charAt('a')则i=1，那么下面计算j-i+1=3-1+1=3就错了
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //不能j - i，考虑s="a"或"ab"这种不重复的用例，最后ans=s.length-1， 即为0或1
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 官方解法一：通过使用 HashSet 作为滑动窗口，我们可以用 O(1)的时间来完成对字符是否在当前的子字符串中的检查。
     * 滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     * 即 [i, j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i, j) 向右滑动 1 个元素，则它将变为 [i+1, j+1)（左闭，右开）。
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。
     * 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。
     * 直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     * 如果我们对所有的 i 这样做，就可以得到答案。
     *
     * 时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次(如bbbb这种情况)。
     * 空间复杂度：O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k) 的空间，其中 kk 表示 Set 的大小。
     * 而 Set 的大小取决于字符串 n 的大小以及字符集 / 字母 m 的大小。
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
                //如果set中已经存在，说明当前子串已经存在重复，那么滑动窗口最左向右移动一格
                //注意这里只是i++, 没有j++, 这也就是说下一次迭代, 如果子串仍然重复,
                //则接着滑动窗口最左向右移动一格. 考虑的是pww这种情况, 先去掉p, 后去掉w, 下一次迭代发现窗口已经不存在w了, 则又一次添加了w
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * @description 自己的解法，算法复杂度O(n²)，空间复杂度O(n)
     * @author liqibo
     * @date 2019/7/8 16:59
     **/
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


    @Test
    public void test1() {
        System.out.println(lengthOfLongestSubstring3(""));//0
        System.out.println(lengthOfLongestSubstring3(" "));//1
        System.out.println(lengthOfLongestSubstring3("a"));//1
        System.out.println(lengthOfLongestSubstring3("ab"));//2
        System.out.println(lengthOfLongestSubstring3("abcabcbb"));//3
        System.out.println(lengthOfLongestSubstring3("pwwkewck"));//4
        System.out.println(lengthOfLongestSubstring3("bbbbb"));//1
        System.out.println(lengthOfLongestSubstring3("abba"));//2
    }

}
