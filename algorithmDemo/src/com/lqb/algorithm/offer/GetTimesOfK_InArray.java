package com.lqb.algorithm.offer;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 
 * @author:JackBauer
 * @date:2016年6月17日 下午3:24:40
 */
public class GetTimesOfK_InArray {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		GetTimesOfK_InArray test = new GetTimesOfK_InArray();

		 int[] array = { 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9,10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
//		 int[] array = {3,3,3,3,4,5};
//		int[] array = { 3, 3, 3, 3 };
//		System.out.println(test.GetNumberOfK(array, 3));
//		System.out.println(myGetNumberOfK(array, 2));
		
		long end = System.currentTimeMillis();
		System.out.println("This program cost " + (end - start) + " s");
	}

	public int GetNumberOfK(int[] array, int k) {

		if (array == null || array.length <= 0) {
			return -1;
		}

		// int firstKIndex = getFirstKIndex(array, k);
		// int lastkIndex = getLastKIndex(array, k);

		int firstKIndex = getFirstKIndex_recursion(array, k, 0, array.length - 1);
		int lastkIndex = getLastKIndex_recursion(array, k, 0, array.length - 1);

		return firstKIndex > -1 ? lastkIndex - firstKIndex + 1 : 0;
	}

	private int getFirstKIndex(int[] array, int k) {

		int start = 0;
		int end = array.length - 1;
		int mid = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			if (array[mid] == k) {
				if (mid - 1 > 0 && array[mid - 1] == k) {
					end = mid - 1;
					continue;
				} else if (mid == 0 || array[mid - 1] != k) {
					return mid;
				}
			} else if (array[mid] < k) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return -1;
	}

	private int getLastKIndex(int[] array, int k) {

		int start = 0;
		int end = array.length - 1;
		int mid = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			if (array[mid] == k) {
				if (mid + 1 < array.length && array[mid + 1] == k) {
					start = mid + 1;
					continue;
				} else if (mid == end || array[mid + 1] != k) {
					return mid;
				}
			} else if (array[mid] < k) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return -1;
	}

	private int getFirstKIndex_recursion(int[] array, int k, int start, int end) {

		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;

		if (array[mid] == k) {
			if (mid - 1 >= 0 && array[mid - 1] == k) {
				return getFirstKIndex_recursion(array, k, start, mid - 1);
			} else if (mid == 0 || array[mid - 1] != k) {
				return mid;
			}
		} else if (array[mid] < k) {
			return getFirstKIndex_recursion(array, k, mid + 1, end);
		} else {
			return getFirstKIndex_recursion(array, k, mid + 1, mid - 1);
		}
		return mid;
	}

	private int getLastKIndex_recursion(int[] array, int k, int start, int end) {

		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;

		if (array[mid] == k) {
			if (mid + 1 < array.length && array[mid + 1] == k) {
				return getLastKIndex_recursion(array, k, mid + 1, end);
			} else if (mid == end || array[mid + 1] != k) {
				return mid;
			}
		} else if (array[mid] < k) {
			return getLastKIndex_recursion(array, k, mid + 1, end);
		} else {
			return getLastKIndex_recursion(array, k, start, mid - 1);
		}
		return mid;
	}

	public static int myGetNumberOfK(int[] a, int k) {
		if(a == null || a.length < 1){
			return 0;
		}else if(a.length == 1 && a[0] == k){
			return 1;
		}
		
		int start = 0;
		int end = a.length - 1;
		int mid = start;
		
		
		while(start < end){
			mid = (start + end) / 2;
			
			if(a[mid] < k){
				start = mid + 1;
			}else if(a[mid] > k){
				end = mid - 1;
			}else{
				return myGetLastK(a, mid, end) - myGetFirstK(a, start, mid) + 1;
			}
		}
		
		return 0;
	}

	private static int myGetFirstK(int[] a, int start, int mid) {
		int k = a[mid];
		int end = mid;
		
		while(end - start > 1){
			mid = (start + end) / 2;
			
			if(a[mid] == k){
				end = mid;
			}else if(a[mid] < k){
				start = mid;
			}
		}
		
		if(end >= start && a[end] == a[start]){
			return start;
		}else{
			return end;
		}
	}

	private static int myGetLastK(int[] a, int mid, int end) {
		int k = a[mid];
		int start = mid;
		
		while(end - start > 1){
			mid = (start + end) / 2;
			
			if(a[mid] == k){
				start = mid;
			}else if(a[mid] > k){
				end = mid;
			}
		}
		
		if(end >= start && a[end] == a[start]){
			return end;
		}else{
			return start;
		}
		
	}

}
