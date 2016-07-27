package com.lqb.algorithm;

import org.junit.Test;

public class Kmp {

	@Test
	public void test() {

		char[] a = "ilovefucking".toCharArray();
		char[] b = "fu".toCharArray();

		boolean contains = kmp(a, b);
		System.out.println(contains);
	}
	
	@Test
	public void testNext(){
		char[] a = "ababab".toCharArray();
		int next = next(a,4);
		System.out.println(next);
	}

	public int next(char[] a, int end) {
		//a b a b a b
		int i = end/2;
		int j = 0;
		int k = 0;

		while (j <= end/2 && i < end) {
			if (a[j] == a[i]) {
				k++;

//				for (int m = i, n = j; m < end && n < i; i++, j++) {
//					if (a[++m] == a[++n]) {
//						k++;
//					}
//				}

				j++;
				i++;
			} else {
				k = 0;
			}

		}

		return k;
	}

	// kmp
	public static boolean kmp2(char[] a, char[] b) {

		for (int i = 0; i < a.length; i++) {

		}

		return false;
	}

	// 回溯法
	public static boolean kmp(char[] a, char[] b) {

		int temp = 0;// 存储被临时改变的i

		for (int i = 0; i < a.length; i++) {

			temp = i;// 之前放在了下面的if里面不对

			for (int j = 0; j < b.length; j++) {

				if (a[i] == b[j]) {
					i++;
				} else {
					break;
				}

			}

			if (i - temp == b.length) {
				return true;
			} else {
				i = temp;
			}

		}

		return false;
	}

}
