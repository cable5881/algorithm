package com.lqb.algorithm.hauwei;

import java.util.Arrays;
import java.util.Scanner;

public class AddLongIntegerString {

	public static void main(String[] args) {
		AddLongIntegerString test = new AddLongIntegerString();
		System.out.println(test.AddLongInteger("123", "123"));
		System.out.println(test.AddLongInteger("923", "123"));
		System.out.println(test.AddLongInteger("999999923", "123"));
		 
//		Scanner in = new Scanner(System.in);
//		while(in.hasNextLine()){
//			String addend = in.nextLine();
//			String augend = in.nextLine();
//			System.out.println(test.AddLongInteger(addend, augend));
//		}
	}

	/*
	 * 请设计一个算法完成两个超长正整数的加法。 输入参数： String addend：加数 String augend：被加数 返回值：加法结果
	 */
	public String AddLongInteger(String addend, String augend) {
		if (addend == null || addend.length() < 1) {
			return null;
		} else if (augend == null || augend.length() < 1) {
			return null;
		}
		
		if(addend.length() >= augend.length()){
			return addLongIntegerCore(addend.toCharArray(), augend.toCharArray());
		} else {
			return addLongIntegerCore(augend.toCharArray(), addend.toCharArray());
		}
		
	}

	private String addLongIntegerCore(char[] a1, char[] a2) {
		int i = a1.length - 1;
		int j = a2.length - 1;
		int forward = 0;
		char[] result = new char[a1.length + 1];
		int t = 0;
		
		while(j >= 0){
			if((t = add(a1[i] - 48, a2[j] - 48 + forward)) >= 10) {
				forward = 1;
				result[i + 1] = (char) (t - 10 + 48);
			} else {
				forward = 0;
				result[i + 1] = (char) (t + 48);
			}
			
			j--;
			i--;
		}
		
		if(forward < 1 && i == j) {
			return new String(Arrays.copyOfRange(result, 1, result.length));
		}
		
		while(forward > 0 && i >= 0) {
			if((t = add(a1[i] - 48, forward)) >= 10) {
				forward = 1;
				result[i + 1] = (char) (t - 10 + 48);
			} else {
				forward = 0;
				result[i + 1] = (char) (t + 48);
			}
			
			i--;
		}
		
		if(forward > 0) {
			result[0] = 49;
		} else {
			while (i >= 0) {
				result[i + 1] = a1[i];
				i--;
			}
		} 
		
		return new String(result);
	}

	private int add(int r1, int r2) {
		return r1 + r2;
	}

}
