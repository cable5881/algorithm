package com.lqb.algorithm.ctci;

import java.util.Arrays;

/**
 * 有一副由NxN矩阵表示的图像，这里每个像素用一个int表示， 请编写一个算法，在不占用额外内存空间的情况下(即不使用缓存矩阵)，将图像顺时针旋转90度。
 * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵,保证N小于等于500，图像元素小于等于256。 测试样例：
 * 
 * [[1,2,3],[4,5,6],[7,8,9]],3
 * 
 * 返回：[[7,4,1],[8,5,2],[9,6,3]]
 * 
 * @Author:JackBauer
 * @Date:2016年7月25日 上午10:31:40
 */
public class TransformImage {

	public static void main(String[] args) {
//		 int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] mat = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int n = 4;
//		int n = 3;

		TransformImage test = new TransformImage();
		int[][] mat2 = test.transformImage2(mat, n);

		for (int[] is : mat2) {
			System.out.println(Arrays.toString(is));
		}
	}

	public int[][] transformImage(int[][] mat, int n) {
		if (mat == null || n <= 1) {
			return mat;
		}

		int threadhold = n / 2;
		int start = 0;
		int[] temp = new int[n];

		for (int i = start; i < threadhold; i++, start++, n--) {
			save(mat, n, start, temp);
			upToRight(mat, n, start, temp);
			rightToBottom(mat, n, start, temp);
			bottomToLeft(mat, n, start, temp);
			leftToUp(mat, n, start, temp);
		}

		return mat;
	}

	private void upToRight(int[][] mat, int n, int start, int[] temp) {
		int rowStart = start;
		int rowEnd = start + n - 1;
		int col = start + n - 1;
		int i = 0;

		while (rowStart <= rowEnd) {
			int t = temp[i];
			temp[i] = mat[rowStart][col];
			mat[rowStart][col] = t;

			rowStart++;
			i++;
		}
	}

	private void rightToBottom(int[][] mat, int n, int start, int[] temp) {
		int colStart = start + n - 2;
		int colEnd = start;
		int row = start + n - 1;
		int i = 1;

		while (colStart >= colEnd) {
			int t = mat[row][colStart];
			mat[row][colStart] = temp[i];
			temp[i] = t;

			colStart--;
			i++;
		}
	}

	private void bottomToLeft(int[][] mat, int n, int start, int[] temp) {
		int rowStart = start + n - 2;
		int rowEnd = start;
		int col = start;
		int i = 1;

		while (rowStart >= rowEnd) {
			int t = mat[rowStart][col];
			mat[rowStart][col] = temp[i];
			temp[i] = t;

			rowStart--;
			i++;
		}
	}

	private void leftToUp(int[][] mat, int n, int start, int[] temp) {
		int colStart = start + 1;
		int colEnd = start + n - 1;
		int row = start;
		int i = 1;

		while (colStart <= colEnd) {
			int t = mat[row][colStart];
			mat[row][colStart] = temp[i];
			temp[i] = t;

			colStart++;
			i++;
		}
	}

	private void save(int[][] mat, int n, int start, int[] temp) {
		int end = n + start - 1;
		int i = 0;
		int j = start;

		while (j <= end) {
			temp[i++] = mat[start][j++];
		}
	}

	public int[][] transformImage2(int[][] mat, int n) {
		if (mat == null || n < 1) {
			return mat;
		}

		int layer = n / 2;

		for (int i = 0; i < layer; i++) {
			int colEnd = n - 1 - i; // 不是i + n - 1
			int rowEnd = n - 1 - i;
			int t;

			//循环结束条件要注意
			for (int j = 0; j < rowEnd - i; j++) {
				t = mat[i][colEnd - j];
				mat[i][colEnd - j] = mat[i + j][i];
				mat[i + j][i] = mat[rowEnd][i + j];
				mat[rowEnd][i + j] = mat[rowEnd - j][colEnd];
				mat[rowEnd - j][colEnd] = t;
			}
		}

		return mat;
	}
}
