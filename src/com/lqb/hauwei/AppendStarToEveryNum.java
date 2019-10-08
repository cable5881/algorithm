package com.lqb.hauwei;

import java.util.Scanner;

public class AppendStarToEveryNum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		while((s = in.nextLine()) != null) {
			System.out.println(markNum(s));
		}
		in.close();
	}
	
	private static String markNum(String pInStr) {
		if(pInStr == null || pInStr.equals("")) {
			return pInStr;
		}
		
		StringBuilder sb = new StringBuilder();
		char[] ch = pInStr.toCharArray();
		
		for(int i = 0; i < ch.length; i++) {
			if(ch[i] >= '0' && ch[i] <= '9') {
				sb.append("*");
				sb.append(ch[i]);
				while(++i < ch.length) {
					if(ch[i] < '0' || ch[i] > '9') {
						break;
					}
					sb.append(ch[i]);
				}
				sb.append("*");
			} else {
				sb.append(ch[i]);
			}
		}
		
		return sb.toString();
	}

}
