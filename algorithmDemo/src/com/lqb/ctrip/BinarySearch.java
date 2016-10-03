package com.lqb.ctrip;

import java.util.Scanner;

/**
 * ��дһ�����ֲ����㷨����һ�������ȳ��ֵ�index����������ڼ�������Ҫ����(-1)-��ǰ��Ӧ�ó��ֵ�λ�á�����
 * [1,3,6],����5,5Ӧ������index=2��λ�õ������ڼ����С�����(-1)-2 = -3��
 * 
 * 3 5 
 * 0 1 3 5 6
 * 
 * @Date:2016��9��17�� ����8:50:53
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
