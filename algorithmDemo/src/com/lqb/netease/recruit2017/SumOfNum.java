package com.lqb.netease.recruit2017;

import java.util.Scanner;

public class SumOfNum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			System.out.println(fun(a, b, c));
		}

		in.close();
	}

	private static int fun(int a, int b, int c) {
		int sumC = getSum(c);
		int min = Integer.MAX_VALUE;
		int minV = 1000000001;
		
		int sumAB;
		int t;
		for(int i = a; i <= b; i++) {
			sumAB = getSum(i);
			t = Math.abs(sumAB - sumC);
			if(t < min) {
				min = t;
				minV = i;
			}
		}
		
		return minV;
	}
	
	private static int getSum(int n) {
		int sum = 0;
		
		while(n > 0) {
			sum += n % 10;
			n /= 10;
		}
		
		return sum;
	}

}
