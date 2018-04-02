package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 请编写一个方法，找出两个数字中最大的那个。条件是不得使用if-else等比较和判断运算符。
 * 给定两个int a和b，请返回较大的一个数。若两数相同则返回任意一个。
 * 
 * @author:JackBauer
 * @date:2016年10月14日
 */
public class Max {
	public int getMax(int a, int b) {
		b = a - b;
		
		// （1）当a>=b时，(a-b)符号位为0，(a-b)右移31位，高位补0，右移后的结果为0；
		// （2）当a<b时，(a-b)符号位为1，(a-b)右移31位，高位补1，故右移后的结果不是 我们想当然的1,而是-1。  
		return a + (b >> 31) * b;
	}
}
