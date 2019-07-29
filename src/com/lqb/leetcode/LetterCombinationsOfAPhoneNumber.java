package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LetterCombinationsOfAPhoneNumber {

    private static final Map<Character, char[]> numLetterMap = new HashMap<>(8);

    static {
        numLetterMap.put('2', new char[]{'a','b','c'});
        numLetterMap.put('3', new char[]{'d','e','f'});
        numLetterMap.put('4', new char[]{'g','h','i'});
        numLetterMap.put('5', new char[]{'j','k','l'});
        numLetterMap.put('6', new char[]{'m','n','o'});
        numLetterMap.put('7', new char[]{'p','q','r', 's'});
        numLetterMap.put('8', new char[]{'t','u','v'});
        numLetterMap.put('9', new char[]{'w','x','y','z'});
    }

    @Test
    public void test1() {
        System.out.println(letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() <= 0) {
            return Collections.emptyList();
        }

        char[] numbers = digits.toCharArray();
        List<String> combinations = new ArrayList<>();
        letterCombinations(numbers, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private void letterCombinations(char[] numbers, int i, StringBuilder sb, List<String> combinations) {

        if (i >= numbers.length) {
            combinations.add(sb.toString());
            return;
        }

        char[] letters = numLetterMap.get(numbers[i]);
        for (char c : letters) {
            sb.append(c);
            letterCombinations(numbers, i + 1, sb, combinations);
            sb.deleteCharAt(i);
        }

    }


}
