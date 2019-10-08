package com.lqb.offer;

import java.util.ArrayList;

public class FindContinuousSequence {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		FindContinuousSequence test = new FindContinuousSequence();
		ArrayList<ArrayList<Integer>> numbersSequence = test.findContinuousSequence(3);
		
		for(ArrayList<Integer> numbers : numbersSequence){
			System.out.println(numbers);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println((end - start));
	}

	public ArrayList<ArrayList<Integer>> findContinuousSequence(int num) {
		ArrayList<ArrayList<Integer>> numbersSequence = new ArrayList<>();

		//注意结束条件
		if (num < 3) {
			return numbersSequence;
		}

		int small = 1;
		int big = small + 1;

//		while (big < num) {
//			int sum = getSum(small, big);
//			if(sum < num){
//				big++;
//			}else if(sum > num){
//				small++;
//			}else{
//				addNumbers(numbersSequence, small, big);
//				small = big - 1;
//				big++;
//			}
//		}
		
		int middle = num / 2;
		int sum = big + small;
		
		//注意是
		while(small <= middle){
			if(num == sum){
				addNumbers(numbersSequence, small, big);
			}
			
			while(sum > num && small <= middle){
				sum -= small;
				small++;
				
				if(num == sum){
					addNumbers(numbersSequence, small, big);
				}
			}
			
			big++;
			sum += big;
		}

		return numbersSequence;
	}
	
	private int getSum(int start, int end){
		int sum = 0;
		
		while(start <= end){
			sum += start++;
		}
		
		return sum;
	}
	
	private void addNumbers(ArrayList<ArrayList<Integer>> numbersSequence, int start, int end){
		ArrayList<Integer> numbers = new ArrayList<>();
		while(start <= end){
			numbers.add(start++);
		}
		numbersSequence.add(numbers);
	}
}
