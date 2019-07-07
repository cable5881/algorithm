package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 编写一个方法，确定某字符串的所有排列组合。
 * 
 * 给定一个string A和一个int n,代表字符串和其长度. 
 * 请返回所有该字符串字符的排列，保证字符串长度小于等于11且字符串中字符均为大写英文字符,
 * 排列中的字符串按字典序从大到小排序。(不合并重复字符串)
 * 
 * @author:JackBauer
 * @date:2016年10月14日
 */
public class Permutation {

	public static void main(String[] args) {
		Permutation test = new Permutation();
		System.out.println(test.getPermutation("abcd"));
	}

	public ArrayList<String> getPermutation(String A) {
		ArrayList<String> strs = new ArrayList<>();
		if (A == null || A.length() < 1) {
			return strs;
		}
		
		char[] c = A.toCharArray();
//		reverse(c, 0, c.length - 1);
		getPermutation(c, strs);
		Collections.sort(strs, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.compareTo(o1);
			}
		});
		
		return strs;
	}

	private void getPermutation(char[] c, ArrayList<String> strs) {
		getPermutationCore(c, 0, strs, new StringBuilder());
	}

	private void getPermutationCore(char[] c, int start, ArrayList<String> strs, StringBuilder sb) {
		if (start >= c.length) {
			strs.add(sb.toString());
		}

		for (int i = start; i < c.length; i++) {
			swap(c, start, i);// 注意交换的是每次递归的第一个,不是swap(c, 0, i)
			sb.append(c[start]);// 注意添加的是交换后的第一个,不是sb.append(c[i])

			getPermutationCore(c, start + 1, strs, sb);// 注意下次递归应该是开始位置下一个,不是 i + 1

			swap(c, start, i);// 同上
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private void swap(char[] c, int i, int j) {
		char t = c[i];
		c[i] = c[j];
		c[j] = t;
	}
	
	private void reverse(char[] c, int start, int end) {
		if(start >= end) {
			return;
		}
		
		char t = c[start];
		int p = start;
		int q = end;
		
		while(p < q) {
			while(p < q && t > c[q]) {
				q--;
			} 
			if(p < q) {
				c[p] = c[q];
			}
			
			while(p < q && t < c[p]) {
				p++;
			}
			
			if(p < q) {
				c[q] = c[p];
			}
		}
		
		c[p] = t;
		
		reverse(c, start, p - 1);
		reverse(c, p + 1, end);
	}
	
}
