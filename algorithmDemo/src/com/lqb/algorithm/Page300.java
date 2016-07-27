package com.lqb.algorithm;

/**
 * @Description:输出字符串的所有组合
 * @author:JackBauer
 * @date:2016年3月17日 下午3:35:13
 */
public class Page300 {

	public static void main(String[] args) {
		String s = "abcde";
		char[] c = s.toCharArray();
		StringBuilder sb = new StringBuilder("");
		int len = c.length;
//		for (int i = 1; i <= len; i++) {
//			combineRecursiveImpl(c, 0, i, sb);// 规定组合长度
//		}

		// outPutOne(c, 0);
		outPutTwo(c, 0,1,sb);
	}

	public static void combineRecursiveImpl(char[] c, int begin, int len, StringBuilder sb) {
		if (len == 0) {
			System.out.println(sb.toString());
			return;
		}

		if (begin == c.length) {
			return;
		}

		sb.append(c[begin]);
		combineRecursiveImpl(c, begin + 1, len - 1, sb);
		sb.deleteCharAt(sb.length() - 1);
		combineRecursiveImpl(c, begin + 1, len, sb);

	}

	public static void outPutOne(char[] c, int index) {
		if (index == c.length) {
			return;
		}

		System.out.println(c[index++]);
		outPutOne(c, index);
	}

	public static void outPutTwo(char[] c, int begin, int len, StringBuilder sb) {
		if(len == 0){
			System.out.println(sb.toString());
			return;
		}
		
		if(begin == c.length){
			return;
		}
		
		sb.append(c[begin]);
		outPutTwo(c, begin + 1, len + 1, sb);
		sb.deleteCharAt(len - 1);
		outPutTwo(c, begin + 1, len, sb);
	}

}
