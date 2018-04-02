package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 对于一个字符串，请设计一个算法，判断其是否为一个合法的括号串。
 * 给定一个字符串A和它的长度n，请返回一个bool值代表它是否为一个合法的括号串。
 * 
 * @author:JackBauer
 * @date:2016年10月14日
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
