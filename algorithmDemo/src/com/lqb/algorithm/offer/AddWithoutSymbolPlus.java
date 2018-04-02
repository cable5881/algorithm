package com.lqb.algorithm.offer;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。 
 * @author:JackBauer
 * @date:2016年6月25日 上午9:38:58
 */
public class AddWithoutSymbolPlus {

	public static void main(String[] args) {
		AddWithoutSymbolPlus test = new AddWithoutSymbolPlus();
		System.out.println(test.add(-3, -3));
	}
	
	public int add(int num1, int num2){
		/*
		 * 要考虑负数的情况
		 */
		while(num2 != 0){
			int addWithoutMoveBit = num1 ^ num2;
			System.out.println(Integer.toBinaryString(num1));
			System.out.println("^");
			System.out.println(Integer.toBinaryString(num2));
			System.out.println(Integer.toBinaryString(addWithoutMoveBit));
			System.out.println("bitMoving...");
			
			int bitMove = (num1 & num2) << 1;
			System.out.println(Integer.toBinaryString(bitMove));
			System.out.println("---------------------------------");
			num1 = addWithoutMoveBit;
			num2 = bitMove;
		}
		
		return num1;
	}

}
