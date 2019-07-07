package com.lqb.algorithm.offer;

public class Power {

	public static void main(String[] args) {
		System.out.println(power(-2, -2));
		System.out.println(power(0.00000000001, -2));
		System.out.println(power(4, 0));
//		System.out.println(1 / 2.0);
	}

	public static double power(double base, int exponent) {

		double num = 1;
		boolean isNegative = exponent < 0 ? true : false;
		
		if( base - 0.0 > -0.000001 && base - 0.0 < 0.000001 ){
			if( exponent == 0 ){
				return 1;
			}else{
				return 0;
			}
				
		}
		
		while( exponent > 0 ){
			num = num * base;
			exponent--;
		}
		
		while( exponent < 0 ){
			num = num * base;
			exponent++;
		}
		
		if( isNegative ){
			num = 1 / num;
		}
		
		return num;
	}

}
