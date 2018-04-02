package com.lqb.algorithm;

import java.util.Arrays;

public class SelectSort {

	public static void main(String[] args) {
		int[] a = {8,5,1,9,6,3,2,7,4};
		selectSort(a);
		System.out.println(Arrays.toString(a));
	}

	// 选择排序
	public static void selectSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			//默认每次迭代的第一个为最小
			int smallIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				//有比smallIndex位置下的元素更小的则把其位置赋值给smallIndex
				if (a[j] < a[smallIndex]) {
					smallIndex = j;
				}
			}
			
			//此时和所有元素都进行了比较,small已经是最小的了
			//将最小值调换到i的位置
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
