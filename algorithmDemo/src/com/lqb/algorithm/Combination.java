package com.lqb.algorithm;

import java.util.ArrayList;

/**
 * 给出一个字符串，输出所有的子串
 * 
 * @author:JackBauer
 * @date:2016年10月2日 下午10:22:00
 */
public class Combination {

	//分别调用两种方法
	public static void main(String[] args) {
		String s1 = "abcd";

		ArrayList<String> combinations1 = getCombination(s1.toCharArray());
		System.out.println(combinations1);
		
		ArrayList<String> combinations2 = new ArrayList<>();
		getCombinationCore(s1.toCharArray(), combinations2, s1.length() - 1);
		System.out.println(combinations2);
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

	/**
	 * 核心思想
	 * 
	 	a
	 		ab
	 			abc
	 				return
	 		ac
	 			return
	 	b
	 		bc
	 			return
	 	c
	 * 
	 * @author:JackBauer
	 * @date:2016年10月15日
	 * @param ch
	 * @param combinations
	 * @param sb
	 * @param start
	 */
	private static void getCombinationCore(char[] ch, ArrayList<String> combinations, StringBuilder sb, int start) {
		if (start >= ch.length) {
			return;
		}

		for (int i = start; i < ch.length; i++) {
			sb.append(ch[i]);
			combinations.add(sb.toString());

			getCombinationCore(ch, combinations, sb, i + 1);

			sb.deleteCharAt(sb.length() - 1);
		}

	}
	
	/**
	 * 核心思想 : f(n)的值为 f(n - 1)的值加上n与f(n - 1)合并的值再加上n
	 * 
	 * f(a) = a => [a]
	 * f(ab) = f(a) + b(a) + b => [a, ab, b]
	 * f(abc) = f(ab) + cf(ab) + c => [a, ab, b, ac, abc, bc, c]
	 * 
	 * 只要找到f(abc) 与 f(ab) 的关系即可，即问题与子问题的关系
	 * 
	 * @author:JackBauer
	 * @date:2016年10月15日
	 * @param ch
	 * @param combinations
	 * @param start
	 */
	private static void getCombinationCore(char[] ch, ArrayList<String> combinations, int start) {
		if (start < 0) {
			return;
		}
		
		getCombinationCore(ch, combinations, start - 1);
		
		for(String s : combinations.toArray(new String[combinations.size()])) {
			combinations.add(s + ch[start]);
		}
		
		combinations.add(ch[start] + "");
	}

}
