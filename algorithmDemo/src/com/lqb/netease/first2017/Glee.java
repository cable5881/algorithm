package com.lqb.netease.first2017;

import java.util.Scanner;

/**
 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生， 要求相邻两个学生的位置编号的差不超过 d，使得这 k
 * 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 * 
 * 输入描述: 每个输入包含 1 个测试用例。每个测试数据的第一行包含一个整数 n (1 <= n <= 50)，表示学生的个数，接下来的一行，包含 n
 * 个整数，按顺序表示每个学生的能力值 ai（-50 <= ai <= 50）。接下来的一行包含两个整数，k 和 d (1 <= k <= 10, 1 <=
 * d <= 50)。
 * 
 * 输出描述: 输出一行表示最大的乘积。
 * 
 * 输入例子: 3 7 4 7 2 50
 * 
 * 输出例子: 49
 * 
 * @Author:JackBauer
 * @Date:2016年8月15日
 */
public class Glee {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// int length = in.nextInt();
		// int[] stu = new int[length];
		// for(int i = 0; i < length; i++){
		// stu[i] = in.nextInt();
		// }
		// int need = in.nextInt();
		// int offset = in.nextInt();
		//
		// System.out.println(pick(stu, need, offset));
		// in.close();

		int[] stu = { 7, -15, 31, 49, -44, 35, 44, -47, -23, 15, -11, 10, -21, 10, -13, 0, -20, -36, 22, -13, -39, -39,
				-31, -13, -27, -43, -6, 40, 5, -47, 35, -8, 24, -31, -24, -1 };
		int need = 3;
		int offset = 31;
		System.out.println(pick(stu, need, offset));
	}

	public static int pick(int[] stu, int need, int offset) {
		int max = -51;
		int len = stu.length;

		for (int i = 1; i <= offset; i++) {
			for (int j = 0; j < len; j++) {
				int count = 0;
				int sum = 1;
				for (int k = j; k < len && count < need; k += i) {
					sum *= stu[k];
					count++;

					if (count == need && sum > max) {
						max = sum;
					}
				}
			}
		}

		return max;
	}

}
