package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ��ʵ��һ���㷨���ڲ�ʹ�ö������ݽṹ�ʹ���ռ������£���תһ���������ַ���(����ʹ�õ������̱���)��
 * ����һ��string iniString���뷵��һ��string��Ϊ��ת����ַ�������֤�ַ����ĳ���С�ڵ���5000��
 * ����������"This is nowcoder"
 * ���أ�"redocwon si sihT"
 * @Description:TODO
 * @Author:JackBauer
 * @Date:2016��7��22�� ����3:30:45
 */
public class ReverseString {

	public static void main(String[] args) {
		ReverseString test = new ReverseString();
		String str = "This is nowcoder";
		System.out.println(test.reverseString(str));

	}
	
	public String reverseString(String iniString) {
		if(iniString == null || iniString.equals("")){
			return iniString;
		}
		
		int start = 0;
		int end = iniString.length() - 1;
		char[] str = iniString.toCharArray();
		
		while(start < end){
			char t = str[start];
			str[start] = str[end];
			str[end] = t;
			
			start++;
			end--;
		}
		
		return new String(str);
    }

}
