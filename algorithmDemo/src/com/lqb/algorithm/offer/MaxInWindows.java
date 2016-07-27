package com.lqb.algorithm.offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Description:�����������ֵ
 * @Author:JackBauer
 * @Date:2016��7��13�� ����3:41:43
 */
public class MaxInWindows {

	public static void main(String[] args) {
//		int[] num = {2,3,4,2,6,2,5,1};
		int[] num = {10,14,12,11};
		int windowSize = 1;
//		int windowSize = 3;
		MaxInWindows test = new MaxInWindows();
		ArrayList<Integer> maxs = test.maxInWindows(num, windowSize);
		System.out.println(maxs);
	}
	
	
	public ArrayList<Integer> maxInWindows(int[] num, int windowSize){
		ArrayList<Integer> maxs = new ArrayList<>();
		
		if(num == null || num.length < windowSize || windowSize < 1){
			return maxs;
		}
		
		LinkedList<Integer> maxQ = new LinkedList<>();
		int windowStart = 0;
		
		maxQ.add(windowStart++);
		
		while(windowStart < windowSize){
			while(!maxQ.isEmpty() && num[windowStart] > num[maxQ.peekLast()]){
				maxQ.removeLast();
			}
			maxQ.add(windowStart++);
		}
		
		while(windowStart < num.length){
			maxs.add(num[maxQ.peek()]);//ע��һ��ʼ��Ҫ�����
			
			while(!maxQ.isEmpty() && num[windowStart] > num[maxQ.peekLast()]){
				maxQ.removeLast();
			}
			
			if(!maxQ.isEmpty() && windowStart - maxQ.peek() >= windowSize){
				maxQ.removeFirst();
			}
			
			maxQ.add(windowStart);
			windowStart++;
		}
		
		maxs.add(num[maxQ.peek()]);//ע�����Ҫ�����һ��
		
		return maxs;
	}
}
