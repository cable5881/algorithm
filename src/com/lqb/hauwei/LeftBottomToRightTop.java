package com.lqb.hauwei;

import java.util.Scanner;

/**
 * 请编写一个函数（允许增加子函数），计算n x m的棋盘格子（n为横向的格子数，m为竖向的格子数）
 * 沿着各自边缘线从左上角走到右下角，总共有多少种走法
 * 要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
 * 
 * 输入描述:输入两个正整数
 * 输出描述:返回结果
 * 
 * 输入例子:
 * 2
 * 2
 * 输出例子:
 * 6
 * 
 * @Author:JackBauer
 * @Date:2016年8月2日
 */
public class LeftBottomToRightTop {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            
            System.out.println(test(a, b));
        }
        
        in.close();
	}

	public static int test(int n, int m) {
		if(n < 1 || m < 1){
			return 1;
		}
		
		return test(n - 1, m) + test(n, m - 1);
	}

}
