package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * �ٶ����Ƕ�֪���ǳ���Ч���㷨�����һ�������Ƿ�Ϊ�����ַ������Ӵ���
 * �뽫����㷨��д��һ�����������������ַ���s1��s2�����д������s2�Ƿ�Ϊs1��ת���ɣ�Ҫ��ֻ�ܵ���һ�μ���Ӵ��ĺ�����
 * ���������ַ���s1,s2,�뷵��boolֵ����s2�Ƿ���s1��ת���ɡ�
 * �ַ������ַ�ΪӢ����ĸ�Ϳո����ִ�Сд���ַ�������С�ڵ���1000��
 * ����������
 * 
 * "Hello world","worldhello "
 * ���أ�false
 * 
 * "waterbottle","erbottlewat"
 * ���أ�true
 * @Author:JackBauer
 * @Date:2016��7��25�� ����5:05:07
 */
public class IsRotation {

	public static void main(String[] args) {
		IsRotation test = new IsRotation();
		String str = "waterbottle";
		String subStr = "";
		
		
		System.out.println(test.isSubstring(str, subStr));
	}
	
	public boolean checkReverseEqual(String s1, String s2) {
		if(s1 == null || s2 == null){
			
		}
		
		
		
		
		
		return false;
    }
	
	
	private boolean isSubstring(String str, String subStr){
		if(str == null || subStr == null){
			return false;
		}
		if(str.length() < subStr.length()){
			return false;
		}
		
		int subLength = subStr.length();
		int sLength = str.length();
		int i = 0;
		int j = 0;
		
		while(j < subLength && i < sLength){
			if(str.charAt(i) == subStr.charAt(j)){
				i++;
				j++;
			}else{
				i++;
				j = 0;
			}
		}
		
		return j == subLength && j > 0;
	}

}
