package com.lqb.algorithm.offer;

/**
 * ���1~13��������1���ֵĴ���,�����100~1300��������1���ֵĴ�����
 * Ϊ�����ر�����һ��1~13�а���1��������1��10��11��12��13��˹�����6��,���Ƕ��ں�����������û���ˡ�
 * ACMerϣ�����ǰ����,������������ձ黯,���Ժܿ���������Ǹ�����������1���ֵĴ�����
 * 
 * @author:JackBauer
 * @date:2016��6��16�� ����8:40:38
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
