package com.lqb.algorithm.offer;

import java.util.Collection;
import java.util.HashMap;

/**
 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符的位置。
 * 若为空串，返回-1。位置索引从0开始 
 * @author:JackBauer
 * @date:2016年6月16日 下午7:52:05
 */
public class FirstNotRepeatingChar {

	public static void main(String[] args) {
		String str = "abcdaaabbbbbddddggppeeekak";
		int index = firstNotRepeatingChar(str);
		System.out.println(str.charAt(index));
	}

	
	public static int firstNotRepeatingChar(String str) {
		
		if( str == null ){
			return -1;
		}
		
		HashMap<Character, Integer> repeatingTimes = new HashMap<>();
		
		for( int i = 0; i < str.length(); i++ ){
			char ch = str.charAt(i);
			Integer times = repeatingTimes.get(ch);
			if( times == null ){
				repeatingTimes.put(ch, 1);
			}else{
				repeatingTimes.put(ch, ++times);
			}
		}
		
		if( isAllRepeatingTimesMoreThanOne(repeatingTimes.values()) ){
			return -1;
		}
			
		for(int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if(repeatingTimes.get(ch) == 1){
				return i;
			}
		}
		
        return 0;
    }
	
	private static boolean isAllRepeatingTimesMoreThanOne(Collection<Integer> repeatingTimes){
		for( Integer times : repeatingTimes ){
			if( times == 1 ){
				return false;
			}
		}
		
		return true;
	}
}
