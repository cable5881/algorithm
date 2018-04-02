package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 
 * @author:JackBauer
 * @date:2016年6月21日 下午11:06:17
 */
public class LeftRotateString {

	public static void main(String[] args) {
		String str = "abcXYZdef";
		LeftRotateString test = new LeftRotateString();
		System.out.println(test.leftRotateString(str, 3));
	}

	public String leftRotateString(String str, int n) {
		if(str == null || str.equals("") || n > str.length()){
			return "";
		}
		
		if(n <= 0){
			return str;
		}
		
		char[] ch = str.toCharArray();
		
		int firstPartStart = 0;
		int firstPartEnd = firstPartStart + n - 1;
		int secondPartStart = n;
		int secondPartEnd = ch.length - 1;
		
		while(firstPartStart < firstPartEnd){
			swap(ch, firstPartStart++, firstPartEnd--);
		}
		
		while(secondPartStart < secondPartEnd){
			swap(ch, secondPartStart++, secondPartEnd--);
		}
		
		int start = 0;
		int end = ch.length - 1;
		
		while(start < end){
			swap(ch, start++, end--);
		}
		
		return new String(ch);
	}
	
	private void swap(char[] array, int index1, int index2){
		char temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

}
