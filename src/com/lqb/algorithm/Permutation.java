package com.lqb.algorithm;

import java.util.ArrayList;

/**
 * 给出一个字符串，输出其全排列
 * @author:JackBauer
 * @date:2016年10月2日 下午9:44:08
 */
public class Permutation {

	public static void main(String[] args) {
//		String s1 = "abc";
//		
//		ArrayList<String> permutation = getPermutation(s1.toCharArray());
//		System.out.println(permutation);
		
		int[] a = {1,2,3,4};
		System.out.println(fun(a));
	}
	
	public static ArrayList<String> getPermutation(char[] ch) {
		ArrayList<String> permutation = new ArrayList<>();
		if(ch == null || ch.length < 1) {
			return permutation;
		}
		
		StringBuilder sb = new StringBuilder();
		getPermutationCore(ch, permutation, sb, 0);
		
		return permutation;
	}

	private static void getPermutationCore(char[] ch, ArrayList<String> permutation, StringBuilder sb, int start) {
		if(start >= ch.length) {
			permutation.add(sb.toString());
		}
		
		for(int i = start; i < ch.length; i++) {
			// 下面两句顺序很重要
			sb.append(ch[i]);
			swap(ch, start, i);
			
			getPermutationCore(ch, permutation, sb, start + 1);
			
			sb.deleteCharAt(sb.length() - 1);
			swap(ch, start, i);
		}
		
	}
	
	private static void swap(char[] ch, int i, int j) {
		char t = ch[i];
		ch[i] = ch[j];
		ch[j] = t;
	}
	
	
	/**
	 * a = {1,2,3,4} 组成多少个3位数？ 
	 * @author:JackBauer
	 * @date:2016年10月18日  下午4:02:27
	 */
	public static int fun(int[] a) {
		int count = 0;
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				for(int k = 0; k < a.length; k++) {
					if(i != j && j != k && k != i) {
						count++;
						System.out.println(a[i] + "" + a[j] + "" + a[k]);
					}
				}
			}
		}
		
		
		return count;
	}

}
