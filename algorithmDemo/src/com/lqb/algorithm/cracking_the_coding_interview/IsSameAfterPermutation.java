package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ���������ַ��������д����ȷ������һ���ַ������ַ��������к��ܷ�����һ���ַ�����
 * ����涨��СдΪ��ͬ�ַ����ҿ����ַ����ص�ո�
 * ����һ��string stringA��һ��string stringB���뷵��һ��bool�����������Ƿ��������к����ͬ��
 * ��֤�����ĳ��ȶ�С�ڵ���5000��
 * 
 * "This is nowcoder","is This nowcoder"
 * ���أ�true
 * 
 * "Here you are","Are you here"
 * ���أ�false
 * @Author:JackBauer
 * @Date:2016��7��22�� ����3:37:04
 */
public class IsSameAfterPermutation {

	public static void main(String[] args) {
//		String stringA = "This is nowcoder";
//		String stringB = "is This nowcoder";
//		
		String stringA = "Here you are";
		String stringB = "Are you here";
		
		IsSameAfterPermutation test = new IsSameAfterPermutation();
		boolean b = test.checkSam(stringA, stringB);
		System.out.println(b);
	}
	
	/**
	 * @Description:
	 * @Author:JackBauer
	 * @Date:2016��7��26��
	 */
	public boolean checkSam(String stringA, String stringB) {
		if(stringA == null || stringB == null || stringA.length() != stringB.length()){
			return false;
		}
		
		char[] a = stringA.toCharArray();
		char[] b = stringB.toCharArray();
		
		int[] t = new int[256];
		
		for(char c : a){
			t[c] += 1;
		}
		
		for(char c : b){
			t[c] -= 1;
			
			if(t[c] < 0){
				return false;
			}
		}
		
		return true;
    }

}
