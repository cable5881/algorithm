package com.lqb.algorithm.hauwei;

import java.util.Scanner;

/**
 * �ַ������һ�����ʵĳ���
 * @author:JackBauer
 * @date:2017��3��5�� ����4:20:23
 */
public class LengthOfLastWord {

	public static void main(String[] args) {
		LengthOfLastWord test = new LengthOfLastWord();
//		String s1 = "hello world";
//		String s2 = "";
//		String s3 = "world";
//		
//		System.out.println(test.getLengthOfLastWorld(s1)); // 5
//		System.out.println(test.getLengthOfLastWorld(s2)); // 0
//		System.out.println(test.getLengthOfLastWorld(s3)); // 5
		
		Scanner in = new Scanner(System.in);
		String s;
		while((s = in.nextLine()) != null) {
			System.out.println(test.getLengthOfLastWorld(s));
		}
		in.close();
	}
	
	
	private int getLengthOfLastWorld(String s) {
		int length = 0;
		
		if(s == null) {
			return length;
		}
		
		char[] ch = s.toCharArray();
		
		for(int i = ch.length - 1; i >= 0; i--) {
			if(ch[i] == ' ') {
				break;
			}
			
			length++;
		}
		
		return length;
	}
}
