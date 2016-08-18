package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * 编写一个函数，确定需要改变几个位，才能将整数A转变成整数B。
 * 
 * 给定两个整数int A，int B。请返回需要改变的数位个数。 测试样例：
 * 
 * 10,5
 * 
 * 返回：4
 * 
 * 
 */
public class BitSwapRequired {

	public static void main(String[] args) {
		System.out.println(calcCost2(10, 5));

	}

	public static int calcCost(int A, int B) {
		int count = 0;
		for (int c = A ^ B; c != 0; c >>= 1) {
			if ((c & 1) != 0) {
				count++;
			}
		}

		return count;
	}

	public static int calcCost2(int A, int B) {
		int count = 0;
		for (int c = A ^ B; c != 0; c = c & (c - 1)) {
			count++;
		}

		return count;
	}

}
