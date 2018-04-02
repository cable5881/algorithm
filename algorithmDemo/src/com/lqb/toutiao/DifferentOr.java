package com.lqb.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定整数m以及n个数字A1, A2, …, An，将数列A中所有元素两两异或，共能得到n(n-1)/2个结果。 
 * 请求出这些结果中大于m的有多少个。
 * 
 * 输入 第一行包含两个整数n, m。 第二行给出n个整数A1, A2, …, An。 
 * 输出 输出仅包括一行，即所求的答案。
 * 
 * 样例输入 
 * 3 10 
 * 6 5 10 
 * 
 * 样例输出 2
 * 
 * Hint 
 * 样例解释 
 * 可能的两两异或的结果有： 
 * 5 xor 6 = 3 
 * 5 xor 10 = 15 
 * 6 xor 10 = 12
 * 所以异或值大于10的有两种方案。
 * 
 * 数据范围 对于30%的数据，1 <= n, m <= 1000 对于100%的数据，1 <= n, m, Ai <= 10^5
 * 
 * @author:JackBauer
 * @date:2016年9月21日 下午8:15:26
 */
public class DifferentOr {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			
			System.out.println(fun2(a, m));
		}
		
		in.close();

	}
	
	/**
	 * 所有测试数据正确率为 30%！ 可以尝试再次完善代码，并调试，争取全部AC通过
	 * 
	 * 温馨提示：有时候，申请大的全局数组会导致超时，如果有此类情况，请检查您的全局数组是否超过题目要求的内存大小。
	 * 排除这个错误后，再检查别的情况引起的超时错误：例如死循环、循环结束条件错误等，或者需要更好的算法！
	 * 
	 * @param a
	 * @param m
	 * @return
	 */
	public static int fun(int[] a, int m) {
		int count = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = i + 1; j < a.length; j++) {
				if((a[j] ^ a[i]) > m) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static int fun2(int[] a, int m) {
		Arrays.sort(a);
		
		for(int i = 0; i < a.length; i++) {
			for(int j = i + 1; j < a.length; j++) {
				if((a[j] ^ a[i]) > m) {
					return 0;
				}
			}
		}
		
		return 0;
	}

}
