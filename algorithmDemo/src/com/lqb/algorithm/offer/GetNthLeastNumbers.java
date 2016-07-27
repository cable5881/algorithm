package com.lqb.algorithm.offer;

import java.util.ArrayList;

/**
 * ����n���������ҳ�������С��K��������������4,5,1,6,2,7,3,8��8�����֣�����С��4��������1,2,3,4,�� 
 * @author:JackBauer
 * @date:2016��6��14�� ����1:38:42
 */
public class GetNthLeastNumbers {

	public static void main(String[] args) {
		int[] array = {4,5,1,6,2,7,3,8};
		System.out.println(GetLeastNumbers_Solution(array, 1));
	}
	
	
	public static ArrayList<Integer> GetLeastNumbers_Solution(int [] array, int k) {
		
		ArrayList<Integer> leastNumbers = new ArrayList<>();
		
		if (array == null || array.length == 0 || k <= 0 || k > array.length) {
			return leastNumbers;
		}

		QSort(array, 0, array.length - 1, k);
		
		for(int i = 0; i < k; i++){
			leastNumbers.add(array[i]);
		}
		
		return leastNumbers;
    }
	
	public static void QSort(int[] array, int start, int end, int k) {

		int point;

		if (start < end) {
			point = partition(array, start, end);
			
			if( point == k - 1){
				return;
			}else{
				QSort(array, point + 1, end, k);
				QSort(array, start, point - 1, k);
			}
		}
		
	}

	public static int partition(int[] array, int start, int end) {

		int temp = array[start];
		int l = start;
		int r = end;

		while (l < r) {
			if (temp <= array[r]) {
				r--;
			} else {
				swap(array, r, l);
				l++;
				while (l < r) {
					if (temp >= array[l]) {
						l++;
					} else {
						swap(array, r, l);
						r--;
						break;
					}
				}
			}
		}

		return l;
	}

	public static void swap(int[] array, int from, int to) {
		int temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}

}
