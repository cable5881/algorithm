package com.lqb.algorithm.offer;

public class NumberOf1 {

	public static void main(String[] args) {
		System.out.println(Math.abs(-219999999));
		System.out.println( numberOf1(-229999999) );

	}
	
	public static int numberOf1(int n) {
        
		int count = 0;
		
		while( n != 0 ){
			count++;
			n = (n - 1) & n;
		}
		
		return count;
    }

}
