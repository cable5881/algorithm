package com.lqb.algorithm;

public class SortUtils {

    public static void main(String[] args) {
        int[] a = {1, 5, 4, 9, 6, 8, 2, 3, 7};
        // int[] a = {1,2,3,4,0,6,7,8,9};
        shellSort(a);

        for (int i : a) {
            System.out.println(i);
        }
    }

	public static void shellSort(int[] a) {
		int h = a.length / 2;

		while (h > 0) {
			// 注意一开始i为h
			for (int i = h; i < a.length; i++) {
				int t = a[i];
				int j = i - h;
				
				while (j >= 0) {
					// 注意每次是和t比,和a[j + h]比就错
					if (t < a[j]) {
						a[j + h] = a[j];
					} else {
						//因为之前的已经排好序了,如果比前一个大,那么肯定比前一个之前的都大
						break;
					}
					
					j -= h;
				}
				
				a[j + h] = t;
			}

			h /= 2;
		}
	}

	public static void qSort(int[] a, int low, int high) {
		if (high <= low) {
            return;// 这个判断要首先放到开头，因为low有可能为负数，所以下面的int temp = a[low];会出错
		}

		int temp = a[low];
		int l = low;
		int r = high;

		while (l < r) {

			while (l < r && temp < a[r]) {
				r--;
			}
			if (l < r) {
				// swap(a, l, r);不用交换值，它的位置就是下一个将要覆盖的位置
                // 这是++和下面的--也不是必须的，只是每回可以少一次的比较
                a[l++] = a[r];// 这是++和下面的--也不是必须的，只是每回可以少一次的比较
			}

			while (l < r && temp >= a[l]) {
				l++;
			}
			if (l < r) {
				a[r--] = a[l];
				// swap(a, l, r);
			}

			// if(temp>a[r]){
			// swap(a,l,r);
			// l++;
			// }else{
			// swap(a, l, r);
			// r--;
			// }
		}

		a[r] = temp;

		qSort(a, low, r - 1);
		qSort(a, r + 1, high);
	}

    public static void quickSort(int[] a) {
		qSort(a, 0, a.length - 1);
	}

	public static void mergeSort(int[] a, int start, int length) {
		if (length == 1) {
			return;
		}

		int lenA = length / 2;
		int lenB = length - lenA;

		mergeSort(a, start, lenA);
		mergeSort(a, start + lenA, lenB);

		merging(a, start, lenA, lenB);
	}

	private static void merging(int[] a, int start, int lenA, int lenB) {
		int[] L = new int[lenA];
		int[] R = new int[lenB];
		int k = start;
		int i, j;

		for (i = 0; i < lenA; i++, k++) {
			L[i] = a[k];
		}
		for (j = 0; j < lenB; j++, k++) {
			R[j] = a[k];
		}

		i = 0;
		j = 0;
		k = start;

		while (i < lenA && j < lenB) {
			if (L[i] < R[j]) {
				a[k++] = L[i++];
			} else {
				a[k++] = R[j++];
			}
		}

		while (i < lenA) {
			a[k++] = L[i++];
		}

		while (j < lenB) {
			a[k++] = R[j++];
		}
	}

	/**
	 * @Description: 高端冒泡排序，结论是有效减少循环次数，交换次数不变
	 * @author:JackBauer
	 * @Date:2016年3月13日下午2:19:33
	 */
	public static void bubbleSort(int[] a) {
		// 8 64 4
		// 5 40 4
		boolean flag = true;
		int outerLoop = 0;
		int innerLoop = 0;
		int swapTimes = 0;

		for (int i = 1; i < a.length && flag; i++) {
			flag = false;
			outerLoop++;
			for (int j = 1; j < a.length; j++) {// 注意不是j=i
				innerLoop++;
				if (a[j - 1] > a[j]) {
					swapTimes++;
					int temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;

					flag = true;
				}
			}
		}
		System.out.println("outerLoop=" + outerLoop);
		System.out.println("innerLoop=" + innerLoop);
		System.out.println("swapTimes=" + swapTimes);
	}

	/**
	 * @Description: 插入排序（从右向左比）
	 * @author:JackBauer
	 * @Date:2016年3月13日下午1:53:20
	 */
	public static void seniorInsertSort(int[] a) {
		if (a == null) {
			return;
		}
		for (int i = 1; i < a.length; i++) {
			int temp = a[i], j = i;// temp是本轮接受比较的值
			if (a[j - 1] > temp) // 和与相邻的一个数进行比较
			{
				while (j >= 1 && a[j - 1] > temp) {
					a[j] = a[j - 1];// 数组往右移
					j--;
				}
			}
			a[j] = temp;// 本轮比较的最小值放在最左边，即移开的空位
		}
	}

	public static void insertSort(int[] a) {
		int temp = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > a[i - 1]) {
				continue;
			}
			for (int j = 0; j < i; j++) {// j<a.length
				if (a[i] > a[j]) {
					continue;
				} else {
					temp = a[i];// temp = a[j]
					int k = i;
					while (k > j) {
						a[k] = a[k - 1];
						k--;
					}
					a[j] = temp;
					break;
				}
			}
		}
	}

	public static void seniorSelectSort(int[] a) {
		int temp = 0;
		int flag = 0;
		int n = a.length;

		for (int i = 0; i < n; i++) {
			temp = a[i];// 存放最小值
			flag = i;// 存放最小值对应的下标
			for (int j = i + 1; j < n; j++) {
				if (a[j] < temp) {
					temp = a[j];
					flag = j;
				}
			}

			if (flag != i) {
				a[flag] = a[i];
				a[i] = temp;
			}
		}
	}

	public static void selectSort(int[] a) {
		int len = a.length;

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	/**
	 * 
	 * @Description: 单链表的选择排序
	 * @author:JackBauer
	 * @Date:2016年3月13日上午9:55:45
	 */
	public static void selectSort2(LinkedList<Integer> list) {
		/**
		 * 正确做法： min的类型不应为int，无法交换cur节点和min对应节点的值，交换的只是min的值
		 * begin需要在内层循环开始时递进，min节点也要在每一次内层循环结束后递进
		 */
		Node<Integer> begin = list.head;
		Node<Integer> cur = null;
		Node<Integer> min = list.head;
		int len = list.length();
		int temp;

		for (int i = 1; i < len; i++) {
			begin = begin.next;
			cur = begin;
			for (int j = i + 1; j <= len; j++) {
				if (min.data > cur.data) {
					temp = cur.data;
					cur.data = min.data;
					min.data = temp;
				}

				cur = cur.next;
			}
			min = min.next;
		}
	}

	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}
