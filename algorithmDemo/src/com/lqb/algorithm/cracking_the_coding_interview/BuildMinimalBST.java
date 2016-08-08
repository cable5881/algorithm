package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * 对于一个元素各不相同且按升序排列的有序序列，请编写一个算法，创建一棵高度最小的二叉查找树。
 * 给定一个有序序列int[] vals,请返回创建的二叉查找树的高度。
 * @Author:JackBauer
 * @Date:2016年8月5日
 */
public class BuildMinimalBST {

	public static void main(String[] args) {
		System.out.println(buildMinimalBST(new int[8]));
	}
	
	public static int buildMinimalBST(int[] vals) {
		if(vals == null || vals.length < 1){
			return 0;
		}
		int value = vals.length;
		String str = Math.ceil(Math.log(value + 1) / Math.log(2)) + "";
		str = str.substring(0,str.lastIndexOf("."));
		return Integer.valueOf(str);
    }

}
