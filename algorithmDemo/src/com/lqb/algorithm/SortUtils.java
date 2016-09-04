package com.lqb.algorithm;

public class SortUtils {

	public static void main(String[] args) {
		int[] a = { 1, 5, 4, 9, 6, 8, 2, 3, 7 };
		// int[] a = {1,2,3,4,0,6,7,8,9};
		shellSort(a);

		for (int i : a) {
			System.out.println(i);
		}
	}

	public static void shellSort(int[] a) {
		int h = a.length / 2;

		while (h > 0) {
			// ע��һ��ʼiΪh
			for (int i = h; i < a.length; i++) {
				int t = a[i];
				int j = i - h;
				
				while (j >= 0) {
					// ע��ÿ���Ǻ�t��,��a[j + h]�Ⱦʹ�
					if (t < a[j]) {
						a[j + h] = a[j];
					} else {
						//��Ϊ֮ǰ���Ѿ��ź�����,�����ǰһ����,��ô�϶���ǰһ��֮ǰ�Ķ���
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
			return;// ����ж�Ҫ���ȷŵ���ͷ����Ϊlow�п���Ϊ���������������int temp = a[low];�����
		}

		int temp = a[low];
		int l = low;
		int r = high;

		while (l < r) {

			while (l < r && temp < a[r]) {
				r--;
			}
			if (l < r) {
				// swap(a, l, r);���ý���ֵ������λ�þ�����һ����Ҫ���ǵ�λ��
				a[l++] = a[r];// ����++�������--Ҳ���Ǳ���ģ�ֻ��ÿ�ؿ�����һ�εıȽ�
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

	public static void quickSort(int a[]) {

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
	 * @Description: �߶�ð�����򣬽�������Ч����ѭ��������������������
	 * @author:JackBauer
	 * @Date:2016��3��13������2:19:33
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
			for (int j = 1; j < a.length; j++) {// ע�ⲻ��j=i
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
	 * @Description: �������򣨴�������ȣ�
	 * @author:JackBauer
	 * @Date:2016��3��13������1:53:20
	 */
	public static void seniorInsertSort(int[] a) {
		if (a == null) {
			return;
		}
		for (int i = 1; i < a.length; i++) {
			int temp = a[i], j = i;// temp�Ǳ��ֽ��ܱȽϵ�ֵ
			if (a[j - 1] > temp) // �������ڵ�һ�������бȽ�
			{
				while (j >= 1 && a[j - 1] > temp) {
					a[j] = a[j - 1];// ����������
					j--;
				}
			}
			a[j] = temp;// ���ֱȽϵ���Сֵ��������ߣ����ƿ��Ŀ�λ
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
			temp = a[i];// �����Сֵ
			flag = i;// �����Сֵ��Ӧ���±�
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
	 * @Description: �������ѡ������
	 * @author:JackBauer
	 * @Date:2016��3��13������9:55:45
	 */
	public static void selectSort2(LinkedList<Integer> list) {
		/**
		 * ��ȷ������ min�����Ͳ�ӦΪint���޷�����cur�ڵ��min��Ӧ�ڵ��ֵ��������ֻ��min��ֵ
		 * begin��Ҫ���ڲ�ѭ����ʼʱ�ݽ���min�ڵ�ҲҪ��ÿһ���ڲ�ѭ��������ݽ�
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
