package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ���д���򽻻�һ������ �����Ƶ�����λ��ż��λ����ʹ��Խ�ٵ�ָ��Խ�ã�
 * 
 * ����һ��int x���뷵�ؽ��������int�� ����������
 * 
 * 10
 * 
 * ���أ�5
 * 
 * @Author:JackBauer
 * @Date:2016��8��18��
 */
public class ExchangeOddEven {

	public static void main(String[] args) {
		System.out.println(exchangeOddEven(10));
	}

	public static int exchangeOddEven(int x) {
		return ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1);
	}

}
