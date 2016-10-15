package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * ��дһ��������ȷ��ĳ�ַ���������������ϡ�
 * 
 * ����һ��string A��һ��int n,�����ַ������䳤��. 
 * �뷵�����и��ַ����ַ������У���֤�ַ�������С�ڵ���11���ַ������ַ���Ϊ��дӢ���ַ�,
 * �����е��ַ������ֵ���Ӵ�С����(���ϲ��ظ��ַ���)
 * 
 * @author:JackBauer
 * @date:2016��10��14��
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
			swap(c, start, i);// ע�⽻������ÿ�εݹ�ĵ�һ��,����swap(c, 0, i)
			sb.append(c[start]);// ע����ӵ��ǽ�����ĵ�һ��,����sb.append(c[i])

			getPermutationCore(c, start + 1, strs, sb);// ע���´εݹ�Ӧ���ǿ�ʼλ����һ��,���� i + 1

			swap(c, start, i);// ͬ��
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
