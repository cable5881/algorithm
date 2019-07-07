package com.lqb.algorithm.offer;

public class Sum_Solution {

	public static void main(String[] args) {
		System.out.println(new Sum_Solution().sum_solution(3));
	}
	
	public int sum_solution(int n){
		int sum = n;
		boolean flag = (n > 0) && ((sum += sum_solution(n - 1)) > 0);
		return sum;
	}

}
