package com.lqb.ctci;

import java.util.Random;

/**
 * @Description:给出一个产生0-4随机数的random5函数，求random7
 * @Author:JackBauer
 * @Date:2016年8月10日
 */
public class Random7 {

	public static void main(String[] args) {
		while (true) {
			System.out.println(fun2());
		}
	}

	//simple solution
	public static int fun1() {
		while (true) {
			int resultBefore = random5() * 5 + random5();
			if (resultBefore < 21) {
				return resultBefore % 7;
			}
		}
	}

	private static int random5() {
		return new Random().nextInt(5);
	}

	//another more complicated solution
	public static int fun2() {
		while (true) {
			int a = 2 * random5();// produces 0,2,4,6,8
			int b = random5();// for produces 0 and 1 later
			if(b != 4){
				int c = b % 2;// produces 1 or 0
				a = a + c;// produces from 0 to 9
				//filter 7,8,9
				if(a < 7){
					return a;//no need to mod 7
				}
			}
		}
	}

}
