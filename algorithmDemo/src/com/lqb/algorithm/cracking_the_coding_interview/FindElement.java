package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ��һ���Ź�������飬����n����������������������������һ�����ȵ���λ
 * ���磬ԭ����Ϊ[1,2,3,4,5,6]��������λ5��λ�ü������[6,1,2,3,4,5],
 * ���ڶ�����λ������飬��Ҫ����ĳ��Ԫ�ص�λ�á�
 * �����һ�����Ӷ�Ϊlog������㷨����������
 * 
 * ����һ��int����A��Ϊ��λ������飬ͬʱ���������Сn����Ҫ���ҵ�Ԫ�ص�ֵx���뷵��x��λ��(λ�ô��㿪ʼ)��
 * ��֤������Ԫ�ػ��졣
 * 
 * @author:JackBauer
 * @date:2016��10��15��
 */
public class FindElement {

	public static void main(String[] args) {
		FindElement test = new FindElement();
		
//		int[] a = { 5, 6, 1, 2, 3, 4 };
//		System.out.println(test.findElementSimple(a,6));
		
		int[] b1 = { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 };
		int[] b2 = { 5, 6, 7, 8, 9, 10, 1, 2, 3, 4 };
		int[] b3 = { 2, 3, 4, 5, 6, 1 };
		int[] b4 = { 9, 1, 2, 3, 4, 5, 6, 7, 8 };
//	    System.out.println(test.findElementComplex(b1,1)); // 3
		System.out.println(test.findElementComplex(b2,6)); // 6
//		System.out.println(test.findElementComplex(b1,10)); // 2
//		System.out.println(test.findElementComplex(b2,10)); // 5
//		System.out.println(test.findElementComplex(b3,2)); // 0
		System.out.println(test.findElementComplex(b4,9)); // 0
	}

	public int findElement(int[] a, int len, int x) {
		return x;
	}

	/**
	 * �򵥰棺O(n)
	 * @author:JackBauer
	 * @date:2016��10��17��
	 */
	public int findElementSimple(int[] a, int x) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * ���Ӱ�: O(logn)
	 * @author:JackBauer
	 * @date:2016��10��17��  ����8:59:35
	 */
	public int findElementComplex(int[] a, int x) {
		int start = 0;
		int end = a.length - 1;
		int mid;
		
		while(start <= end) {
			mid = (start + end) / 2;
			
			if(a[mid] == x) {
				return mid;
			} else if(a[end] > a[start]) {
				if(a[mid] > x) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else if(a[mid] > x) {
				if(x >= a[start]) { 
					// >= ����Ϊ��{ 2, 3, 4, 5, 6, 1 }; xΪ2ʱ
					end = mid - 1;
				} else if(a[mid] >= a[start]) { //��ʱmid��mid����ת��
					// >= ����ΪԪ��ֻ������ʱ,��10,1  xΪ1ʱ
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				if(x < a[start]) {
					// ���ܵ��� { 9, 1, 2, 3, 4, 5, 6, 7, 8 }; xΪ9ʱ
					start = mid + 1;
				} else if(a[mid] >= a[start]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		
		return -1;
	}
	
}
