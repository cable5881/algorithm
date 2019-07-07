package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 请编写程序交换一个数的 二进制的奇数位和偶数位。（使用越少的指令越好）
 * 
 * 给定一个int x，请返回交换后的数int。 测试样例：
 * 
 * 10
 * 
 * 返回：5
 * 
 * @Author:JackBauer
 * @Date:2016年8月18日
 */
public class ExchangeOddEven {

	public static void main(String[] args) {
		System.out.println(exchangeOddEven(10));
	}

	public static int exchangeOddEven(int x) {
		return ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1);
	}

}
