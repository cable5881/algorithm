package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * ����һ��Ԫ�ظ�����ͬ�Ұ��������е��������У����дһ���㷨������һ�ø߶���С�Ķ����������
 * ����һ����������int[] vals,�뷵�ش����Ķ���������ĸ߶ȡ�
 * @Author:JackBauer
 * @Date:2016��8��5��
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
