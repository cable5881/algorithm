package com.lqb.algorithm.offer;

import java.util.ArrayList;

/**
 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。 例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * 
 * @author:JackBauer
 * @date:2016年6月17日 下午9:41:50
 */
public class UglyNumber {

	public static void main(String[] args) {
		// System.out.println(GetUglyNumber_Solution(0));
		// System.out.println(GetUglyNumber_Solution(1));
		// System.out.println(GetUglyNumber_Solution(2));
		// System.out.println(GetUglyNumber_Solution(3));
		// System.out.println(GetUglyNumber_Solution(4));
		// System.out.println(GetUglyNumber_Solution(5));
		// System.out.println(GetUglyNumber_Solution(6));
		// System.out.println(GetUglyNumber_Solution(7));
		// System.out.println(GetUglyNumber_Solution(8));
		// System.out.println(GetUglyNumber_Solution(9));
		// System.out.println(GetUglyNumber_Solution(10));
		// System.out.println(GetUglyNumber_Solution(11));
		// System.out.println(GetUglyNumber_Solution(12));
		System.out.println(GetUglyNumber_Solution(1500));
		// System.out.println(GetUglyNumber_Solution2(1500));
	}

	public static int GetUglyNumber_Solution(int index) {

		if (index < 1) {
			return 0;
		} else if (index == 1) {
			return 1;
		}

		int[] uglyNumbers = new int[index];
		uglyNumbers[0] = 1;

		int multiplyStart_1 = 0;
		int multiplyStart_2 = 0;
		int multiplyStart_3 = 0;

		int numOfUglyNumber = 1;
		int lastSmallest = uglyNumbers[0];

		while (numOfUglyNumber < index) {
			int smallest = smallestOfThreeNum(uglyNumbers[multiplyStart_1] * 2, uglyNumbers[multiplyStart_2] * 3,uglyNumbers[multiplyStart_3] * 5);
			
			uglyNumbers[numOfUglyNumber] = smallest;
			numOfUglyNumber++;
			lastSmallest = smallest;

			while (uglyNumbers[multiplyStart_1] * 2 <= smallest) {
				multiplyStart_1++;
			}
			while (uglyNumbers[multiplyStart_2] * 3 <= smallest) {
				multiplyStart_2++;
			}
			while (uglyNumbers[multiplyStart_3] * 5 <= smallest) {
				multiplyStart_3++;
			}

			// if (smallest == uglyNumbers[multiplyStart_1] * 2) {
			// multiplyStart_1++;
			// } else if (smallest == uglyNumbers[multiplyStart_2] * 3) {
			// multiplyStart_2++;
			// } else if (smallest == uglyNumbers[multiplyStart_3] * 5) {
			// multiplyStart_3++;
			// }
		}

		return lastSmallest;
	}

	public static int smallestOfThreeNum(int num1, int num2, int num3) {
		int min = num1 < num2 ? num1 : num2;
		return min < num3 ? min : num3;
	}

	public static ArrayList<Integer> GetUglyNumber_Solution2(int n) {
		if (n == 0)
			return null;
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(1);
		int i2 = 0, i3 = 0, i5 = 0;
		while (res.size() < n) {
			int m2 = res.get(i2) * 2;
			int m3 = res.get(i3) * 3;
			int m5 = res.get(i5) * 5;
			int min = Math.min(m2, Math.min(m3, m5));
			res.add(min);
			if (min == m2)
				i2++;
			if (min == m3)
				i3++;
			if (min == m5)
				i5++;
		}
		return res;
	}

}
