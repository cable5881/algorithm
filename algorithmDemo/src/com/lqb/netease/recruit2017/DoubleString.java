package com.lqb.netease.recruit2017;

import java.util.HashSet;
import java.util.Scanner;

public class DoubleString {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String s = in.nextLine();
			System.out.println(fun(s));
		}

		in.close();

	}

	private static int fun(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		
		//aaabccabccCC
		return funCore(s.toCharArray());
	}

	private static int funCore(char[] ch) {
		int count = 0;
		String a;
		String b;
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < ch.length; i++) {
			for (int j = 1; (i + 2 * j - 1) < ch.length; j++) {
				a = new String(ch, i, j);
				b = new String(ch,i + j, j);
				if(a.equals(b) && !set.contains(a)) {
					set.add(a);
					count++;
				}
			}
		}

		return count;
	}

}
