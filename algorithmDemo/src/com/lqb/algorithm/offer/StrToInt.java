package com.lqb.algorithm.offer;

/**
 * 将一个字符串转为数字
 * @author:JackBauer
 * @date:2016年6月27日 下午6:33:43
 */
public class StrToInt {

	public static void main(String[] args) {
		// System.out.println(Integer.MAX_VALUE + 1);
		// System.out.println(Integer.MIN_VALUE);

		StrToInt test = new StrToInt();
		System.out.println(test.strToInt("+123456789"));
		System.out.println(test.strToInt("-123456789"));
//		System.out.println(test.strToInt("-"));
//		System.out.println(test.strToInt("+"));
//		System.out.println(test.strToInt("+$$"));
//		System.out.println(test.strToInt("$&"));
//		System.out.println(test.strToInt("+2147483648"));
//		System.out.println(test.strToInt("-2147483649"));
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
}
