package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ������32λ����n��m�����д�㷨��m�Ķ�������λ���뵽n�Ķ����Ƶĵ�j����iλ,���ж����Ƶ�λ���ӵ�λ������λ����0��ʼ��
 * ����������int n��int m��ͬʱ����int j��int i����������������
 * �뷵�ز������������֤n�ĵ�j����iλ��Ϊ�㣬��m�Ķ�����λ��С�ڵ���i-j+1��
 * 
 * ����������
 * 1024��19��2��6
 * ���أ�1100
 * 
 * @Author:JackBauer
 * @Date:2016��8��10��
 */
public class BinInsert {

	public static void main(String[] args) {
		BinInsert test = new BinInsert();
		System.out.println(test.binInsert(1024, 19, 2, 6));
	}

	public int binInsert(int n, int m, int j, int i) {
		n = n & getCover(j, i);
		m = m << (j);
		
		return n | m;
	}

	private int getCover(int m, int j) {
		int allOnes = ~0;
		
		int left = allOnes << (j + 1);
		int right = 1 << (m - 1);
		int mask = left | right;
		
		return mask;
	}

}
