package com.lqb.qunar;

import java.util.Scanner;

/**
 * 判断一个数是否为2的次方，不能使用任何类库以及内置函数
 * 
 * @author:JackBauer
 * @date:2016年9月27日 上午10:56:25
 */
public class IsPower2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			int n = in.nextInt();
			System.out.println(isPower2(n));
		}
		
		in.close();
	}

	public static boolean isPower2(int n) {
		if(n <= 0) {
			return false;
		}
		
		return (n & (n - 1)) == 0;
	}

}
