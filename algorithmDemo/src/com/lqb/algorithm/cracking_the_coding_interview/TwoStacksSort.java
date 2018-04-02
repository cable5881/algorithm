package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;

/**
 * 
 * 请编写一个程序，按升序对栈进行排序（即最大元素位于栈顶），
 * 要求最多只能使用一个额外的栈存放临时数据，但不得将元素复制到别的数据结构中。
 * 给定一个int[] numbers(C++中为vector&ltint>)，其中第一个元素为栈顶，请返回排序后的栈。
 * 请注意这是一个栈，意味着排序过程中你只能访问到第一个元素
 * 
 * 测试样例：
 * [1,2,3,4,5]
 * 返回：[5,4,3,2,1]
 * 
 * @Description:
 * @Author:JackBauer
 * @Date:2016年8月5日
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
