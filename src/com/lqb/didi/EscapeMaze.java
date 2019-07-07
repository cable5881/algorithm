package com.lqb.didi;

import java.util.ArrayList;

/**
 * 逃脱 n * m 的迷宫，1为可走，0为障碍物 总共可以走p步
 * 求最短路径
 * @author:JackBauer
 * @date:2016年9月18日 下午5:35:42
 */
public class EscapeMaze {

	// 用一个全局变量保存路径,如果有新的更短的路径那么就替换原来的更长的路径
	static ArrayList<String> minPath = new ArrayList<>();
	
	// 用一个全局变量保存生命值
	static int maxP = 0;

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// while (in.hasNextInt()) {
		// int n = in.nextInt();
		// int m = in.nextInt();
		// int p = in.nextInt();
		// int[][] maze = new int[n][m];
		//
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < m; j++) {
		// maze[i][j] = in.nextInt();
		// }
		// }
		//
		// escape(maze, n, m, p);
		// }
		//
		// in.close();

		// 测试用例
		int n = 4;
		int m = 4;
		int p = 10;
		int[][] maze = { { 1, 0, 1, 1 }, { 1, 1, 0, 1 }, { 0, 1, 0, 1 }, { 1, 1, 1, 1 } };
		escape(maze, n, m, p + 1);
	}

	private static void escape(int[][] maze, int n, int m, int p) {
		ArrayList<String> path = new ArrayList<String>();
		int[][] visited = new int[n][m];
		if (escapeCore(maze, visited, n, m, 0, 0, p, path)) {
			print(minPath);
		}
	}

	/**
	 * 查找最小路径核心代码
	 * @param maze 迷宫
	 * @param visited 记录访问过的位置（因为上下均可移动，可能存在访问重复位置，因此需要记录）
	 * @param n
	 * @param m
	 * @param x 目前点所在的横坐标
	 * @param y 目前点所在的纵坐标
	 * @param p 生命值
	 * @param path 用于保存路径的集合
	 * @return
	 */
	private static boolean escapeCore(int[][] maze, int[][] visited, int n, int m, int x, int y, int p,
			ArrayList<String> path) {
		if (p < 0) {
			return false;
		}

		if (x > n - 1 || x < 0) {
			return false;
		}

		if (y > m - 1 || y < 0) {
			return false;
		}

		if (maze[x][y] == 0) {
			return false;
		}

		if (visited[x][y] == 1) {
			return false;
		}

		p--;
		visited[x][y] = 1;
		path.add("[" + x + "," + y + "]");

		if (x == 0 && y == m - 1) {
			boolean flag = true;
			if (p > maxP) {
				maxP = p;
				minPath = new ArrayList<>(path);
			} else {
				flag = false;
			}

			// 注意即使成功到达终点还是要重置关键变量,否则路径集合中会一直保存终点位置无法删去
			reset(visited, x, y, path);
			return flag;
		}

		// 因为要求最短的路径,所以必须每个方向都遍历一次
		// 如果用if(... || ...)这样判断那么找出一条可行路径后就退出,无法求出最小的
		boolean down = escapeCore(maze, visited, n, m, x + 1, y, p, path);
		boolean right = escapeCore(maze, visited, n, m, x, y + 1, p, path);
		boolean up = escapeCore(maze, visited, n, m, x - 1, y, p, path);

		//无论本次遍历是否成功都要重置关键变量,这点非常重要
		reset(visited, x, y, path);
		
		return right || down || up;
	}

	/**
	 * 打印
	 * @param minPath
	 */
	private static void print(ArrayList<String> minPath) {
		System.out.print(minPath.get(0));
		for (int i = 1; i < minPath.size(); i++) {
			System.out.print("," + minPath.get(i));
		}
		System.out.println();
	}

	/**
	 * 将访问过的位置重置为0,将路径集合path中删去x,y
	 * @param visited
	 * @param x
	 * @param y
	 * @param path
	 */
	private static void reset(int[][] visited, int x, int y, ArrayList<String> path) {
		visited[x][y] = 0;
		path.remove("[" + x + "," + y + "]");
	}

}
