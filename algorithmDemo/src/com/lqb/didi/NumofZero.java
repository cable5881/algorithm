package com.lqb.didi;

import java.util.Scanner;

/**
 * ���� n �׳��� 0 �ĸ���
 * @author:JackBauer
 * @date:2016��9��18�� ����8:56:59
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
	 * Z(0) = N/5 + N /(5*5) + N/(5*5*5).....ֱ��N/(5��K�η�)����0
	 * ��ʽ�� N/5��ʾ������N�������ܱ�5������������һ��5��N/(5*5)��ʾ������N�������ܱ�25���������ٹ���һ��5.......
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
