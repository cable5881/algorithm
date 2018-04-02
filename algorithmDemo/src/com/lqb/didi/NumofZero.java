package com.lqb.didi;

import java.util.Scanner;

/**
 * 计算 n 阶乘中 0 的个数
 * @author:JackBauer
 * @date:2016年9月18日 下午8:56:59
 */
public class NumofZero {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			System.out.println(fun2(n));
		}

	}

	public static int numOfZero(int n) {
		return n / 5;
	}

	/**
	 * Z(0) = N/5 + N /(5*5) + N/(5*5*5).....直到N/(5的K次方)等于0
	 * 公式中 N/5表示不大于N的数中能被5整除的数贡献一个5，N/(5*5)表示不大于N的数中能被25整除的数再共享一个5.......
	 * 
	 * @param n
	 * @return
	 */
	public static int fun2(int n) {
		int num = 0;

		while (n > 0) {
			num += n / 5;
			n = n / 5;
		}

		return num;
	}
}
