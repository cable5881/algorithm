package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 *
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31−1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseInteger {

    @Test
    public void test() {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(0));
        System.out.println(reverse(1));
        System.out.println(reverse(-1));
        System.out.println(reverse(1534236469));

        //这个是可以翻转的
        System.out.println(reverse(-2147483412));

        //int的最大值和最小值，都不能被翻转
        System.out.println(reverse(2147483647));
        System.out.println(reverse(-2147483648));
    }

    public int reverse(int x) {

        int base = 0;
        int left = x;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        boolean positive = x >= 0;

        while (left != 0) {

            //先判断溢出，再进行base的累加
            //否则不能翻转2147483412，因为累加完后再判断就溢出了
            // 这个判断保证了下面的累加可以进行
            //不能base >= max，因为如果base=max时，下面的base*10 + i还是有可能不溢出的
            if (positive && (base < 0 || base > max)) {
                return 0;
            } else if (!positive && (base > 0 || base < min)) {
                return 0;
            }

            int i = left % 10;
            left = left / 10;
            base = base * 10 + i;

            //如果此时left=0那么这里就退出了，此时base有可能溢出么？
            //不可能，因为它是翻转，在中间就已经return 0了，不同与strToInt
        }


        return base;
    }

}
