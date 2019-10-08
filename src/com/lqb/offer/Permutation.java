package com.lqb.offer;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。 
 * 结果请按字母顺序输出。 
 * @Description:输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * @author:JackBauer
 * @date:2016年6月14日 下午1:39:50
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
		 
			 答案错误:您提交的程序没有通过所有的测试用例
	
			case通过率为50.00%
			测试用例:
			abc
			
			对应输出应该为:
			
			["abc","acb","bac","bca","cab","cba"]
			
			你的输出为:
			
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
