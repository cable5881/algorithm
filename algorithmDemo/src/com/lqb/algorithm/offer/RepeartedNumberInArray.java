package com.lqb.algorithm.offer;

/**
 * ��һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڡ� 
 * ������ĳЩ�������ظ��ģ�����֪���м����������ظ��ġ�
 * Ҳ��֪��ÿ�������ظ����Ρ����ҳ�����������һ���ظ������֡� 
 * ���磬������볤��Ϊ7������{2,3,1,0,2,5,3}����ô��Ӧ��������ظ�������2����3��
 * @author:JackBauer
 * @date:2016��7��1�� ����4:10:31
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
