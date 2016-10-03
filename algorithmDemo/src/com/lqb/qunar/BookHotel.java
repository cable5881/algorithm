package com.lqb.qunar;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ��Ҫ��ȥ���Σ���NԪ��Ԥ��ס�Ƶ꣬��M�ҾƵ깩����ѡ����Щ�Ƶ궼�м۸�X��
 * ��Ҫ�����û�����NԪס�Ƶ꣨���ܶ࣬Ҳ�����٣�������ס������������ס��������û��ƥ��ķ���-1
 * ��������1000Ԫ�����оƵ궼�Ǵ���1000�ģ��򷵻�-1
 * ��������1000Ԫ����1��1000Ԫ�ģ���1��300����1��700����������ס1�����ס2��300+700��������1
 * ��������1000Ԫ����1��387Ԫ����1��2Ԫ����һ��611���򷵻�3��3�Ҹ�ס1�죩
 * ��������1000Ԫ����1��1Ԫ�ģ���һ��2Ԫ�ģ���һ��1001Ԫ�ģ��򷵻�500��1Ԫ��1000�죬2Ԫ��500�죩
 * 
 * 
 * ���� n��int�����һ��intΪ��ӵ�е�Ǯ��[0, n-2]Ϊ�Ƶ�ļ۸� ��� ��������ס��������û��ƥ��ķ���-1
 * 
 * �������� 1001 1002 1003 2001 1000 
 * ������� -1
 * 
 * @author:JackBauer
 * @date:2016��9��27�� ����10:41:26
 */
public class BookHotel {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] parts = s.split(" ");
			int[] A = convert(parts);
			int m = A[A.length - 1];
			System.out.println(getLeastDays(Arrays.copyOf(A, A.length - 1), m));
		}
		in.close();
	}
	
	public static int getLeastDays(int[] a, int m) {
		if(a == null || a.length < 2) {
			return -1;
		}
		
		//���ж��Ƿ��ܹ��պ�ס��
		if(!isRight(a, m)) {
			return -1;
		}
		
		int minDays = Integer.MAX_VALUE;
		int tDays = -1;
		
		for(int i = a.length - 1; i >= 0; i--) {
			if(m >= a[i]) {
				tDays = m / a[i];
				if(tDays < minDays) {
					minDays = tDays;
				}
			}
		}
		
		return tDays;
	}


	
	private static boolean isRight(int[] a, int m) {
		
		return isRightCore(a, 0, a.length - 1, m);
	}
	
	private static boolean isRightCore(int[] a, int start, int end, int m) {
		return true;
	}
	

	private static int[] convert(String[] s) {
		int[] a = new int[s.length];
		
		for(int i = 0; i < s.length; i++) {
			a[i] = Integer.parseInt(s[i]);
		}
		return a;
	}
}
