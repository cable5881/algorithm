package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * �����������һ����λָ�����ѭ�����ƣ�ROL���������и��򵥵����񣬾������ַ���ģ�����ָ�����������
 * ����һ���������ַ�����S���������ѭ������Kλ������������
 * ���磬�ַ�����S=��abcXYZdef��,Ҫ�����ѭ������3λ��Ľ��������XYZdefabc����
 * 
 * @author:JackBauer
 * @date:2016��6��21�� ����11:06:17
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
