package com.lqb.algorithm;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] a = { -1, 3, 4, 1, 2, 8, 7, 6, 0 };

		heapSort(a, 8);
		
		System.out.println(Arrays.toString(a));
	}

	/**
	 * @Description: ������
	 * @param a
	 * @param n ���鳤��,Ĭ��a[0]Ϊ��ЧԪ�أ�Ϊ�˿�����ֱ��
	 */
	public static void heapSort(int[] a, int n) {
		int i;
		
		//��ѡ������Ԫ�ط��ڶѶ�
		//��iΪÿ�����ڵ�
		for (i = n / 2; i > 0; i--) {
			heapAdjust(a, i, n);
		}

		//ÿһ�ֽ����󶥲���������Ԫ��
		for (i = n; i > 1; i--) {
			//����������Ԫ�ص��������
			swap(a, 1, i);
			//����Ԫ�ر������Ѷ�����һ�ν��й����
			//ע����ʱ�ǴӶѶ�(���±�Ϊ1)��ʼ���¹���
			//��Ϊ��������Ԫ�ص�ĩβ��,�ӶѶ���ʼ�ĵڶ����а����еڶ����Ԫ��,������һ�ֱȽϺ�����Ľ��
			//������һ�ֵĽ��,����ֱ�Ӱѵڶ���Ԫ�طŵ��Ѷ�
			//ͬ����һ�εڶ������е������Ԫ��
			//����ִ�����¿�ʼ,��ΪԪ�ض��Ѿ��ǱȽϺ��˵�,ÿһ���Ԫ�ض�������Ӧ��һ�����Ԫ��Ҫ��,��ʱ�Ƕ���Ĳ���
			heapAdjust(a, 1, i - 1);
		}
	}

	
	/**
	 * @Description: �����
	 * @param a 
	 * @param p ���ڵ��±�
	 * @param n ���һ��Ԫ���±�
	 */
	private static void heapAdjust(int a[], int p, int n) {
		int temp = a[p];//���ڵ�

		for (int i = 2 * p; i <= n; i *= 2) {
			//iΪ�����±�
			//����Һ��Ӵ�������,��i+1ָ���Һ���
			//i < n����Ϊ���ڵ����ֻ��һ������,��ʱ����a[i + 1]�Ļ���Խ����
			if (i < n && a[i] < a[i + 1])
				i++;
			
			//������ڵ�����ĺ��ӻ�Ҫ��ֱ������ѭ��
			//ע��ÿ�ζ��Ǻ�temp�Ƚϣ���Ϊtemp���䣬ÿһ�ζ��Ǹ��ڵ�
			//д��a[p] >= a[i]�ʹ���
			if (temp >= a[i])
				break;
			
			//���ĺ��ӱȸ��ڵ㻹Ҫ��,��ú�������Ϊ���ڵ�,���ú��ӵ�ֵ���Ǹ��ڵ��ֵ
			a[p] = a[i];
			//����������¸��ڵ㣬���º��ӣ�ԭ���ڵ㣬ֵ��Ϊtemp������Ϊ��һ�ֵĸ��ڵ���������
			p = i;
		}
		
		//�¸��ڵ�(p = i)��û�б���ֵ���˳���ѭ��,���ø��ڵ��Ѿ�û�к�����
		//���Ǹ�����ֵ��Ϊ��ԭʼ�ĸ��ڵ��ֵtemp
		a[p] = temp;
	}

	/**
	 * @Description: �����򣬴��±�0��ʼ
	 * @param a
	 */
	public static void heapSort2(int[] a) {
		if (a == null || a.length < 1) {
			return;
		}

		int len = a.length;

		//ע����i = len / 2 - 1
		for (int i = len / 2 - 1; i >= 0; i--) {
			heapAdjust2(a, i, len - 1);
		}

		for (int i = len - 1; i > 0; i--) {
			swap(a, 0, i);
			heapAdjust2(a, 0, i - 1);
		}

	}

	/**
	 * @Description: ���춥�ѣ����±�0��ʼ
	 * @param a
	 * @param parent
	 * @param end
	 */
	private static void heapAdjust2(int[] a, int parent, int end) {
		int temp = a[parent];
		
		//ע����i = parent * 2 + 1.
		for (int i = parent * 2 + 1; i <= end; i = i * 2 + 1) {
			if(i < end && a[i] < a[i + 1]){
				i++;
			}
			
			if(a[i] > temp){
				a[parent] = a[i];
				parent = i;
			}else{
				break;
			}
		}

		a[parent] = temp;
	}
	
	
	/**
	 * @Description: ����Ԫ��
	 * @param a
	 * @param i
	 * @param j
	 */
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
