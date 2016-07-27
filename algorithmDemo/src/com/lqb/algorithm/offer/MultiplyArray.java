package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * @Author:JackBauer
 * @Date:2016年7月20日 下午4:47:09
 */
public class MultiplyArray {

	public static void main(String[] args) {
		int[] a = {3,5,7,9,2};
		int[] b = new MultiplyArray().myMultiply(a);
//		int[] b = new MultiplyArray().multiply(a);
		System.out.println(Arrays.toString(b));
	}

	public int[] multiply(int[] A) {
		int len = A.length;
		int forword[] = new int[len];
		int backword[] = new int[len];
		int B[] = new int[len];

		forword[0] = 1;
		backword[0] = 1;

		for (int i = 1; i < len; i++) {
			forword[i] = A[i - 1] * forword[i - 1];
			backword[i] = A[len - i] * backword[i - 1];
		}

		for (int i = 0; i < len; i++) {
			B[i] = forword[i] * backword[len - i - 1];
		}
		return B;
	}

	public int[] myMultiply(int[] a) {
		if(a == null || a.length <= 0){
			return new int[0];
		}
		
		int[] b = new int[a.length];
		
		b[0] = 1;
		for(int i = 1; i < a.length; i++){
			b[i] = b[i - 1] * a[i - 1];
		}
		
		int temp = 1;
		
		for(int i = a.length - 1; i >= 1; i--){
			temp *= a[i];
			b[i - 1] *= temp;
		}
		
		return b;
	}
}
