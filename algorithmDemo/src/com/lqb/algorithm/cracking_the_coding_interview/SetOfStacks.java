package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;

/**
 * ��ʵ��һ�����ݽṹSetOfStacks���ɶ��ջ��ɣ�
 * ����ÿ��ջ�Ĵ�СΪsize����ǰһ��ջ����ʱ���½�һ��ջ��
 * �����ݽṹӦ֧������ͨջ��ͬ��push��pop������
 * ����һ����������int[][2] ope��ÿ�������ĵ�һ��������������ͣ�
 * ��Ϊ1����Ϊpush��������һ����ΪӦpush�����֣���Ϊ2����Ϊpop��������һ���������塣
 * �뷵��һ��int[][]��Ϊ������в������SetOfStacks��˳��ӦΪ���µ��ϣ�Ĭ�ϳ�ʼ��SetOfStacksΪ�ա�
 * ��֤���ݺϷ���

 * @Author:JackBauer
 * @Date:2016��8��3��
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
