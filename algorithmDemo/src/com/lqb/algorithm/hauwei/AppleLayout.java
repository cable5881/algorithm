package com.lqb.algorithm.hauwei;

import java.util.Scanner;

/**
 * 把M个同样的苹果放在N个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？ （用K表示）5，1，1和1，5，1 是同一种分法。
 * 
 * 输入描述: 输入两个int整数
 * 
 * 输出描述: 输出结果，int型
 * 
 * 放苹果分为两种情况，一种是有盘子为空，一种是每个盘子上都有苹果。 令(m,n)表示将m个苹果放入n个盘子中的摆放方法总数。
 * 1.假设有一个盘子为空，则(m,n)问题转化为将m个苹果放在n-1个盘子上，即求得(m,n-1)即可
 * 2.假设所有盘子都装有苹果，则每个盘子上至少有一个苹果，即最多剩下m-n个苹果，问题转化为将m-n个苹果放到n个盘子上 即求(m-n，n)
 * 
 * @Author:JackBauer
 * @Date:2016年8月2日
 */
public class AppleLayout {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			System.out.println(test(m, n));
		}
		
		sc.close();
	}

	public static int test(int apples, int plates) {
		if (apples < 0 || plates < 1) {
			return -1;
		}

		return layoutCore(apples, plates);
	}

	public static int layoutCore(int apples, int plates) {
		if (apples == 0 || apples == 1) {
			return 1;
		}
		
		if(plates == 1){
			return 1;
		}

		if (apples < plates) {
			return layoutCore(apples, apples);
		}

		return layoutCore(apples, plates - 1) + layoutCore(apples - plates, plates);
	}

}
