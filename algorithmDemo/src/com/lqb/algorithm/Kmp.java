package com.lqb.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class Kmp {

	@Test
	public void test() {

		// char[] a = "ilovefucking".toCharArray();
		// char[] b = "fu".toCharArray();
		//
		// boolean contains = kmp(a, b);
		// System.out.println(contains);

		char[] p = "ababaaaba".toCharArray();
		int[] next = new int[p.length];
		getNext(next, p);
		System.out.println(Arrays.toString(next));
	}

	@Test
	public void testNext() {
		char[] a = "ababab".toCharArray();
		int next = next(a, 4);
		System.out.println(next);
	}

	public int next(char[] a, int end) {
		// a b a b a b
		int i = end / 2;
		int j = 0;
		int k = 0;

		while (j <= end / 2 && i < end) {
			if (a[j] == a[i]) {
				k++;

				// for (int m = i, n = j; m < end && n < i; i++, j++) {
				// if (a[++m] == a[++n]) {
				// k++;
				// }
				// }

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

	// ���ݷ�
	public static boolean kmp(char[] a, char[] b) {

		int temp = 0;// �洢����ʱ�ı��i

		for (int i = 0; i < a.length; i++) {

			temp = i;// ֮ǰ�����������if���治��

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

	public void getNext(int[] next, char[] p) {
		int i = 1;
		int j = 0;

		next[0] = 0;

		while (i < p.length) {
			if (j == 0 || p[i] == p[j]) {
				i++;
				j++;
				
				next[i] = j;
//				if (p[i] != p[j]) {
//					next[i] = j;
//				} else {
//					next[i] = next[j];
//				}
			} else {
				j = next[j];
			}
		}
	}
}
