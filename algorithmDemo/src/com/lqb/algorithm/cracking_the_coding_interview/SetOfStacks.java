package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;

/**
 * 请实现一种数据结构SetOfStacks，由多个栈组成，
 * 其中每个栈的大小为size，当前一个栈填满时，新建一个栈。
 * 该数据结构应支持与普通栈相同的push和pop操作。
 * 给定一个操作序列int[][2] ope，每个操作的第一个数代表操作类型，
 * 若为1，则为push操作，后一个数为应push的数字；若为2，则为pop操作，后一个数无意义。
 * 请返回一个int[][]，为完成所有操作后的SetOfStacks，顺序应为从下到上，默认初始的SetOfStacks为空。
 * 保证数据合法。

 * @Author:JackBauer
 * @Date:2016年8月3日
 */
public class SetOfStacks {

	public static void main(String[] args) {
		int[][] ope = {{1,1},{1,2},{1,3},{1,4},{1,5},{2},{2},{1,4},{1,7},{2},{2},{2},{2},{2}};
		int size = 2;
		
		SetOfStacks test = new SetOfStacks();
		ArrayList<ArrayList<Integer>> stacks = test.setOfStacks(ope, size);
		System.out.println(stacks);
	}

	public ArrayList<ArrayList<Integer>> setOfStacks(int[][] ope, int size) {
		ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
		
		for(int i = 0; i < ope.length; i++){
			switch(ope[i][0]){
			case 1:
				push(stacks, ope[i][1], size);
				break;
			case 2:
				pop(stacks);
			}
		}
		
		return stacks;
	}
	
	private Integer pop(ArrayList<ArrayList<Integer>> stacks){
		Integer result = null;
		
		if(stacks.isEmpty()){
			return result;
		}
		
		ArrayList<Integer> topStatck = stacks.get(stacks.size() - 1);
		result = topStatck.remove(topStatck.size() - 1);
		
		if(topStatck.isEmpty()){
			stacks.remove(stacks.size() - 1);
		}
		return result;
	}
	
	private void push(ArrayList<ArrayList<Integer>> stacks, int value, int size){
		ArrayList<Integer> topStatck = null;
		if(!stacks.isEmpty()){
			topStatck = stacks.get(stacks.size() - 1);
		}
		
		if(stacks.isEmpty() || topStatck.size() >= size){
			topStatck = new ArrayList<>(size);
			stacks.add(topStatck);
		}
		
		topStatck.add(value);
	}

}
