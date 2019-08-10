package com.lqb.leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidAnagram {

    @Test
    public void test() {
        System.out.println(isAnagram("anagram" , "nagaram"));
        System.out.println(isAnagram("rat" , "car"));
        System.out.println(isAnagram(null , "car"));
        System.out.println(isAnagram(null , null));
        System.out.println(isAnagram("anagramanagram" , "nagaram"));
    }

    /**
     * 自己的解法 空间复杂度O(n), 时间复杂度O(2n)
     */
    public boolean isAnagram(String s, String t) {

        if(s == null && t == null) {
            return true;
        }

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            Integer times = map.getOrDefault(t.charAt(i), 0) - 1;
            if (times < 0) {
                return false;
            }
            map.put(t.charAt(i), times);
        }

        return true;
    }

    /**
     * 官方解法 空间复杂度O(26), 时间复杂度O(2n)
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 官方解法2 空间复杂度O(26), 时间复杂度O(n + 26)
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] has = new int[26];
        for (int i = 0; i < s.length(); i++) {
            has[s.charAt(i) - 'a']++;
            has[t.charAt(i) - 'a']--;
        }
        for (int i : has) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

}
