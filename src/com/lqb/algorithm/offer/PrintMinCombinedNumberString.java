
package com.lqb.algorithm.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。 
 * @author:JackBauer
 * @date:2016年6月16日 下午8:00:03
 */
public class PrintMinCombinedNumberString {

	public static void main(String[] args) {
//		int[] numbers = {4, 32, 321};
		int[] numbers = {3334,3,3333332};
		System.out.println(PrintMinNumber(numbers));
	}
	
	public static String PrintMinNumber(int [] numbers) {
		  
		if( numbers == null || numbers.length == 0 ){
			return "";
		}
		
		ArrayList<String> stringList = new ArrayList<>();
		
		for( int number : numbers){
			stringList.add(String.valueOf(number));
		}
		
		Collections.sort(stringList, new Comparator<String>() {

			@Override
			public int compare(String string, String anotherString) {
				String _string = string + anotherString;
				String _anotherString = anotherString + string;
				
		        char v1[] = _string.toCharArray();
		        char v2[] = _anotherString.toCharArray();

		        int k = 0;
		        while (k < _string.length()) {
		            char c1 = v1[k];
		            char c2 = v2[k];
		            if (c1 != c2) {
		                return c1 - c2;
		            }
		            k++;
		        }
		        return 0;
			}
		});
		
		
		StringBuffer sb = new StringBuffer();
		for( String str : stringList ){
			sb.append(str);
		}
		
		return sb.toString();
    }

}
