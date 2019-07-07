package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 有两个32位整数n和m，请编写算法将m的二进制数位插入到n的二进制的第j到第i位,其中二进制的位数从低位数到高位且以0开始。
 * 给定两个数int n和int m，同时给定int j和int i，意义如题所述，
 * 请返回操作后的数，保证n的第j到第i位均为零，且m的二进制位数小于等于i-j+1。
 * 
 * 测试样例：
 * 1024，19，2，6
 * 返回：1100
 * 
 * @Author:JackBauer
 * @Date:2016年8月10日
 */
public class BinInsert {

	public static void main(String[] args) {
		BinInsert test = new BinInsert();
		System.out.println(test.binInsert(1024, 19, 2, 6));
		System.out.println(test.binInsert(4355, 4, 2, 7));//4370
	}

	public int binInsert(int n, int m, int j, int i) {
		n = n & getCover(j, i);
		m = m << (j);
		
		return n | m;
	}

	private int getCover(int m, int j) {
		int allOnes = ~0;
		
		int left = allOnes << (j + 1);
		int right = (1 << m) - 1;
		int mask = left | right;
		
		return mask;
	}

}
