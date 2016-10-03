package com.lqb.algorithm;

import java.util.ArrayList;

/**
 * ����һ���ַ�����������е��Ӵ�
 * 
 * @author:JackBauer
 * @date:2016��10��2�� ����10:22:00
 */
public class Combination {

	public static void main(String[] args) {
		String s1 = "abcd";

		ArrayList<String> combinations = getCombination(s1.toCharArray());
		System.out.println(combinations);

	}

	public static ArrayList<String> getCombination(char[] ch) {
		ArrayList<String> combinations = new ArrayList<>();
		if (ch == null || ch.length < 1) {
			return combinations;
		}

		StringBuilder sb = new StringBuilder();
		getCombinationCore(ch, combinations, sb, 0);

		return combinations;
	}

	private static void getCombinationCore(char[] ch, ArrayList<String> combinations, StringBuilder sb, int start) {
		if (start >= ch.length) {
			return;
		}

		for (int i = start, j = 0; i < ch.length; i++, j++) {
			sb.append(ch[i]);
			combinations.add(sb.toString());

			getCombinationCore(ch, combinations, sb, start + j + 1);

			sb.deleteCharAt(sb.length() - 1);
		}

	}

}
