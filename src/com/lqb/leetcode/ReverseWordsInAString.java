package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWordsInAString {

    @Test
    public void test() {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("a good   example"));
    }

    public String reverseWords(String s) {
        if (s == null || s.trim().equals("")) {
            return s;
        }

        char[] chars = s.toCharArray();

        reverseWords(chars, 0, chars.length - 1);
        int i = 0;
        int j = 0;
        while (j < chars.length) {
            //找到单词末尾
            while (j < chars.length && chars[j] != ' ') {
                j++;
            }
            reverseWords(chars, i, j - 1);
            if (j < chars.length) {
                chars[j++] = ' ';
                while (chars[j] == ' ') {
                    j++;
                }
                i = j;
            }
        }

        return new String(chars);
    }

    private void reverseWords(char[] s, int i, int j) {
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

}
