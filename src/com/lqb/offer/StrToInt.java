package com.lqb.offer;

import org.junit.Test;

/**
 * 将一个字符串转为数字
 *
 * @author:JackBauer
 * @date:2016年6月27日 下午6:33:43
 */
public class StrToInt {

    @Test
    public void test() {
        System.out.println(strToInt2("+123456789"));
        System.out.println(strToInt2("-123456789"));
        System.out.println(strToInt2("-"));
        System.out.println(strToInt2("+"));
        System.out.println(strToInt2("+$$"));
        System.out.println(strToInt2("$&"));
        System.out.println(strToInt2("+2147483647"));
        System.out.println(strToInt2("+2147483648"));
        System.out.println(strToInt2("-2147483648"));
        System.out.println(strToInt2("-2147483649"));
    }

    private boolean isValid = true;

    public int strToInt(String str) {
        if (str == null || str.trim().equals("")) {
            isValid = false;
            return 0;
        }

        char[] numCh = str.toCharArray();

        boolean isPositive = true;
        int start = 0;

        if (numCh[0] == '-') {
            isPositive = false;
            start++;
        } else if (numCh[0] == '+') {
            start++;
        }

        long num = 0;

        while (start < numCh.length) {
            if (numCh[start] <= '9' && numCh[start] >= '0') {
                num = fillLastBit(num, numCh[start], isPositive);
                start++;
            } else {
                isValid = false;
            }

            if (!isValid) {
                return 0;
            }
        }

        return Integer.valueOf(Long.toString(num));
    }

    private long fillLastBit(long num, char ch, boolean isPositive) {
        if (isPositive) {
            num = num * 10 + (ch - '0');
            if (num > Integer.MAX_VALUE) {
                isValid = false;
                return 0;
            }
        } else {
            num = num * 10 - (ch - '0');
            if (num < Integer.MIN_VALUE) {
                isValid = false;
                return 0;
            }
        }

        return num;
    }

    /**
     * 20200221
     * 可以支持前后空格，且12acc这种是转换为12
     */
    public int strToInt2(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }

        char[] num = str.toCharArray();
        int res = 0;
        boolean positive = true;
        int i = 0;
        //先判断正数还是负数
        while (i < num.length) {
            if (num[i] == ' ') {
                i++;
            } else if (num[i] == '+') {
                i++;
                break;
            } else if (num[i] == '-') {
                i++;
                positive = false;
                break;
            } else if (num[i] <= '9' && num[i] >= '0') {
                break;
            } else {
                return res;
            }
        }

        while (i < num.length) {
            if (num[i] <= '9' && num[i] >= '0') {
                //res在×10过程中也有可能溢出，所以也要有一次溢出判断
                res = res * 10;
                if (!overflow(positive, res)) {
                    //注意不能统一用加法，然后最后返回结果的时候判断正负
                    //因为负数绝对值|-2147483648| > |+2147483647|
                    //也就是说如果是负数的话可以达到+2147483648，而实际上则允许
                    res = positive ? res + (num[i] - '0') : res - (num[i] - '0');
                } else {
                    return 0;
                }
                i++;

                //代码上面稍微优化了一下，其实差不多，就是复用一下overflow函数
                // if (positive && res >= 0) {
                //     res += (num[i] - '0');
                // } else if (!positive && res <= 0) {
                //     res -= (num[i] - '0');
                // } else {
                //     return 0;
                // }
                // i++;
            } else {
                break;
            }

            //res累加完后再做一次判断
            if (overflow(positive, res)) {
                return 0;
            }
        }

        return res;
    }

    private boolean overflow(boolean positive, int res) {
        return positive ? res < 0 : res > 0;
    }
}
