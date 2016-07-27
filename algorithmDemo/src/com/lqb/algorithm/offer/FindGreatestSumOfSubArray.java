package com.lqb.algorithm.offer;

/**
 * {6,-3,-2,7,-15,1,2,2},����������������Ϊ8(�ӵ�0����ʼ,����3��Ϊֹ)
 * 
 * @author:JackBauer
 * @date:2016��6��15�� ����4:25:32
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

		int oldSum = array[0];// ע���ֵ����Ԫ�أ�����0�޷�ͨ����������-2, -8, -1, -5, -9
		int nowSum = array[0];

		//ע���1��ʼ������array[0]�ᱻ�ظ����
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
