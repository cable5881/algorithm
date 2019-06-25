package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class IsNumber {

    @Test
    public void test1() {
        Assert.assertEquals(false, isNumber(".e1"));
        Assert.assertEquals(false, isNumber("1e"));
        Assert.assertEquals(true, isNumber("1."));
        Assert.assertEquals(true, isNumber(".2"));
        Assert.assertEquals(false, isNumber("e1"));
        Assert.assertEquals(false, isNumber("123ea"));
        Assert.assertEquals(false, isNumber("1e123456."));
        Assert.assertEquals(false, isNumber("1e123 456."));
        Assert.assertEquals(true, isNumber("1 "));
        Assert.assertEquals(false, isNumber("-."));
        Assert.assertEquals(true, isNumber("46.e3"));
        Assert.assertEquals(false, isNumber("6ee69"));
        Assert.assertEquals(true, isNumber(".2e81"));
        Assert.assertEquals(false, isNumber("-e58"));
    }

    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }

        s = s.trim();
        if(s.length() < 1) {
            return false;
        }

        char[] numArr = s.toCharArray();

        int i = 0;
        if (numArr[0] == '+' || numArr[i] == '-') {
            i++;
            if (numArr.length == 1) {
                return false;
            }
        }

        while (i < numArr.length) {
            if ('0' <= numArr[i] && numArr[i] <= '9') {
                i++;
                continue;
            } else if (numArr[i] == '.') {
                return isCommonDecimal(numArr, i);
            } else if (numArr[i] == 'e') {
                return isSciDecimal(numArr, i);
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isSciDecimal(char[] numArr, int i) {
        if (i - 1 < 0) {
            return false;
        }
        return isInteger(numArr, i + 1, false,  false);
    }

    private boolean isCommonDecimal(char[] numArr, int i) {
        boolean noNumBeforeDot = false;
        if (numArr.length <= 1) {
            return false;
        }

        if(i < 1 || '0' > numArr[i - 1] || numArr[i - 1] > '9') {
            noNumBeforeDot = true;
        }

        if (i == numArr.length - 1) {
            // 排除+.
            if(i > 0 && noNumBeforeDot) {
                return false;
            }
            return true;
        }

        return isInteger(numArr, i + 1, true, noNumBeforeDot);
    }

    private boolean isInteger(char[] numArr, int start, boolean isCommon, boolean noNumBeforeDot) {
        if (start >= numArr.length) {
            return false;
        }

        boolean hasE = false;

        for (int i = start; i < numArr.length; i++) {
            // 64.e3
            if(isCommon && !hasE && numArr[i] == 'e' && i + 1 < numArr.length) {
                // 排除.e2, .2e81
                if(noNumBeforeDot && (numArr[i -1] > '9' || numArr[i - 1] < '0')) {
                    return false;
                }
                hasE = true;
            } else if (numArr[i] > '9' || numArr[i] < '0') {
                return false;
            }
        }

        return true;
    }

}
