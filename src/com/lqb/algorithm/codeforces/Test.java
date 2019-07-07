package com.lqb.algorithm.codeforces;

import java.util.Scanner;
import java.math.BigInteger;

public class Test {

	public static void main(String[] args) {
		int m, n, a;
		Scanner in = new Scanner(System.in);
		m = in.nextInt();
		n = in.nextInt();
		a = in.nextInt();

		BigInteger result = getLeastFlagstones(m, n, a);
		System.out.println(result);
		
		in.close();
	}

	public static BigInteger getLeastFlagstones(int m, int n, int a) {
		BigInteger x, y;

		if (m <= a) {
			x = BigInteger.valueOf(1);
		} else {
			//x = m % a == 0 ? m/a : (a - m % a) + m;
			x = BigInteger.valueOf(m % a == 0 ? (m / a) : (m / a + 1));
			//1000000000
			//System.out.println("x:" + x);
			//System.out.println("1000000000/192:" + 1000000000/192);
		}

		if (n <= a) {
			y = BigInteger.valueOf(1);
		} else {
			y = BigInteger.valueOf(n % a == 0 ? n / a : n / a + 1);
			//System.out.println("y:" + y);
		}
		
		if (a == 1) {
			x = BigInteger.valueOf(m);
			y = BigInteger.valueOf(n);
		}
		
		return x.multiply(y);
	}

}