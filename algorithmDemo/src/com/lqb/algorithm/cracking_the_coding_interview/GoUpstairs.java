package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶、3阶。
 * 请实现一个方法，计算小孩有多少种上楼的方式。
 * 为了防止溢出，请将结果Mod 1000000007
 * 给定一个正整数int n，请返回一个数，代表上楼的方式数。保证n小于等于100000。
 * 
 * @Author:JackBauer
 * @Date:2016年8月12日
 */
public class GoUpstairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int countWays(int n) {
		if(n <= 0){
			return 0;
		}else if(n == 1){
			return 1;
		}else if(n == 2){
			return 2;
		}else if(n == 3){
			return countWays(n - 1) + countWays(n - 2) + 1;
		}
		
		return (countWays(n - 1) % 1000000007 
			 + countWays(n - 2) % 1000000007
			 + countWays(n - 3) % 1000000007) % 1000000007;
    }

}
