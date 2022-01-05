package com.lqb.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Trie {

    @Test
    public void test() {
        Trie t = new Trie();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.search("app"));
        System.out.println(t.startsWith("app"));
        t.insert("app");
        System.out.println(t.search("app"));
    }

    /**由于题目规定了字符串中只包含26个字母，因此这里可以优化成一个26长度的数组*/
    /**这里无需用数组，可以用Map来优化搜索*/
    private Map<Character, Trie> links;

    /**用来标记到目前为止是不是一个单词，如trie树中插入“apple”，此时搜索到了“app”它不是一个单词*/
    private boolean isWord;

    /** Initialize your data structure here. */
    public Trie() {
        this.links = new HashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.equals("")) {
            return;
        }

        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (t.links.containsKey(c)) {
                t = t.links.get(c);
                continue;
            }

            Trie temp = new Trie();
            t.links.put(c, temp);
            t = temp;
        }

        t.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = searchTrie(word);
        return trie != null && trie.isWord;
    }

    private Trie searchTrie(String word) {
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!t.links.containsKey(c)) {
                return null;
            }

            t = t.links.get(c);
        }

        return t;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchTrie(prefix) != null;
    }

    public Map<Character, Trie> getLinks() {
        return links;
    }

    public boolean isWord() {
        return isWord;
    }
}
