package com.lqb.netease.first2017;

import java.util.Scanner;

/**
 * �� n ��ѧ��վ��һ�ţ�ÿ��ѧ����һ������ֵ��ţţ����� n ��ѧ���а���˳��ѡȡ k ��ѧ���� Ҫ����������ѧ����λ�ñ�ŵĲ���� d��ʹ���� k
 * ��ѧ��������ֵ�ĳ˻�������ܷ������ĳ˻���
 * 
 * ��������: ÿ��������� 1 ������������ÿ���������ݵĵ�һ�а���һ������ n (1 <= n <= 50)����ʾѧ���ĸ�������������һ�У����� n
 * ����������˳���ʾÿ��ѧ��������ֵ ai��-50 <= ai <= 50������������һ�а�������������k �� d (1 <= k <= 10, 1 <=
 * d <= 50)��
 * 
 * �������: ���һ�б�ʾ���ĳ˻���
 * 
 * ��������: 3 7 4 7 2 50
 * 
 * �������: 49
 * 
 * @Author:JackBauer
 * @Date:2016��8��15��
 */
public class Glee {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// int length = in.nextInt();
		// int[] stu = new int[length];
		// for(int i = 0; i < length; i++){
		// stu[i] = in.nextInt();
		// }
		// int need = in.nextInt();
		// int offset = in.nextInt();
		//
		// System.out.println(pick(stu, need, offset));
		// in.close();

		int[] stu = { 7, -15, 31, 49, -44, 35, 44, -47, -23, 15, -11, 10, -21, 10, -13, 0, -20, -36, 22, -13, -39, -39,
				-31, -13, -27, -43, -6, 40, 5, -47, 35, -8, 24, -31, -24, -1 };
		int need = 3;
		int offset = 31;
		System.out.println(pick(stu, need, offset));
	}

	public static int pick(int[] stu, int need, int offset) {
		int max = -51;
		int len = stu.length;

		for (int i = 1; i <= offset; i++) {
			for (int j = 0; j < len; j++) {
				int count = 0;
				int sum = 1;
				for (int k = j; k < len && count < need; k += i) {
					sum *= stu[k];
					count++;

					if (count == need && sum > max) {
						max = sum;
					}
				}
			}
		}

		return max;
	}

}
