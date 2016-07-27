package com.lqb.algorithm.offer;

import java.util.ArrayList;

/**
 * ����һ����������������һ������S���������в�����������ʹ�����ǵĺ�������S
 * ����ж�����ֵĺ͵���S������������ĳ˻���С�ġ� 
 * @author:JackBauer
 * @date:2016��6��23�� ����5:11:55
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
