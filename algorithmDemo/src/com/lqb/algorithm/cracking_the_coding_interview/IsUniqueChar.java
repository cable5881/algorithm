package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.Arrays;
import java.util.Formatter;

/**
 * ��ʵ��һ���㷨��ȷ��һ���ַ����������ַ��Ƿ�ȫ����ͬ����������Ҫ������ʹ�ö���Ĵ洢�ṹ��
 * ����һ��string iniString���뷵��һ��boolֵ,True���������ַ�ȫ����ͬ��False���������ͬ���ַ�����֤�ַ����е��ַ�ΪASCII�ַ����ַ����ĳ���С�ڵ���3000��
 * ����������
 * 
 * "aeiou"
 * ���أ�True
 * 
 * "BarackObama"
 * ���أ�False
 * 
 * @Author:JackBauer
 * @Date:2016��7��25�� ����9:30:17
 */
public class IsUniqueChar {

	public static void main(String[] args) {
		String str = "D-5H0F6T%Z?QM9,\72:[A8X! ;YJ#";
//		String str = "acbdqecsy";
//		System.out.println(Arrays.toString(str.toCharArray()));
//		System.out.println("\72");
//		System.out.println("\58");
		IsUniqueChar test = new IsUniqueChar();
		System.out.println(test.checkDifferent3(str));

	}
	
	
	public boolean checkDifferent(String iniString) {
		if(iniString == null || iniString.length() < 1){
			return true;
		}else if(iniString.length() > 256){
			return false;
		}
		
		boolean[] isDifferent = new boolean[256];
		
		for(int i = 0; i < iniString.length(); i++){
			if(isDifferent[iniString.charAt(i)]){
				return false;
			}
			
			isDifferent[iniString.charAt(i)] = true;
		}
		
		return true;
    }
	
	public boolean checkDifferent2(String str) {
		if(str == null || str.length() < 1){
			return true;
		}else if(str.length() > 256){
			return false;
		}
		
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if((checker & (1 << val)) > 0){
				return false;
			}
			
			checker |= (1 << val);
		}
		
		return true;
    }
	
	/**
	 * ��������
	 * @author:JackBauer
	 * @date:2016��10��16��
	 * @param str
	 * @return
	 */
	public boolean checkDifferent3(String str) {
		if(str == null || str.length() < 1){
			return true;
		}
		
		for(int i = 0; i < str.length(); i++) {
			for(int j = i + 1; j < str.length(); j++) {
				if(str.charAt(i) == str.charAt(j)) {
					return false;
				}
			}
		}
		
		return true;
	}

}
