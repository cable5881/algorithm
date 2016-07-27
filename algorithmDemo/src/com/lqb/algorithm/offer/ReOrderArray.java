package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��
 * ʹ�����е�����λ�������ǰ�벿�֣����е�ż��λ��λ������ĺ�벿�֣�����֤������������
 * ż����ż��֮������λ�ò��䡣
 * @author:JackBauer
 * @date:2016��6��10�� ����2:45:04
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
