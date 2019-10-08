package com.lqb.offer;

/**
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 * @author:JackBauer
 * @date:2016年7月1日 下午4:10:31
 */
public class RepeartedNumberInArray {

	public static void main(String[] args) {
		RepeartedNumberInArray test = new RepeartedNumberInArray();
		int numbers[] = {3,1,5,4,2,0};
		int[] duplication = {-1};
		boolean flag = test.duplicate(numbers, numbers.length, duplication);
		
		System.out.println(flag);
		System.out.println(duplication[0]);
	}

	public boolean duplicate(int numbers[], int length, int[] duplication) {
		if (numbers == null || length < 1 || duplication == null || duplication.length < 1) {
			return false;
		}

		int i = 0;
		while(i < length){
			if (isKthNumCorrect(numbers, i)) {
				i++;
			}else if(!trySwap(numbers, i, numbers[i])){
				duplication[0] = numbers[i];
				return true;
			}
		}

		return false;
	}

	private boolean isKthNumCorrect(int numbers[], int k) {
		return numbers[k] == k ? true : false;
	}

	private boolean trySwap(int[] numbers, int i, int k) {
		if (numbers[i] != numbers[k]) {
			int temp = numbers[k];
			numbers[k] = numbers[i];
			numbers[i] = temp;

			return true;
		} else {
			return false;
		}

	}

}
