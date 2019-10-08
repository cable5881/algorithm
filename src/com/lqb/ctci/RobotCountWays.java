package com.lqb.ctci;

/**
 * 一、
 * 有一个XxY的网格，一个机器人只能走格点且只能向右或向下走，要从左上角走到右下角。
 * 请设计一个算法，计算机器人有多少种走法。 给定两个正整数int x,int y，请返回机器人的走法数目。 
 * 保证x＋y小于等于12。
 * 
 * 二、
 * 有一个XxY的网格，一个机器人只能走格点且只能向右或向下走，要从左上角走到右下角。
 * 请设计一个算法，计算机器人有多少种走法。
 * 注意这次的网格中有些障碍点是不能走的。
 * 给定一个int[][] map(C++ 中为vector >),表示网格图，若map[i][j]为1则说明该点不是障碍点，否则则为障碍。
 * 另外给定int x,int y，表示网格的大小。
 * 请返回机器人从(0,0)走到(x - 1,y - 1)的走法数，为了防止溢出，请将结果Mod 1000000007。
 * 保证x和y均小于等于50
 * 
 * 
 * 尝试改成 DP 失败 - -
 * 
 * @author:JackBauer
 * @date:2016年10月16日
 */
public class RobotCountWays {

	public static void main(String[] args) {
		RobotCountWays test = new RobotCountWays();
//		System.out.println(test.countWays(2, 2));
//		System.out.println(test.countWays(3, 3));
//		System.out.println(test.countWays(3, 4));
//		System.out.println(test.countWays(4, 4));
//		System.out.println(test.countWays(4, 5));
//		System.out.println(test.countWays(5, 5));
//		System.out.println(test.countWays(6, 6));
//		System.out.println(test.countWays(7, 7));
//		System.out.println(test.countWays(8, 8));
//		System.out.println(test.countWays(9, 9));
//		System.out.println(test.countWays(20, 20)); // 过界
		
		int[][] map = {{1,0,1},{1,0,0},{1,1,1}};
		System.out.println(test.countWaysWithBarrier(map, map.length, map[0].length));
	}
	
	public int countWays(int row, int col) {
		if(row < 1 || col < 1) {
			return 0;
		}
		
		return countWaysCore(row, col, 1, 1);
    }
	
	public int countWaysCore(int row, int col, int x, int y) {
		if(x == row && y == col) {
			return 1;
		} else if(x > row || y > col) {
			return 0;
		}
		
		return countWaysCore(row, col, x + 1, y) + countWaysCore(row, col, x, y + 1);
    }
	
	public int countWaysWithBarrier(int[][] map, int row, int col) {
		if(map == null || row < 1 || col < 1) {
			return 0;
		}
		
		return countWaysWithBarrierCore(map, row, col, 0, 0);
    }
	
	public int countWaysWithBarrierCore(int[][] map, int row, int col, int x, int y) {
		if(x >= col || y >= row) {
			return 0;
		} else if(x == col - 1 && y == row - 1) {
			return 1;
		} else if(map[y][x] == 0) {
			return 0;
		}
		
		return (countWaysWithBarrierCore(map, row, col, x + 1, y) % 1000000007 
			  + countWaysWithBarrierCore(map, row, col, x, y + 1) % 1000000007
		) % 1000000007;
    }

}
