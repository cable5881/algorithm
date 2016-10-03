package com.lqb.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ��������m�Լ�n������A1, A2, ��, An��������A������Ԫ��������򣬹��ܵõ�n(n-1)/2������� 
 * �������Щ����д���m���ж��ٸ���
 * 
 * ���� ��һ�а�����������n, m�� �ڶ��и���n������A1, A2, ��, An�� 
 * ��� ���������һ�У�������Ĵ𰸡�
 * 
 * �������� 
 * 3 10 
 * 6 5 10 
 * 
 * ������� 2
 * 
 * Hint 
 * �������� 
 * ���ܵ��������Ľ���У� 
 * 5 xor 6 = 3 
 * 5 xor 10 = 15 
 * 6 xor 10 = 12
 * �������ֵ����10�������ַ�����
 * 
 * ���ݷ�Χ ����30%�����ݣ�1 <= n, m <= 1000 ����100%�����ݣ�1 <= n, m, Ai <= 10^5
 * 
 * @author:JackBauer
 * @date:2016��9��21�� ����8:15:26
 */
public class DifferentOr {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			
			System.out.println(fun2(a, m));
		}
		
		in.close();

	}
	
	/**
	 * ���в���������ȷ��Ϊ 30%�� ���Գ����ٴ����ƴ��룬�����ԣ���ȡȫ��ACͨ��
	 * 
	 * ��ܰ��ʾ����ʱ��������ȫ������ᵼ�³�ʱ������д����������������ȫ�������Ƿ񳬹���ĿҪ����ڴ��С��
	 * �ų����������ټ�����������ĳ�ʱ����������ѭ����ѭ��������������ȣ�������Ҫ���õ��㷨��
	 * 
	 * @param a
	 * @param m
	 * @return
	 */
	public static int fun(int[] a, int m) {
		int count = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = i + 1; j < a.length; j++) {
				if((a[j] ^ a[i]) > m) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static int fun2(int[] a, int m) {
		Arrays.sort(a);
		
		for(int i = 0; i < a.length; i++) {
			for(int j = i + 1; j < a.length; j++) {
				if((a[j] ^ a[i]) > m) {
					return 0;
				}
			}
		}
		
		return 0;
	}

}
