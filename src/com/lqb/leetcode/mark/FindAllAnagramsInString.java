package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.*;

/**
 * 找到字符串中所有字母异位词
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@SuppressWarnings("ALL")
public class FindAllAnagramsInString {

    @Test
    public void test() {
        System.out.println(findAnagrams4("cbaebabacd", "abc"));
        System.out.println(findAnagrams4("abab", "ab"));
        System.out.println(findAnagrams4("cbaebabacd", "faz"));
        System.out.println(findAnagrams4("cbaebabacd", "a"));
        System.out.println(findAnagrams4("baa", "aa"));
        System.out.println(findAnagrams4("bpaa", "aa"));
    }

    /**
     * 暴力法，也可以认为是滑动窗口
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        char[] pChars = p.toCharArray();
        int pLen = pChars.length;
        Map<Character, Integer> pMap = new HashMap<>(pLen);
        for (char pChar : pChars) {
            int cnt = pMap.getOrDefault(pChar, 0);
            pMap.put(pChar, ++cnt);
        }

        char[] sChars = s.toCharArray();
        boolean lastAnagrams = false;
        for (int i = 0; i <= sChars.length - pLen; i++) {
            int start = i;
            int end = i + pLen - 1;
            if (!pMap.containsKey(sChars[start])) {
                lastAnagrams = false;
                continue;
            }
            if (i > 0 && lastAnagrams) {
                if (sChars[start - 1] == sChars[end]) {
                    res.add(i);
                } else {
                    lastAnagrams = false;
                }
            } else if (isAnagrams(sChars, start, end, new HashMap<>(pMap))) {
                res.add(i);
                lastAnagrams = true;
            } else {
                lastAnagrams = false;
            }
        }
        return res;
    }

    private boolean isAnagrams(char[] sChars, int start, int end, Map<Character, Integer> pMap) {
        for (int i = start; i <= end; i++) {
            int cnt = pMap.getOrDefault(sChars[i], 0);
            if (--cnt < 0) {
                return false;
            }
            pMap.put(sChars[i], cnt);
        }
        return true;
    }

    /**
     * 优化后的滑动窗口v1
     * 时间复杂度：O(sLen * 26)
     * 空间复杂度：O(26 * 2)
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for (int i = 0; i < pChars.length; i++) {
            pCnt[pChars[i] - 'a']++;
            sCnt[sChars[i] - 'a']++;
        }
        if (Arrays.equals(pCnt, sCnt)) {
            res.add(0);
        }
        for (int i = 0; i < sChars.length - p.length(); i++) {
            sCnt[sChars[i] - 'a']--;
            sCnt[sChars[i + p.length()] - 'a']++;
            if (Arrays.equals(pCnt, sCnt)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    /**
     * 优化后的滑动窗口v2，统计不同的字母数
     * 时间复杂度：O(sLen)
     * 空间复杂度：O(26 * 2)
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        int[] cnt = new int[26];
        for (int i = 0; i < pChars.length; i++) {
            cnt[pChars[i] - 'a']--;
            cnt[sChars[i] - 'a']++;
        }
        //统计不同的字母有多少个。
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                // 注意这里不能是diff直接++
                // 因为如果某个字母出现多次，这里就累加了一次，如cnt[0]=-5，表示a需要5个
                diff += Math.abs(c);
            }
        }
        if (diff == 0) {
            res.add(0);
        }

        //滑动窗口移动的过程中，窗口左边的统计值cnt--，窗口右边的统计值cnt++
        //如果左边的统计值cnt--后比原来小(绝对值)，说明不同的字母在减少，则diff--，否则++
        //如果右边的统计值cnt++后比原来小(绝对值)，说明不同的字母在减少，则diff--，否则++
        for (int i = 0; i < sChars.length - pChars.length; i++) {
            int l = sChars[i] - 'a';
            int lastLeft = cnt[l];
            if (Math.abs(--cnt[l]) < Math.abs(lastLeft)) {
                diff--;
            } else {
                diff++;
            }

            int r = sChars[i + pChars.length] - 'a';
            int lastRight = cnt[r];
            if (Math.abs(++cnt[r]) < Math.abs(lastRight)) {
                diff--;
            } else {
                diff++;
            }

            if (diff == 0) {
                res.add(i + 1);
            }
        }

        return res;
    }

    /**
     * 最佳的滑动窗口，官方的最佳解法
     */
    public List<Integer> findAnagrams4(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }

        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }

        if (differ == 0) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            int l = s.charAt(i) - 'a';
            --count[l];
            // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
            if (count[l] == 0) {
                --differ;
            } else if (count[l] == -1) {
                // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }

            int r = s.charAt(i + pLen) - 'a';
            ++count[r];
            // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
            if (count[r] == 0) {
                --differ;
            } else if (count[r] == 1) {
                // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }

            if(differ == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
