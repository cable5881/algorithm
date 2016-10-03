package com.lqb.algorithm;

import java.util.ArrayList;

/**
 * ����һ���ַ����������ȫ����
 * @author:JackBauer
 * @date:2016��10��2�� ����9:44:08
 */
public class Permutation {

	public static void main(String[] args) {
		String s1 = "abc";
		
		ArrayList<String> permutation = getPermutation(s1.toCharArray());
		System.out.println(permutation);
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
			// ��������˳�����Ҫ
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

}
