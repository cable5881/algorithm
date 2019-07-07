package com.lqb.ctrip;

/**
 * 算法思路：暴力搜索
 * 依次从每个国家开始出发，找到该行数组中的最小值，累加。最小值的索引为下一个国家的去处。
 * 到达下一个国家后继续找出找到该行数组中的最小值，累加。
 * 依次类推
 */
public class Travelling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int getMinPathLength(int[][] a) {
		if(a == null || a.length < 1) {
			return 0;
		}
		
		int countryNum = a.length;
		int[] minV = new int[countryNum];
		
		for(int i = 0; i < countryNum; i++) {
			for(int j = 0; j < countryNum; j++){
				
			}
		}
		
		return 0;
	}

}
