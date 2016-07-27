package com.lqb.algorithm.offer;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * ����һ���ַ���,���ֵ����ӡ�����ַ������ַ����������С�
 * ���������ַ���abc,���ӡ�����ַ�a,b,c�������г����������ַ���abc,acb,bac,bca,cab��cba�� 
 * ����밴��ĸ˳������� 
 * @Description:����һ���ַ���,���Ȳ�����9(�������ַ��ظ�),�ַ�ֻ������Сд��ĸ��
 * @author:JackBauer
 * @date:2016��6��14�� ����1:39:50
 */
public class Permutation {

	static HashSet<String> set = new HashSet<>();
	
	public static void main(String[] args) {
//		
//		String s1 = new String("aa");
//		String s2 = new String("aa");
//		System.out.println(s1==s2);
//		set.add(s1);
//		set.add(s2);
//		System.out.println(set);
		System.out.println(permutation("abcd"));
		System.out.println(permutation("ab"));
		System.out.println(permutation("a"));
		System.out.println(permutation("aaaa"));
		System.out.println(permutation(null));
		
		/*
		 
			 �𰸴���:���ύ�ĳ���û��ͨ�����еĲ�������
	
			caseͨ����Ϊ50.00%
			��������:
			abc
			
			��Ӧ���Ӧ��Ϊ:
			
			["abc","acb","bac","bca","cab","cba"]
			
			������Ϊ:
			
			["abc","acb","bac","bca","cba","cab"]

		  
		 */
	}
	
	public static ArrayList<String> permutation(String str) {
		ArrayList<String> strings = new ArrayList<>();
		
		if( str == null || str.length() <= 0 ){
			return strings;
		}
		
		char[] string = str.toCharArray();
		
		permutate(string,0,strings);
		
		return strings;
    }

	private static void permutate(char[] string, int start, ArrayList<String> strings) {
		
		if( start >= string.length - 1 ){
			if( !set.contains(new String(string)) ){
				strings.add(new String(string));
				set.add(new String(string));
			}
			return;
		}
		
		for( int i = start; i < string.length; i++ ){
			char temp = string[start];
			string[start] = string[i];
			string[i] = temp;
			
			permutate(string,start+1,strings);
			
			temp = string[start];
			string[start] = string[i];
			string[i] = temp;
		}
	}

}
