package com.lqb.qunar;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 你要出去旅游，有N元的预算住酒店，有M家酒店供你挑选，这些酒店都有价格X。
 * 需要你正好花完这N元住酒店（不能多，也不能少）最少能住几晚？返回最少住的天数，没有匹配的返回-1
 * 比如你有1000元，所有酒店都是大于1000的，则返回-1
 * 比如你有1000元，有1家1000元的，有1家300，有1家700。则最少能住1晚，最多住2晚（300+700）。返回1
 * 比如你有1000元，有1家387元，有1家2元，有一家611，则返回3（3家各住1天）
 * 比如你有1000元，有1家1元的，有一家2元的，有一家1001元的，则返回500（1元的1000天，2元的500天）
 * 
 * 
 * 输入 n个int，最后一个int为你拥有的钱，[0, n-2]为酒店的价格 输出 返回最少住的天数，没有匹配的返回-1
 * 
 * 样例输入 1001 1002 1003 2001 1000 
 * 样例输出 -1
 * 
 * @author:JackBauer
 * @date:2016年9月27日 上午10:41:26
 */
public class BookHotel {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] parts = s.split(" ");
			int[] A = convert(parts);
			int m = A[A.length - 1];
			System.out.println(getLeastDays(Arrays.copyOf(A, A.length - 1), m));
		}
		in.close();
	}
	
	public static int getLeastDays(int[] a, int m) {
		if(a == null || a.length < 2) {
			return -1;
		}
		
		//先判断是否能够刚好住完
		if(!isRight(a, m)) {
			return -1;
		}
		
		int minDays = Integer.MAX_VALUE;
		int tDays = -1;
		
		for(int i = a.length - 1; i >= 0; i--) {
			if(m >= a[i]) {
				tDays = m / a[i];
				if(tDays < minDays) {
					minDays = tDays;
				}
			}
		}
		
		return tDays;
	}


	
	private static boolean isRight(int[] a, int m) {
		
		return isRightCore(a, 0, a.length - 1, m);
	}
	
	private static boolean isRightCore(int[] a, int start, int end, int m) {
		return true;
	}
	

	private static int[] convert(String[] s) {
		int[] a = new int[s.length];
		
		for(int i = 0; i < s.length; i++) {
			a[i] = Integer.parseInt(s[i]);
		}
		return a;
	}
}
