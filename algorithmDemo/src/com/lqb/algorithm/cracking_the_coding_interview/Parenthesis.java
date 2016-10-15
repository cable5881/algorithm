package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ����һ���ַ����������һ���㷨���ж����Ƿ�Ϊһ���Ϸ������Ŵ���
 * ����һ���ַ���A�����ĳ���n���뷵��һ��boolֵ�������Ƿ�Ϊһ���Ϸ������Ŵ���
 * 
 * @author:JackBauer
 * @date:2016��10��14��
 */
public class Parenthesis {
	public static void main(String[] args) {
		Parenthesis test = new Parenthesis();
		System.out.println(test.chkParenthesis("(((())))", 8));
		System.out.println(test.chkParenthesis("(())()))", 8));
	}

	public boolean chkParenthesis(String A, int n) {
		if (A == null || A.length() < 2 || (n & 1) != 0) {
			return false;
		}

		char[] ch = A.toCharArray();
		int leftB = 0;

		for (char c : ch) {
			if (c != '(' && c != ')') {
				return false;
			}

			if (c == '(') {
				leftB++;
			} else if (leftB > 0) {
				leftB--;
			} else {
				return false;
			}
		}

		return true;
	}
}
