package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 利用字符重复出现的次数，编写一个方法，实现基本的字符串压缩功能。
 * 比如，字符串“aabcccccaaa”经压缩会变成“a2b1c5a3”。若压缩后的字符串没有变短，则返回原先的字符串。
 * 给定一个string iniString为待压缩的串(长度小于等于3000)，
 * 保证串内字符均由大小写英文字母组成，返回一个string，为所求的压缩后或未变化的串。
 * 测试样例:
 * 
 * "aabcccccaaa"
 * 返回："a2b1c5a3"
 * 
 * "welcometonowcoderrrrr"
 * 返回："welcometonowcoderrrrr"
 * 
 * @Author:JackBauer
 * @Date:2016年7月22日 下午4:49:49
 */
public class ZipString {

	public static void main(String[] args) {
//		String iniString = "aabcccccaaa";
		String iniString = "welcometonowcoderrrrr";
		
		ZipString test = new ZipString();
		System.out.println(test.zipString(iniString));
	}

	public String zipString(String iniString) {
		if(iniString == null || iniString.length() < 1){
			return iniString;
		}
		
		char[] s = iniString.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length; i++){
			int times = 1;
			char c = s[i];
			sb.append(c);
			
			for(i += 1; i < s.length && s[i] == c; i++){
				times++;
			}
			
			sb.append(times);
			i--;
		}
		
		String newString = sb.toString();
		
		return newString.length() > iniString.length() ?  iniString : newString;
	}

}
