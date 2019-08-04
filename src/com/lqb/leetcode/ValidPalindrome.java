package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidPalindrome {

    @Test
    public void test() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("r"));
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome(" *&%"));
    }

    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftC = s.charAt(left);
            if (!Character.isLetterOrDigit(leftC)) {
                left++;
                continue;
            } else {
                leftC = Character.toLowerCase(leftC);
            }

            char rightC = s.charAt(right);
            if (!Character.isLetterOrDigit(rightC)) {
                right--;
                continue;
            } else {
                rightC = Character.toLowerCase(rightC);
            }

            if (leftC != rightC) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}
