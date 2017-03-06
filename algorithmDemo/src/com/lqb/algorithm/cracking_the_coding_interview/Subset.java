package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;
import java.util.Collections;

public class Subset {
	public static void main(String[] args) {
		int[] A = {123,456,789};
		Subset test = new Subset();
		System.out.println(test.getSubsets2(A, 3));
	}
	
	// 排列组合法
	public ArrayList<ArrayList<Integer>> getSubsets2(int[] A, int n) {
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
		if(A == null) {
			return subsets;
		}
		
		ArrayList<Integer> AA = new ArrayList<Integer>(A.length);
		for(int i = 0; i < A.length; i++) {
			AA.add(A[i]);
		}
		
		getSubsets2Core(AA, 0, subsets);
		return subsets;
	}
	
	private void getSubsets2Core(ArrayList<Integer> A, int cur, ArrayList<ArrayList<Integer>> subsets) {
		if(cur >= A.size()) {
			return;
		}
		
		for(int i = cur; i < A.size(); i++) {
			swap(A, 0, cur);
			
			ArrayList<Integer> subset = new ArrayList<>(A);
			subsets.add(subset);
			
			swap(A, 0, cur);
			
			getSubsets2Core(A, cur + 1, subsets);
		}
	}
	
	private void swap(ArrayList<Integer> A, int i, int j) {
		int t = A.get(i);
		A.set(i, A.get(j));
		A.set(j, t);
	}
	
	public ArrayList<ArrayList<Integer>> getSubsets(int[] A, int n) {
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>());
		
		if(A == null) {
			return subsets;
		}
		
		ArrayList<ArrayList<Integer>> temp;
		
		for(int i = 0; i < n; i++) {
			temp = new ArrayList<>(subsets);
			
			for(ArrayList<Integer> aList : subsets) {
				ArrayList<Integer> newList = new ArrayList<>(aList);
				aList.add(A[i]);
				Collections.copy(newList, aList);
				temp.add(newList);
			}
			
			subsets = temp;
		}
		
		return subsets;
	}
}
