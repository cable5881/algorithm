package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.Point;

/**
 * @Description:矩阵中的路径
 * @Author:JackBauer
 * @Date:2016年7月13日 下午5:26:41
 */
public class MatrixPath {

	public static void main(String[] args) {
		char[][] matrix = {
				{'a','b','c','e'},
				{'s','c','c','s'},
				{'a','d','e','e'}
		};
		
		MatrixPath test = new MatrixPath();
//		String testStr = null;
//		String testStr = "";
//		String testStr = "abcb";
//		String testStr = "bccedcb";
//		String testStr = "bcced";
		
		//这个测试用例需要回退,即b->c(第一行第三个),回退,b->c(第二行第二个)->d->e->c->c
		//回退的同时需要把访问的第一行第三个c的位置设置为未访问（false）
		String testStr = "bcdecc";
		boolean flag = test.hasMatrixPath(matrix, 3, 4, testStr.toCharArray());
		System.out.println(flag);
	}
	
	public boolean hasMatrixPath(char[][] matrix, int rows, int cols, char[] pathStr){
		if(matrix == null || matrix.length <=0 || pathStr == null || pathStr.length == 0){
			return false;
		}
		
		boolean [][] isVisited = new boolean[rows][cols];
		
		resetPath(isVisited);
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				Point point = new Point(i, j);
				//不需要对第一个做特殊处理
//				if(tryVisit(matrix, isVisited, point, pathStr[0])){
//					boolean flag = hasMatrixPathCore(matrix, pathStr, 1, point, isVisited);
//					if(flag){
//						return true;
//					}
//					
//					tryResetPoint(isVisited, point, false);
//				}
				
				boolean flag = hasMatrixPathCore(matrix, pathStr, 0, point, isVisited);
				if(flag){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @Description: 递归核心
	 * @Author:JackBauer
	 * @Date:2016年7月14日上午10:06:29
	 */
	private boolean hasMatrixPathCore(char[][] matrix, char[] pathStr, int pathIndex, Point point, boolean[][] isVisited){
		
		//注意递归结束条件7
		if(pathIndex >= pathStr.length){
			return true;
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		boolean right = false;
		boolean down = false;
		boolean left = false;
		boolean up = false;
		
		Point nowPoint = null;
		
		if(point.col < cols - 1){
			nowPoint = new Point(point.row, point.col + 1);
			if(tryVisit(matrix, isVisited, nowPoint, pathStr[pathIndex])){
				right = hasMatrixPathCore(matrix, pathStr, pathIndex + 1, nowPoint, isVisited);
				tryResetPoint(isVisited, nowPoint, right);//注意如果此次访问路径不成功则应该清除
			}
		}
		
		if(point.row < rows - 1){
			nowPoint = new Point(point.row + 1, point.col);
			if(tryVisit(matrix, isVisited, nowPoint, pathStr[pathIndex])){
				down = hasMatrixPathCore(matrix, pathStr, pathIndex + 1, nowPoint, isVisited);
				tryResetPoint(isVisited, nowPoint, down);
			}
		}
		
		if(point.col > 0){
			nowPoint = new Point(point.row, point.col - 1);
			if(tryVisit(matrix, isVisited, nowPoint, pathStr[pathIndex])){
				left = hasMatrixPathCore(matrix, pathStr, pathIndex + 1, nowPoint, isVisited);
				tryResetPoint(isVisited, nowPoint, left);
			}
		}
		
		if(point.row > 0){
			nowPoint = new Point(point.row - 1, point.col);
			if(tryVisit(matrix, isVisited, nowPoint, pathStr[pathIndex])){
				up = hasMatrixPathCore(matrix, pathStr, pathIndex + 1, nowPoint, isVisited);
				tryResetPoint(isVisited, nowPoint, up);
			}
		}
		
		return right || down || left || up;
	}
	
	/**
	 * @Description: 重置isVisited数组某个位置为false
	 * @Author:JackBauer
	 * @Date:2016年7月14日上午10:06:45
	 */
	private void tryResetPoint(boolean[][] isVisited, Point nowPoint, boolean flag) {
		if(flag == false){
			isVisited[nowPoint.row][nowPoint.col] = false;
		}
	}

	/**
	 * @Description: 尝试访问某个点
	 * @Author:JackBauer
	 * @Date:2016年7月14日上午10:07:14
	 */
	private boolean tryVisit(char[][] matrix, boolean[][] isVisited, Point point, char c){
		if(matrix[point.row][point.col] == c && isVisited[point.row][point.col] == false){
			isVisited[point.row][point.col] = true;
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 初始化isVisited数组
	 * @Author:JackBauer
	 * @Date:2016年7月14日上午10:07:32
	 */
	public void resetPath(boolean[][] isVisited){
		for(int i = 0; i < isVisited.length; i++){
			for(int j = 0; j < isVisited[0].length; j++){
				isVisited[i][j] = false;
			}
		}
	} 
	
}
