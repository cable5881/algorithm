package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，
 * 偶数和偶数之间的相对位置不变。
 * @author:JackBauer
 * @date:2016年6月10日 下午2:45:04
 */
public class ReOrderArray {

	public static void main(String[] args) {
//		int[] array = {};
//		int[] array = null;
		int[] array = {1,2,3,4,5,6,7,8};
		reOrderArray(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void reOrderArray(int [] array) {
		
		if( array == null || array.length == 0 ){
			return;
		}
		
		int length = array.length;
        int [] oddArr = new int[length];
        int [] evenArr = new int[length];
        int oddIndex = 0;
        int evenIndex = 0;
        
        for( int i = 0; i < length; i++ ){
        	if( (array[i] & 1) == 0 ){
        		evenArr[evenIndex++] = array[i];
        	}else{
        		oddArr[oddIndex++] = array[i];
        	}
        }
        
        evenIndex = 0;
        
        while( oddIndex < length ){
        	oddArr[oddIndex++] = evenArr[evenIndex++];
        }
        
        for( int i = 0; i < length; i++ ){
        	array[i] = oddArr[i];
        }
    }

}
