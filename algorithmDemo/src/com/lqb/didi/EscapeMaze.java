package com.lqb.didi;

import java.util.ArrayList;

/**
 * ���� n * m ���Թ���1Ϊ���ߣ�0Ϊ�ϰ��� �ܹ�������p��
 * �����·��
 * @author:JackBauer
 * @date:2016��9��18�� ����5:35:42
 */
public class EscapeMaze {

	// ��һ��ȫ�ֱ�������·��,������µĸ��̵�·����ô���滻ԭ���ĸ�����·��
	static ArrayList<String> minPath = new ArrayList<>();
	
	// ��һ��ȫ�ֱ�����������ֵ
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

		// ��������
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
	 * ������С·�����Ĵ���
	 * @param maze �Թ�
	 * @param visited ��¼���ʹ���λ�ã���Ϊ���¾����ƶ������ܴ��ڷ����ظ�λ�ã������Ҫ��¼��
	 * @param n
	 * @param m
	 * @param x Ŀǰ�����ڵĺ�����
	 * @param y Ŀǰ�����ڵ�������
	 * @param p ����ֵ
	 * @param path ���ڱ���·���ļ���
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

			// ע�⼴ʹ�ɹ������յ㻹��Ҫ���ùؼ�����,����·�������л�һֱ�����յ�λ���޷�ɾȥ
			reset(visited, x, y, path);
			return flag;
		}

		// ��ΪҪ����̵�·��,���Ա���ÿ�����򶼱���һ��
		// �����if(... || ...)�����ж���ô�ҳ�һ������·������˳�,�޷������С��
		boolean down = escapeCore(maze, visited, n, m, x + 1, y, p, path);
		boolean right = escapeCore(maze, visited, n, m, x, y + 1, p, path);
		boolean up = escapeCore(maze, visited, n, m, x - 1, y, p, path);

		//���۱��α����Ƿ�ɹ���Ҫ���ùؼ�����,���ǳ���Ҫ
		reset(visited, x, y, path);
		
		return right || down || up;
	}

	/**
	 * ��ӡ
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
	 * �����ʹ���λ������Ϊ0,��·������path��ɾȥx,y
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
