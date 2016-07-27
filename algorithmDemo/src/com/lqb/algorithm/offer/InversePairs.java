package com.lqb.algorithm.offer;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * @author:JackBauer
 * @date:2016年6月21日 下午10:17:13
 */
public class InversePairs {

	public static void main(String[] args) {
		int[] array = {9,7,5,1,2,1};
//		int[] array = {9,7,5};
//		int[] array = {9};
//		int[] array = {};
//		int[] array = null;
		InversePairs test = new InversePairs();
		System.out.println(test.myGetInversePairs(array));
		
//		System.out.println(Integer.MAX_VALUE > 1000000007);

	}

	public int getInversePairs(int[] array) {
		if(array == null || array.length < 2){
			return 0;
		}
		
		return InversePairsCore(array, new int[array.length], 0, array.length - 1);
	}
	
	private int InversePairsCore(int[] array, int[] copy, int start, int end){
		
		if(start >= end){
			return 0;
		}
		
		int another_start = (start + end) / 2 + 1;
		
		int left_count = InversePairsCore(array, copy, start, another_start - 1);
		int right_count = InversePairsCore(array, copy, another_start, end);
		
		int big_index1 = (start + end) / 2;
		int big_index2 = end;
		int copy_index = copy.length - 1;
		int count = 0;
		
		while(big_index1 >= start && big_index2 >= another_start){
			if(array[big_index1] > array[big_index2]){
				copy[copy_index--] = array[big_index1--];
				count += big_index2 - another_start + 1;
			}else{
				copy[copy_index--] = array[big_index2--];
			}
		}
		
		while(big_index1 >= start){
			copy[copy_index--] = array[big_index1--];
		}
		
		while(big_index2 >= another_start){
			copy[copy_index--] = array[big_index2--];
		}
		
		for(int i = start; i <= end; i++){
			array[i] = copy[++copy_index];
		}
		
		return count + left_count + right_count;
	}


	public int myGetInversePairs(int[] a) {
		if(a == null || a.length < 1){
			return 0;
		}
		
		return myInversePairsCore(a, new int[a.length], 0, a.length - 1) % 1000000007;
	}
	
	private int myInversePairsCore(int[] a, int[] t, int start, int end){
		if (end <= start) {
			return 0 ;
		}

		int leftStart = start;
		int leftEnd = (start + end) / 2;
		int rightStart = leftEnd + 1;
		int rightEnd = end;

		int leftCount = myInversePairsCore(a, t, leftStart, leftEnd);
		int rightCount = myInversePairsCore(a, t, rightStart, rightEnd);
		int mainCount = 0;
		int i = t.length - 1;

		while (leftEnd >= leftStart && rightStart <= rightEnd) {
			if (a[leftEnd] > a[rightEnd]) {
				mainCount += (rightEnd - rightStart + 1);
				t[i--] = a[leftEnd--];
				
				if(mainCount > 1000000007)//数值过大求余
                {
					mainCount %= 1000000007;
                }
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
		
		return (leftCount + rightCount + mainCount) % 1000000007;
	}
}
