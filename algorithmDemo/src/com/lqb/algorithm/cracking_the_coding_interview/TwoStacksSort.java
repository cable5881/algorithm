package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;

/**
 * 
 * ���дһ�����򣬰������ջ�������򣨼����Ԫ��λ��ջ������
 * Ҫ�����ֻ��ʹ��һ�������ջ�����ʱ���ݣ������ý�Ԫ�ظ��Ƶ�������ݽṹ�С�
 * ����һ��int[] numbers(C++��Ϊvector&ltint>)�����е�һ��Ԫ��Ϊջ�����뷵��������ջ��
 * ��ע������һ��ջ����ζ�������������ֻ�ܷ��ʵ���һ��Ԫ��
 * 
 * ����������
 * [1,2,3,4,5]
 * ���أ�[5,4,3,2,1]
 * 
 * @Description:
 * @Author:JackBauer
 * @Date:2016��8��5��
 */
public class TwoStacksSort {

	public static void main(String[] args) {
		int[] numbers = {4,8,1,6,3,9,7,5,2};
		TwoStacksSort test = new TwoStacksSort();
		ArrayList<Integer> stack = test.twoStacksSort(numbers);
		System.out.println(stack);
	}

	public ArrayList<Integer> twoStacksSort(int[] numbers) {
		ArrayList<Integer> stack = new ArrayList<>();
		ArrayList<Integer> buffer = new ArrayList<>();

		for (int i = 0; i < numbers.length; i++) {
			while (!stack.isEmpty() && stack.get(0) > numbers[i]) {
				buffer.add(0, stack.remove(0));
			}

			stack.add(0, numbers[i]);

			while (!buffer.isEmpty()) {
				stack.add(0, buffer.remove(0));
			}
		}

		return stack;
	}
}
