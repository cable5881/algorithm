package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 有一个介于0和1之间的实数，类型为double，返回它的二进制表示。 如果该数字无法精确地用32位以内的二进制表示，返回“Error”。
 * 给定一个double num，表示0到1的实数，请返回一个string，代表该数的二进制表示或者“Error”。
 * 
 * 测试样例： 0.625 返回：0.101
 * 
 * @Author:JackBauer
 * @Date:2016年8月8日
 */
public class BinDecimal {

	public static void main(String[] args) {
		BinDecimal test = new BinDecimal();
		System.out.println(test.printBin(0.625));
	}

	public String printBin(double num) {
		StringBuilder sb = new StringBuilder("0.");
		
		while(num != 0){
			if((num = num * 2) >= 1){
				sb.append(1);
				num -= 1;
			}else{
				sb.append(0);
			}
			
			if(sb.length() > 32){
				return "ERROR";
			}
		}
		
		return sb.toString();
	}

}
