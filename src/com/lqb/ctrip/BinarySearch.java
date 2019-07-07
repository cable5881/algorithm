package com.lqb.ctrip;

import java.util.Scanner;

/**
 * 请写一个二分查找算法查找一个数最先出现的index，如果数不在集合中需要返回(-1)-当前数应该出现的位置。例如
 * [1,3,6],查找5,5应该是在index=2的位置但并不在集合中。返回(-1)-2 = -3。
 * 
 * 3 5 
 * 0 1 3 5 6
 * 
 * @Date:2016年9月17日 下午8:50:53
 */
public class BinarySearch {

	private static boolean flag = true;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int key = in.nextInt();
		int length = in.nextInt();

		int[] a = new int[length];
		for (int i = 0; i < length; i++) {
			a[i] = in.nextInt();
		}
		int index = binarySearch(a, key);
		if (flag) {
			System.out.println(index);
		} else {
			System.out.println(-1 - index);
		}
	}

	public static int binarySearch(int[] a, int key) {
		if (a == null) {
			return -1;
		}

		int low = 0;
		int high = a.length - 1;
		int mid = 0;

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

		flag = false;
		return mid;
	}

}
