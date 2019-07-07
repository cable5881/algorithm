package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 假定我们都知道非常高效的算法来检查一个单词是否为其他字符串的子串。
 * 请将这个算法编写成一个函数，给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成，要求只能调用一次检查子串的函数。
 * 给定两个字符串s1,s2,请返回bool值代表s2是否由s1旋转而成。
 * 字符串中字符为英文字母和空格，区分大小写，字符串长度小于等于1000。
 * 测试样例：
 * 
 * "Hello world","worldhello "
 * 返回：false
 * 
 * "waterbottle","erbottlewat"
 * 返回：true
 * @Author:JackBauer
 * @Date:2016年7月25日 下午5:05:07
 */
public class IsRotation {

	public static void main(String[] args) {
		IsRotation test = new IsRotation();
		String str = "waterbottle";
		String subStr = "erbottlewat";
		
		
		System.out.println(test.checkReverseEqual(str, subStr));
	}
	
	public boolean checkReverseEqual(String s1, String s2) {
		if(s1 == null && s2 == null){
			return true;
		}else if(s1 == null || s2 == null){
            return false;
        }
		
		if(s1.length() != s2.length()){
			return false;
		}
		
		String s3 = s1 + s1;
		
		return isSubstring(s3, s2);
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
