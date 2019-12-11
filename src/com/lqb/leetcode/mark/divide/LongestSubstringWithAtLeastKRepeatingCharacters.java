package com.lqb.leetcode.mark.divide;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ，求T中的每一字符出现次数都不少于 k 。
 * 输出T的长度。
 *
 * 示例 1:
 * 输入:
 * s = "aaabb", k = 3
 * 输出:
 * 3
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 *
 * 示例 2:
 * 输入:
 * s = "ababbc", k = 2
 * 输出:
 * 5
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    @Test
    public void test() {
        //3
        System.out.println(longestSubstring2("aaabb", 3));

        //5
        System.out.println(longestSubstring2("ababbc", 2));

        //3
        System.out.println(longestSubstring2("bbaaacbd", 3));

        //6
        System.out.println(longestSubstring2("aaabbbcdefcdefcde", 3));
    }

    /**
     * 使用数组记录每个位置还剩下多少个相同元素
     */
    public int longestSubstring(String s, int k) {
        int maxLen = 0;
        char[] chars = s.toCharArray();
        int[] dp = getDp(chars);

        int i = 0;
        //如果剩下的长度小于maxLen则直接退出
        while (i < chars.length && (chars.length - i) > maxLen) {
            //如果当前位置的相同字母数量不足K，直接跳过
            if (dp[i] < k) {
                i++;
                continue;
            }

            int j = i;
            int tmpLen = 0;
            //利用一个Map记录本次迭代的子串中字母出现的次数
            Map<Character, Integer> v = new HashMap<>(26);
            while (j < chars.length) {
                if (!v.containsKey(chars[j]) && dp[j] < k) {
                    break;
                }

                int times = v.getOrDefault(chars[j], 0) + 1;
                v.put(chars[j], times);
                j++;
                tmpLen++;

                if (tmpLen >= v.size() * k && check(v, k)) {
                    maxLen = Math.max(tmpLen, maxLen);
                }
            }
            i++;
        }

        return maxLen;
    }

    /**
     * 检查字母出现次数是不是都大于K了
     */
    private boolean check(Map<Character, Integer> v, int k) {
        for (Map.Entry<Character, Integer> entry : v.entrySet()) {
            if (entry.getValue() < k) {
                return false;
            }
        }
        return true;
    }

    int[] getDp(char[] chars) {
        int[] dp = new int[chars.length];
        Map<Character, Integer> map = new HashMap<>(26);
        for (int i = chars.length - 1; i >= 0; i--) {
            int times = map.getOrDefault(chars[i], 0) + 1;
            map.put(chars[i], times);
            dp[i] = times;
        }
        return dp;
    }

    /**
     * 解题思路：递归拆分子串，分治。
     * 先统计出每个字符出现的频次，维护一对双指针，从首尾开始统计，从首尾往中间排除，
     * 如果出现次数小于k则不可能出现在最终子串中，排除并挪动指针，然后得到临时子串，依次从头遍历，
     * 一旦发现出现频次小于k的字符，以该字符为分割线，分别递归求其最大值返回。
     *
     * 作者：tzfun
     * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/javadi-gui-by-tzfun/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int longestSubstring2(String s, int k) {
        return longestSubstring2(s.toCharArray(), 0, s.length() - 1, k);
    }

    private int longestSubstring2(char[] chars, int p1, int p2, int k) {

        if (p2 - p1 + 1 < k) {
            return 0;
        }

        //  统计出现频次
        int[] map = new int[26];
        for (int i = p1; i <= p2; i++) {
            ++map[chars[i] - 'a'];
        }

        //  如果该字符出现频次小于k，则不可能出现在结果子串中
        //  分别排除，然后挪动两个指针
        while (p2 - p1 + 1 >= k && map[chars[p1] - 'a'] < k) {
            p1++;
        }
        while (p2 - p1 + 1 >= k && map[chars[p2] - 'a'] < k) {
            p2--;
        }

        if (p2 - p1 + 1 < k) {
            return 0;
        }

        for (int i = p1; i <= p2; i++) {
            if (map[chars[i] - 'a'] < k) {
                //  如果第i个不符合要求，切分成左右两段分别递归求得
                return Math.max(longestSubstring2(chars, p1, i - 1, k), longestSubstring2(chars, i + 1, p2, k));
            }
        }

        return p2 - p1 + 1;
    }

}
