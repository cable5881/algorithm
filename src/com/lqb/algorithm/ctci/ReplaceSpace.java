package com.lqb.algorithm.ctci;

/**
 * 请编写一个方法，将字符串中的空格全部替换为“%20”。
 * 假定该字符串有足够的空间存放新增的字符，并且知道字符串的真实长度(小于等于1000)，同时保证字符串由大小写的英文字母组成。
 * 给定一个string iniString 为原始的串，以及串的长度 int len, 返回替换后的string。
 * 测试样例：
 * 
 * "Mr John Smith”,13
 * 返回："Mr%20John%20Smith"
 * 
 * ”Hello  World”,12
 * 返回：”Hello%20%20World”
 * 
 * @Author:JackBauer
 * @Date:2016年7月22日 下午4:32:55
 */
public class ReplaceSpace {
	
	public static void main(String[] args) {
		ReplaceSpace test = new ReplaceSpace();
		String a = "Mr John Smith";
		String b = test.replaceSpace(a, 13);
		System.out.println(b);
	}
	
	public String replaceSpace(String iniString, int length) {
		if(iniString == null || length <= 0){
			return iniString;
		}
		
		int spaces = 0;
		char[] a = iniString.toCharArray();
		
		for(char c : a){
			if(c == ' '){
				spaces++;
			}
		}
		
		char[] b = new char[length + 2 * spaces];
		
		int i = length - 1;
		int j = b.length - 1;
		
		while(i >= 0){
			if(a[i] != ' '){
				b[j--] = a[i--];
			}else{
				b[j--] = '0';
				b[j--] = '2';
				b[j--] = '%';
				
				i--;
			}
		}
		
		return new String(b);
    }
}
