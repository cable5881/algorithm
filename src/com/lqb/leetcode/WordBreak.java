package com.lqb.leetcode;

import com.google.common.collect.Lists;
import com.lqb.util.Trie;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/word-break
 **/
public class WordBreak {

    @Test
    public void test() {
        //true
        System.out.println(wordBreak3("leetcode", Lists.newArrayList("leet", "code")));

        //true
        System.out.println(wordBreak3("applepenapple", Lists.newArrayList("apple", "pen")));

        //false
        System.out.println(wordBreak3("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")));

        //true
        System.out.println(wordBreak3("bb", Lists.newArrayList("a","b","bbb","bbbb")));

        //true
        System.out.println(wordBreak3("aaaaaaa", Lists.newArrayList("aaaa","aaa")));

        //true
        System.out.println(wordBreak3("aaabbb", Lists.newArrayList("aaa","aaabbb")));

        //false
        System.out.println(wordBreak3("aaaaaaa", Lists.newArrayList("aaaa","aa")));

        //false
        System.out.println(wordBreak3("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Lists.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa", "aaaaaaaaaa")));
    }

    @Test
    public void test1() {
        ConcurrentLinkedQueue<Object> q = new ConcurrentLinkedQueue<>();
        q.add("xxx");
        System.out.println(q.poll());
        System.out.println(q.poll());
    }

    /**
     * @author liqibo
     * @date 2019/11/6 17:06
     * @description 使用trie树 + 广度优先搜索 + 动态规划
     * 使用广度优先是因为需要有些场景是需要回退的，如"aaaaaaa" - ["aaaa","aaa"]，它每次都会匹配到"aaa"就切分一次，导致最后只剩下一个"a"
     * 因此当检测到切分为"aaa"不过时，应该接着试探"aaaa"的可能
     * 这还不够，测试用例会超时，需要再加上动态规划，曾经切分过的位置保存起来，下次递归就能提前结束了
     *
     * 时间复杂度 最坏的情况情况是s的每个子串都去匹配，也就是 O(n²)
     * 空间复杂度，Trie树 + 长度为n的dp数组 + 递归产生的栈
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        return search(s, 0, trie, new int[s.length()]);
    }

    private boolean search(String s, int i, Trie trie, int[] dp) {
        Trie t = trie;
        Map<Character, Trie> links = t.getLinks();
        for (int j = i; j < s.length(); j++) {
            //先判断isWord()再判断containsKey，否则如果存在下一个字母则会一直迭代下去，无法进行广度优先
            //dp[j] == 0表示j这个位置没有切分过
            if (t.isWord() && dp[j] == 0) {
                if (search(s, j, trie, dp)) {
                    //如果切分成功不需要再记录一个成功值到dp了，因为return true会一直到最顶层直接结束程序
                    return true;
                } else {
                    //说明这个位置切分字符串不可行，进行记录
                    dp[j] = 1;
                }
            }

            if (links.containsKey(s.charAt(j))) {
                t = links.get(s.charAt(j));
                links = t.getLinks();
            } else {
                return false;
            }
        }

        return t.isWord();
    }

    /**
     * @author liqibo
     * @date 2019/11/6 20:15
     * @description 不使用trie树的动态规划,使用set过滤
     * 时间复杂度 O(n²)
     * 空间复杂度 O(n)
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        return wordBreak2(s, 0, new boolean[s.length()], set);
    }

    private boolean wordBreak2(String s, int i, boolean[] dp, HashSet<String> set) {
        if (i >= s.length()) {
            return true;
        }

        for (int j = i; j < s.length(); j++) {
            if (set.contains(s.substring(i, j + 1)) && !dp[j]) {
                if (wordBreak2(s, j + 1, dp, set)) {
                    return true;
                } else {
                    dp[j] = true;
                }
            }
        }

        return false;
    }

    /**
     * @author liqibo
     * @date 2019/11/6 20:45
     * @description 官方版本动态规划
     * 时间复杂度 O(n²)
     * 空间复杂度 O(n)
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);

        //表示某位置可以切成单词
        //但是前提是这个单词前面的字符串可以被切成单词
        //如第三个测试用例，catsandog，因为cats之前被切成单词了（dp[4]=true），所以and可以被切成单词
        //但是dog不能被切为单词，因为catsan没有被切为单词，即dp[6]=false
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        //遍历是顺序是
        //c
        //ca、a
        //cat dp[3]=true(因为dp[0]是true)
        //cats dp[4]=true(因为dp[0]是true)
        //catsa、atsa、tsa、sa、a
        //catsan、atsan、tsan、san、an、n
        //catsand dp[7]=true(因为dp[3]是true)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                //只有前面的可以被切成单词(dp判断)才进行contains判断
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        //如果最后一个单词可以被切，则前面的单词也可以被切，那么整个字符串就可以被切
        return dp[s.length()];
    }

    /**
     * @author liqibo
     * @date 2019/11/20 17:03
     * @description 和wordBreak2原理一样
     */
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        return wordBreak4(s, 0, new int[s.length()], set);
    }

    /**
     * @author liqibo
     * @date 2019/11/20 19:14
     * @description 核心递归
     */
    private boolean wordBreak4(String s, int start, int[] dp, Set<String> set) {

        if (start >= s.length()) {
            return true;
        } else if (dp[start] == 1) {
            return false;
        }

        for (int i = start; i < s.length(); i++) {
            //如果当前位置能切成单词，且剩下的也能切成单词则返回true
            if (set.contains(s.substring(start, i + 1)) && wordBreak4(s, i + 1, dp, set)) {
                return true;
            }
        }

        //到这说明从start开始到结束都没有一个有效的切点能切成单词，则当前位置记录为1，返回false
        dp[start] = 1;
        return false;
    }

}

