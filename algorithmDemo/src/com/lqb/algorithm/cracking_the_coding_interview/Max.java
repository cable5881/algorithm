package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ���дһ���������ҳ����������������Ǹ��������ǲ���ʹ��if-else�ȱȽϺ��ж��������
 * ��������int a��b���뷵�ؽϴ��һ��������������ͬ�򷵻�����һ����
 * 
 * @author:JackBauer
 * @date:2016��10��14��
 */
public class Max {
	public int getMax(int a, int b) {
		b = a - b;
		
		// ��1����a>=bʱ��(a-b)����λΪ0��(a-b)����31λ����λ��0�����ƺ�Ľ��Ϊ0��
		// ��2����a<bʱ��(a-b)����λΪ1��(a-b)����31λ����λ��1�������ƺ�Ľ������ �����뵱Ȼ��1,����-1��  
		return a + (b >> 31) * b;
	}
}
