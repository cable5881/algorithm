package com.lqb.netease.recruit2017;

import java.util.Scanner;

public class BinaryPriority {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			System.out.println(fun(n));
		}

		in.close();

		// System.out.println(getNumOf1(1000000000));
		// System.out.println(getNumOf1(1000000512));
	}

	public static int fun(int n) {
		if (n < 0) {
			return -1;
		}

		int big = n + 1;
		int t = getNumOf1(n);
		while (true) {
			if (getNumOf1(big) == t) {
				return big;
			} else if (big < Integer.MAX_VALUE) {
				big++;
			} else {
				return -1;
			}
		}
		
	}

	private static int getNumOf1(int n) {
		int count = 0;

		while (n != 0) {
			count++;
			n = n & (n - 1);
		}

		return count;
	}

}
