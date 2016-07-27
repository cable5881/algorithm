package com.lqb.algorithm.offer;

/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
 * 
 * @author:JackBauer
 * @date:2016年6月16日 下午8:40:38
 */
public class NumberOf1Between1AndN {

	public static void main(String[] args) {

		System.out.println(numberOf1Between1AndN(81345));

	}

	public static int numberOf1Between1AndN(int i) {

		if (i < 1) {
			return 0;
		} else if (i < 10) {
			return 1;
		}

		String numString = String.valueOf(i);
		int length = numString.length();

		int firstBit = Integer.valueOf(numString.substring(0, 1));
		int timesOfOneOfFirstBit;

		if (firstBit > 1) {
			timesOfOneOfFirstBit = powerOf10(length - 1);
		} else {
			timesOfOneOfFirstBit = Integer.valueOf(numString.substring(1)) + 1;
		}

		int timesOfOneWithoutFirstBit = firstBit * (length - 1) * powerOf10(length - 2);

		int num = Integer.valueOf(numString.substring(1));
		int remainingTimes = numberOf1Between1AndN(num);

		return timesOfOneOfFirstBit + remainingTimes + timesOfOneWithoutFirstBit;
	}

	private static int powerOf10(int n) {
		int result = 1;
		for (int i = 0; i < n; i++) {
			result *= 10;
		}

		return result;
	}

}
