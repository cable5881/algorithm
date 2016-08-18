package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * ��дһ��������ȷ����Ҫ�ı伸��λ�����ܽ�����Aת�������B��
 * 
 * ������������int A��int B���뷵����Ҫ�ı����λ������ ����������
 * 
 * 10,5
 * 
 * ���أ�4
 * 
 * 
 */
public class BitSwapRequired {

	public static void main(String[] args) {
		System.out.println(calcCost2(10, 5));

	}

	public static int calcCost(int A, int B) {
		int count = 0;
		for (int c = A ^ B; c != 0; c >>= 1) {
			if ((c & 1) != 0) {
				count++;
			}
		}

		return count;
	}

	public static int calcCost2(int A, int B) {
		int count = 0;
		for (int c = A ^ B; c != 0; c = c & (c - 1)) {
			count++;
		}

		return count;
	}

}
