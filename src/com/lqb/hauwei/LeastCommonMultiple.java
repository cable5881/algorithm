package com.lqb.hauwei;

import java.util.Scanner;

/**
 * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
 * 
 * 输入描述:
 * 输入两个正整数A和B。
 * 输出描述:
 * 输出A和B的最小公倍数。
 * 
 * 输入例子:
 * 5 7
 * 输出例子:
 * 35
 * 
 * 最小公倍数 = 两数之积除以最大公约数
 * 
 * @Author:JackBauer
 * @Date:2016年8月2日
 */
public class LeastCommonMultiple {

	public static void main(String[] args) {
		LeastCommonMultiple test = new LeastCommonMultiple();
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			int n = input.nextInt();
			int m = input.nextInt();
			int t = test.fun(n, m);
			System.out.println(n * m / t);
		}
		
		input.close();
	}

	public int fun(int n, int m) {
		int big;
		int small;
		int temp;
		
		if(n > m){
			big = n;
			small = m;
		}else{
			big = m;
			small = n;
		}
		
		while(small > 0){
			temp = big % small;
			big = small;
			small = temp;
		}
		
		return big;
	}

}
