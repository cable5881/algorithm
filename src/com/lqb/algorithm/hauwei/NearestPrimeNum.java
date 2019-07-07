package com.lqb.algorithm.hauwei;

import java.util.Scanner;

/**
 * 任意一个偶数（大于2）都可以由2个素数组成，组成偶数的2个素数有很多种情况，本题目要求输出组成指定偶数的两个素数差值最小的素数对 
 * @Author:JackBauer
 * @Date:2016年8月5日
 */
public class NearestPrimeNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num = in.nextInt();
			int half = num / 2;
			int final1 = 0;
			int final2 = 0;
			for (int i = 0; i < num / 2; i++) {
				//首先输入的肯定是偶数，从中间开始找的话就是保证两个数差距最小，然后再分别判断两个数是不是质数，就可以了
				if (isPrime(half - i) && isPrime(half + i)) {
					final1 = half - i;
					final2 = half + i;
					break;
				}
			}
			System.out.println(final1);
			System.out.println(final2);
		}
	}

	static boolean isPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i != 0) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
