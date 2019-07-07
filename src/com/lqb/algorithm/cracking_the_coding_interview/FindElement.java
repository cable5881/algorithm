package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 有一个排过序的数组，包含n个整数，但是这个数组向左进行了一定长度的移位
 * 例如，原数组为[1,2,3,4,5,6]，向左移位5个位置即变成了[6,1,2,3,4,5],
 * 现在对于移位后的数组，需要查找某个元素的位置。
 * 请设计一个复杂度为log级别的算法完成这个任务。
 * 
 * 给定一个int数组A，为移位后的数组，同时给定数组大小n和需要查找的元素的值x，请返回x的位置(位置从零开始)。
 * 保证数组中元素互异。
 * 
 * @author:JackBauer
 * @date:2016年10月15日
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
	 * 简单版：O(n)
	 * @author:JackBauer
	 * @date:2016年10月17日
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
	 * 复杂版: O(logn)
	 * @author:JackBauer
	 * @date:2016年10月17日  下午8:59:35
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
					// >= 是因为如{ 2, 3, 4, 5, 6, 1 }; x为2时
					end = mid - 1;
				} else if(a[mid] >= a[start]) { //此时mid到mid中无转置
					// >= 是因为元素只有两个时,如10,1  x为1时
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				if(x < a[start]) {
					// 不能等于 { 9, 1, 2, 3, 4, 5, 6, 7, 8 }; x为9时
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
