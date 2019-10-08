package com.lqb.offer;

/**
 * {6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)
 * 
 * @author:JackBauer
 * @date:2016年6月15日 下午4:25:32
 */
public class FindGreatestSumOfSubArray {

	public static void main(String[] args) {
		// int[] array = {6,-3,-2,7,-15,1,2,2};
		// int[] array = { 1, -2, 3, 10, -4, 7, 2, -5 };
		// int[] array = { -2, -8, -1, -5, -9 };
		int[] array = { 2, 8, 1, 5, 9 };
		// int[] array = null;
		// int[] array = {};
		// int[] array = {1};
		System.out.println(findGreatestSumOfSubArray(array));
	}

	public static int findGreatestSumOfSubArray(int[] array) {

		if (array == null || array.length == 0) {
			return 0;
		}

		int oldSum = array[0];// 注意初值赋首元素，若赋0无法通过测试用例-2, -8, -1, -5, -9
		int nowSum = array[0];

		//注意从1开始，否则array[0]会被重复添加
		for (int i = 1; i < array.length; i++) {

			if (nowSum <= 0) {
				nowSum = array[i];
			} else {
				nowSum += array[i];
			}

			if (nowSum > oldSum) {
				oldSum = nowSum;
			}

		}

		return oldSum;
	}

}
