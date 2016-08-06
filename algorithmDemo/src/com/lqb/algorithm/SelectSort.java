package com.lqb.algorithm;

import java.util.Arrays;

public class SelectSort {

	public static void main(String[] args) {
		int[] a = {8,5,1,9,6,3,2,7,4};
		selectSort(a);
		System.out.println(Arrays.toString(a));
	}

	// ѡ������
	public static void selectSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			//Ĭ��ÿ�ε����ĵ�һ��Ϊ��С
			int smallIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				//�б�smallIndexλ���µ�Ԫ�ظ�С�������λ�ø�ֵ��smallIndex
				if (a[j] < a[smallIndex]) {
					smallIndex = j;
				}
			}
			
			//��ʱ������Ԫ�ض������˱Ƚ�,small�Ѿ�����С����
			//����Сֵ������i��λ��
			if(smallIndex != i){
				swap(a,i,smallIndex);
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}
