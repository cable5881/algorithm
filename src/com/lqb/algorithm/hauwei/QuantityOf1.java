package com.lqb.algorithm.hauwei;

import java.util.Scanner;

public class QuantityOf1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		System.out.println(test(num));
		input.close();
	}
	
	public static int test(int num){
		int count = 0;
		
		while(num != 0){
//			if((num & 1) > 0){
//				count++;
//			}
//			num = num >> 1;
			
			count++;
			num = num & (num - 1);
		}
		
		return count;
	}

}
