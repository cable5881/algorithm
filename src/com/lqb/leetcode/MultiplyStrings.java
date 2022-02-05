package com.lqb.leetcode;

import org.junit.Test;

/**
 * 字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *  
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MultiplyStrings {

    @Test
    public void add() {
        System.out.println(add("3", "6"));
        System.out.println(add("10", "66"));
        System.out.println(add("66", "100"));
        System.out.println(add("163", "169"));
    }

    @Test
    public void test() {
        System.out.println(multiply("3", "6"));
        System.out.println(multiply("0", "66"));
        System.out.println(multiply("66", "0"));
        System.out.println(multiply("11", "11"));
        System.out.println(multiply("100", "33"));
        System.out.println(multiply("20", "20"));
    }

    public String multiply(String num1, String num2) {

        //下面有两层for循环，外循环比较耗时，所以让长度较短的放在外层
        if (num1.length() > num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int b = num2.charAt(i) - '0';
            int carry = 0;
            StringBuilder tmp = new StringBuilder();
            for (int j = num1.length() - 1; j >= 0 ; j--) {
                int a = num1.charAt(j) - '0';
                int c = a * b + carry;
                int mod = c % 10;
                carry = c / 10;
                tmp = tmp.append(mod);
            }
            if (carry > 0) {
                tmp = tmp.append(carry);
            }
            tmp = tmp.reverse();
            for (int k = 1; k < num2.length() - i; k++) {
                tmp.append('0');
            }
            res = add(res, tmp.toString());
        }

        return res;
    }

    private String add(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0) {
            int a = num1.charAt(i) - '0';
            int b = num2.charAt(j) - '0';
            int c = a + b + carry;
            int mod = c % 10;
            carry = c / 10;
            res = res.append(mod);
            i--;
            j--;
        }

        while (j >= 0) {
            int b = num2.charAt(j) - '0';
            int c = b + carry;
            int mod = c % 10;
            carry = c / 10;
            res = res.append(mod);
            j--;
        }

        if (carry > 0) {
            res = res.append(carry);
        }

        return res.reverse().toString();
    }
}
