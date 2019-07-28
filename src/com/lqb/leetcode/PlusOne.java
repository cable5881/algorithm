package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlusOne {

    @Test
    public void test() {
        System.out.println(Arrays.toString(plusOne2(new int[] {1,2,3})));
        System.out.println(Arrays.toString(plusOne2(new int[] {4,3,2,1})));
        System.out.println(Arrays.toString(plusOne2(new int[] {4,9,9,9})));
        System.out.println(Arrays.toString(plusOne2(new int[] {9,9,9,9})));
        System.out.println(Arrays.toString(plusOne2(new int[] {9})));
    }

    /**
     * 自己的解法, 没有官网推荐的好
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];

        int carry = 1;
        for (int i = digits.length - 1, j = ans.length - 1; i >= 0; i--, j--) {
            carry += digits[i];
            if (carry > 9) {
                ans[j] = carry - 10;
                carry = 1;
            } else {
                ans[j] = carry;
                carry = 0;
            }
        }

        // 容易忘记
        if (carry == 1) {
            ans[0] = 1;
        }

        return Arrays.copyOfRange(ans, ans[0] == 0 ? 1 : 0, ans.length);
    }

    /**
     * 官方推荐解法: 延迟数组长度 + 1, 且可以提前返回
     */
    public int[] plusOne2(int[] digits) {

        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry += digits[i];
            if (carry > 9) {
                digits[i] = carry - 10;
                carry = 1;
            } else {
                digits[i] = carry;
                //后面不可能有进位了, 直接返回
                return digits;
            }
        }

        //如果到达了这里说明肯定还有进位且肯定是10的倍数
        //将数组长度 + 1延迟到这里
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

}
