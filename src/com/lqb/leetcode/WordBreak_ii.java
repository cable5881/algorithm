package com.lqb.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordBreak_ii {

    @Test
    public void test1() {
        List<String> res = wordBreak("catsanddog", Lists.newArrayList("cat", "cats", "and", "sand", "dog"));
        System.out.println(res);
        res = wordBreak("pineapplepenapple", Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(res);
        res = wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat"));
        System.out.println(res);
        res = wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                Lists.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        System.out.println(res);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> res = new LinkedList<>();
        if (s != null && s.length() > 0) {
            wordBreak(s, set, 0, new LinkedList<>(), res, new boolean[s.length() + 1]);
        }
        return res;
    }

    /**
     * 时间复杂度n^2，空间复杂度n
     */
    private boolean wordBreak(String s, Set<String> set, int start, List<String> tmp, List<String> res, boolean[] dp) {
        if (start >= s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tmp.size(); i++) {
                sb.append(tmp.get(i));
                if (i < tmp.size() - 1) {
                    sb.append(" ");
                }
            }
            res.add(sb.toString());
            return true;
        }

        boolean flag = false;
        for (int i = start; i < s.length(); i++) {
            String pre = s.substring(start, i + 1);
            if (set.contains(pre) && !dp[i + 1]) {
                tmp.add(pre);
                if (!wordBreak(s, set, i + 1, tmp, res, dp)) {
                    dp[i + 1] = true;
                } else {
                    flag = true;
                }
                tmp.remove(tmp.size() - 1);
            }
        }

        return flag;
    }

}
