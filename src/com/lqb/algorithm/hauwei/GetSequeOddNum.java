package com.lqb.algorithm.hauwei;

import java.util.Scanner;

/**
 * 功能: 验证尼科彻斯定理，即：任何一个整数m的立方都可以写成m个连续奇数之和。 原型： int GetSequeOddNum(int m,char *
 * pcSequeOddNum);
 * 
 * 输入参数： int m：整数(取值范围：1～100)
 * 
 * 返回值： m个连续奇数(格式：“7+9+11”);
 */
public class GetSequeOddNum {

	public static void main(String[] args) {
		GetSequeOddNum test = new GetSequeOddNum();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int m = in.nextInt();
			System.out.println(test.getSequeOddNum(m));
		}
		in.close();
	}

	//错解
	public String getSequeOddNum(int m) {
		int trippleM = m * m * m;
		int sum = 0;
		int start = 1;
		int end = -1;

		while (end <= trippleM && sum < trippleM) {
			end += 2;
			sum += end;
			if (sum == trippleM && (end - start) / 2 + 1 == m) {
				return getString(start, end);
			} else if (sum > trippleM) {
				while (sum > trippleM) {
					sum -= start;
					start += 2;
				}
//				sum -= end;
			}
		}

		if (sum == trippleM && (end - start) / 2 + 1 == m) {
			return getString(start, end);
		} else{
			return null;
		}
	}

	private String getString(int start, int end) {
		StringBuilder sb = new StringBuilder();
		sb.append(start);
		for (int i = start += 2; i <= end; i += 2) {
			sb.append("+");
			sb.append(i);
		}

		return sb.toString();
	}
	
	/*
	 正确解法：
	   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int m = sc.nextInt();
            int a = m*m-m+1;
            String str = String.valueOf(a);
            for(int i=1;i<m;i++){
                a += 2;
                str = str+"+"+String.valueOf(a);
            }
            System.out.println(str);
        }
    }
	  
	 */

}
