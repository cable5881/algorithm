package com.lqb.algorithm.hauwei;

import java.util.Scanner;

public class Approximate {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double num = input.nextDouble();
		System.out.println(test(num));
		input.close();
	}
	
	public static int test(double num){
		return (int)(num + 0.5);
	}

}
