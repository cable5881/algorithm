package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * �и�С��������¥�ݣ�¥����n��̨�ף�С��һ�ο�����1�ס�2�ס�3�ס�
 * ��ʵ��һ������������С���ж�������¥�ķ�ʽ��
 * Ϊ�˷�ֹ������뽫���Mod 1000000007
 * ����һ��������int n���뷵��һ������������¥�ķ�ʽ������֤nС�ڵ���100000��
 * 
 * @Author:JackBauer
 * @Date:2016��8��12��
 */
public class GoUpstairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int countWays(int n) {
		if(n <= 0){
			return 0;
		}else if(n == 1){
			return 1;
		}else if(n == 2){
			return 2;
		}else if(n == 3){
			return countWays(n - 1) + countWays(n - 2) + 1;
		}
		
		return (countWays(n - 1) % 1000000007 
			 + countWays(n - 2) % 1000000007
			 + countWays(n - 3) % 1000000007) % 1000000007;
    }

}
