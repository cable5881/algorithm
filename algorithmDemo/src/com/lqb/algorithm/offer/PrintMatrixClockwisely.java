package com.lqb.algorithm.offer;

import java.util.ArrayList;

import com.lqb.algorithm.offer.domain.Point;

/**
 *  输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 *  例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
 *  则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * @Author:JackBauer
 * @Date:2016年7月16日 下午12:40:45
 */
public class PrintMatrixClockwisely {

	public static void main(String[] args) {
//		int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//		int[][] matrix = new int[][]{{1,2,3}};
//		int[][] matrix = new int[][]{{1},{2},{3}};
//		int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//		int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
//		int[][] matrix = new int[][]{{1}};
		int[][] matrix = new int[][]{};
		ArrayList<Integer> list = myPrintMatrix(matrix);
		System.out.println(list);
		
//		for( int i = 0; i < 3; ++i ){
//			System.out.println(i);
//		}
//		System.out.println((int)Math.ceil(5 / 2.0));
	}

	public static ArrayList<Integer> printMatrix(int[][] matrix) {

		ArrayList<Integer> arr = new ArrayList<>();

		if (matrix == null || matrix[0] == null) {
			return arr;
		}

		int start = 0;
		int length = matrix.length;

		while (start < length) {
			visitCircle(matrix, matrix.length, matrix[0].length, start, arr);
			start++;
		}
		return arr;
	}

	public static void visitCircle(int[][] matrix, int row, int col, int start, ArrayList<Integer> arr) {
		
		int endX = col - start - 1;
		int endY = row - start - 1;
		
		for( int i = start; i <= endX; i++ ){
			arr.add(matrix[start][i]);
		}
		
		for( int i = start; i <= endY; ++i ){
			arr.add(matrix[i][endX]);
		}
		
		for( int i = endX; i > start; --i ){
			arr.add(matrix[endY][i]);
		}
		
		for( int i = endY; i > start; --i ){
			arr.add(matrix[i][start]);
		}
		
	}

	
	public static ArrayList<Integer> myPrintMatrix(int[][] matrix) {
		ArrayList<Integer> arr = new ArrayList<>();

		if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1) {
			return arr;
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		int reachableRow = (int) Math.ceil(rows / 2.0);
		int reachableCol = (int) Math.ceil(cols / 2.0);
		int reachable = reachableCol > reachableRow ? reachableRow : reachableCol;
		
		for(int i = 0; i < reachable; i++){
			printMatrixCore(matrix, new Point(i,i), arr, rows--, cols--);
		}
		
		return arr;
	}

	private static void printMatrixCore(int[][] matrix, Point start, ArrayList<Integer> arr, int rows, int cols) {
		int y = 0;
		int x = 0;
		
		for(x = start.col, y = start.row; x < cols; x++){
			arr.add(matrix[y][x]);
		}
		
		for(x -= 1, y += 1; y < rows; y++){
			arr.add(matrix[y][x]);
		}
		
		for(x -= 1, y-= 1; x >= start.col && y > start.row; x--){
			arr.add(matrix[y][x]);
		}
		
		for(x += 1, y-= 1; y > start.row && x < start.col + cols - 1; y--){
			arr.add(matrix[y][x]);
		}
		
	}
}
