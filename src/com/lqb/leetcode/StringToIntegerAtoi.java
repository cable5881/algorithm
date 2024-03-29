package com.lqb.leetcode;

import org.junit.Test;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 *
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 *
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−2^31) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class StringToIntegerAtoi {

    @Test
    public void test() {
        System.out.println(myAtoi2("42"));
        System.out.println(myAtoi2("  -42"));
        System.out.println(myAtoi2("4193 with words"));
        System.out.println(myAtoi2("words and 987"));
        System.out.println(myAtoi2("-91283472332"));
        System.out.println(myAtoi2("2147483648"));
    }

    /**
     * 如果是-912834723，再 * 10 还是负数，且绝对值变小，并不会想象中的变成正数
     */
    public int myAtoi(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }

        int i = trim(str);
        if (i >= str.length()) {
            return 0;
        }

        boolean positive = true;
        char c = str.charAt(i);
        if (c == '+') {
            i++;
            positive = true;
        } else if (c == '-') {
            i++;
            positive = false;
        }

        int min = Integer.MIN_VALUE / 10;
        int max = Integer.MAX_VALUE / 10;
        int base = 0;
        while (i < str.length()) {
            c = str.charAt(i);
            if ('0' <= c && c <= '9') {
                if (positive) {
                    if (base > max) {
                        return Integer.MAX_VALUE;
                    }
                    base = base * 10 + (c - '0');
                } else {
                    if (base < min) {
                        return Integer.MIN_VALUE;
                    }
                    base = base * 10 - (c - '0');
                }
                if (isOverflow(positive, base)) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                i++;
            } else {
                break;
            }
        }

        return base;
    }

    private boolean isOverflow(boolean positive, int base) {
        return positive ? base < 0 : base > 0;
    }

    private int trim(String str) {
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) != ' ') {
                break;
            }
            i++;
        }
        return i;
    }

    public int myAtoi2(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        int res = 0;
        int start = 0;
        boolean positive = true;
        while (start < str.length()) {
            char ch = str.charAt(start);
            if (ch == ' ') {
                start++;
                continue;
            }
            if (ch == '-') {
                positive = false;
                start++;
                break;
            }
            if (ch == '+') {
                start++;
                break;
            }
            if (ch >= '0' && ch <= '9') {
                break;
            }
            return 0;
        }

        if (start >= str.length()) {
            return 0;
        }

        for (int i = start; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') {
                break;
            }
            res = res * 10 + (ch - '0');
        }

        return positive ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
    }

}
