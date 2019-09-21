package com.lqb.algorithm.ctci;

/**
 * 请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。
 * 给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。
 * 测试样例：
 * 
 * "aeiou"
 * 返回：True
 * 
 * "BarackObama"
 * 返回：False
 * 
 * @Author:JackBauer
 * @Date:2016年7月25日 上午9:30:17
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
	 * 暴力搜索
	 * @author:JackBauer
	 * @date:2016年10月16日
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
