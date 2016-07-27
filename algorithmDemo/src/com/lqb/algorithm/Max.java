package com.lqb.algorithm;

public class Max {

	public static void main(String[] args) {
		int[] a = { 1, 5, 4, 9, 6, 8, 2, 3, 7 };
		System.out.println(max(a,a.length-1));
	}

	public static int max(int[] a,int n) {
		
		if(n==1){
			return a[n];
		}

		int max = max(a,n-1);
		int temp = a[n];
		
		if(temp>max){
			return temp;
		}
		
		return max;
	}

}
