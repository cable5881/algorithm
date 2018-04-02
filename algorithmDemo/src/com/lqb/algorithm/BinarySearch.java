package com.lqb.algorithm;

/**
 * @Description:二分查找
 * @Author:JackBauer
 * @Date:2016年9月4日 下午8:27:58
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = { 10, 11, 12, 16, 18, 23, 29, 33, 48, 54, 57, 68, 77, 84, 98 };
		System.out.println(rank(a, 48));
	}

	public static int rank(int[] a, int key) {
		if (a == null) {
			return -1;
		}

		int low = 0;
		int high = a.length - 1;
		int mid;

		//注意low和high是可以相等的
		while (low <= high) {
			mid = (high + low) / 2;

			if (a[mid] > key) {
				high = mid - 1;
			} else if (a[mid] < key) {
				low = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

}
