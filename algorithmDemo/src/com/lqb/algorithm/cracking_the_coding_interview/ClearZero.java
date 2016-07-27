package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ���дһ���㷨����MxN������ĳ��Ԫ��Ϊ0���������ڵ����������㡣
 * ����һ��MxN��int[][]����(C++��Ϊvector>)mat�;���Ľ���n��
 * �뷵����ɲ������int[][]����(C++��Ϊvector>)����֤nС�ڵ���300�������е�Ԫ��Ϊint��Χ�ڡ�
 * ����������
 * 
 * [[1,2,3],[0,1,2],[0,0,1]]
 * 
 * ���أ�[[0,0,3],[0,0,0],[0,0,0]]
 * @Author:JackBauer
 * @Date:2016��7��25�� ����4:44:05
 */
public class ClearZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int[][] clearZero(int[][] mat, int n) {
		if(mat == null || n < 1){
			return mat;
		}
		
		boolean[] row = new boolean[n];
		boolean[] col = new boolean[n];
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(mat[i][j] == 0){
					row[i] = true;
					col[j] = true;
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(row[i] || row[j]){
					mat[i][j] = 0;
				}
			}
		}
		
		return mat;
	}
}
