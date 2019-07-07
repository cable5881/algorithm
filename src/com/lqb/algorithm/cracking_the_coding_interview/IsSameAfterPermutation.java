package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 给定两个字符串，请编写程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 这里规定大小写为不同字符，且考虑字符串重点空格。
 * 给定一个string stringA和一个string stringB，请返回一个bool，代表两串是否重新排列后可相同。
 * 保证两串的长度都小于等于5000。
 * 
 * "This is nowcoder","is This nowcoder"
 * 返回：true
 * 
 * "Here you are","Are you here"
 * 返回：false
 * @Author:JackBauer
 * @Date:2016年7月22日 下午3:37:04
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
	 * @Date:2016年7月26日
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
