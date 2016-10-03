package com.lqb.qunar;

import java.util.Scanner;

/**
 * �ж�һ�����Ƿ�Ϊ2�Ĵη�������ʹ���κ�����Լ����ú���
 * 
 * @author:JackBauer
 * @date:2016��9��27�� ����10:56:25
 */
public class IsPower2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			int n = in.nextInt();
			System.out.println(isPower2(n));
		}
		
		in.close();
	}

	public static boolean isPower2(int n) {
		if(n <= 0) {
			return false;
		}
		
		return (n & (n - 1)) == 0;
	}

}
