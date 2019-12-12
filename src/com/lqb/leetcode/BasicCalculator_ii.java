package com.lqb.leetcode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格。
 * 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BasicCalculator_ii {

    @Test
    public void test() {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
    }

    /**
     * 自己的解法，使用栈存储相加的数，一个变量存储加减符号的数量
     */
    public int calculate(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        LinkedList<Integer> nums = new LinkedList<>();
        int adders = 0;
        char[] chs = s.toCharArray();
        int i = 0;
        while (i < chs.length) {
            if (chs[i] == ' ') {
                i++;
                continue;
            }

            if (chs[i] >= '0' && chs[i] <= '9') {
                i = getNextNum(chs, i, nums);
            } else if (chs[i] == '*' || chs[i] == '/') {
                //如果是 * 或者 / 则直接计算然后存入栈
                boolean mul = chs[i] == '*';
                i = getNextNum(chs, i + 1, nums);
                int n1 = nums.removeLast();
                int n2 = nums.removeLast();
                nums.addLast(mul ? n1 * n2 : n2 / n1);
            } else if (chs[i] == '+' || chs[i] == '-') {
                adders++;
                //如果是 - 则将数字转成负数存入栈
                i = getNextNum(chs, i + 1, nums, chs[i] == '-');
            }
        }

        while (!nums.isEmpty() && adders > 0) {
            Integer n1 = nums.removeLast();
            Integer n2 = nums.removeLast();
            nums.addLast(n1 + n2);
            adders--;
        }

        return nums.removeLast();
    }

    private int getNextNum(char[] chs, int i, LinkedList<Integer> nums) {
        return getNextNum(chs, i, nums, false);
    }

    private int getNextNum(char[] chs, int i, LinkedList<Integer> nums, boolean negative) {
        int res = 0;

        //去除获取数字前的空格
        while (i < chs.length && chs[i] == ' ') {
            i++;
        }

        while (i < chs.length && chs[i] >= '0' && chs[i] <= '9') {
            res = res * 10 + chs[i] - '0';
            i++;
        }

        nums.addLast(negative ? res * -1 : res);
        return i;
    }

    /**
     * 方法二，与方法一类似，操作符另外设置一个栈，当遇到大于等于栈顶操作符时，先弹出栈顶进行计算后再压栈
     */

}
