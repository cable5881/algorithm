package com.lqb.algorithm;

/**
 * @Description:归并排序
 * @Author:JackBauer
 * @Date:2016年7月20日 上午11:28:57
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = { 1, 9, 7, 5, 2, 8, 4, 3, 6 };
		new MergeSort().mergeSort(a);

		for (int i : a) {
			System.out.print(i + " ");
		}
		
	}

	public void mergeSort(int[] a) {
		if (a == null || a.length < 1) {
			return;
		}

		mergeSortCore(a, new int[a.length], 0, a.length - 1);
	}

	private void mergeSortCore(int[] a, int t[], int start, int end) {
		if (end <= start) {
			return;
		}

		int leftStart = start;
		int leftEnd = (start + end) / 2;
		int rightStart = leftEnd + 1;
		int rightEnd = end;

		mergeSortCore(a, t, leftStart, leftEnd);
		mergeSortCore(a, t, rightStart, rightEnd);

		int i = t.length - 1;

		while (leftEnd >= leftStart && rightStart <= rightEnd) {
			if (a[leftEnd] > a[rightEnd]) {
				t[i--] = a[leftEnd--];
			} else {
				t[i--] = a[rightEnd--];
			}
		}

		while (leftEnd >= leftStart) {
			t[i--] = a[leftEnd--];
		}

		while (rightStart <= rightEnd) {
			t[i--] = a[rightEnd--];
		}

		while (start <= end) {
			a[start++] = t[++i];
		}

	}

}
