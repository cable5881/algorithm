package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 请实现一个算法，在不使用额外数据结构和储存空间的情况下，翻转一个给定的字符串(可以使用单个过程变量)。
 * 给定一个string iniString，请返回一个string，为翻转后的字符串。保证字符串的长度小于等于5000。
 * 测试样例："This is nowcoder"
 * 返回："redocwon si sihT"
 * @Description:TODO
 * @Author:JackBauer
 * @Date:2016年7月22日 下午3:30:45
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
