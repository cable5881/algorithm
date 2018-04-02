package com.lqb.algorithm.offer;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。 
 * @author:JackBauer
 * @date:2016年6月23日 下午5:11:55
 */
public class FindNumberWithSum {

	public static void main(String[] args) {
		FindNumberWithSum test = new FindNumberWithSum();
		int[] array = {1,2,3,4,5,6,7,8,9};
		ArrayList<Integer> numbers = test.findNumberWithSum(array, 11);
		System.out.println(numbers);
	}

	public ArrayList<Integer> findNumberWithSum(int array[], int sum) {
		ArrayList<Integer> numbers = new ArrayList<>();
		
		if(array == null || array.length < 2){
			return numbers;
		}
		
		int small = 0;
		int big = array.length - 1;
		
		while(small < big){
			int temp = array[small] + array[big];
			if(temp > sum){
				big--;
			}else if(temp < sum){
				small++;
			}else{
				numbers.add(array[small]);
				numbers.add(array[big]);
				break;
			}
		}
		
		return numbers;
	}

}
