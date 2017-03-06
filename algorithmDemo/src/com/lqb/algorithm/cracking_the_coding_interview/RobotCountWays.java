package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * һ��
 * ��һ��XxY������һ��������ֻ���߸����ֻ�����һ������ߣ�Ҫ�����Ͻ��ߵ����½ǡ�
 * �����һ���㷨������������ж������߷��� ��������������int x,int y���뷵�ػ����˵��߷���Ŀ�� 
 * ��֤x��yС�ڵ���12��
 * 
 * ����
 * ��һ��XxY������һ��������ֻ���߸����ֻ�����һ������ߣ�Ҫ�����Ͻ��ߵ����½ǡ�
 * �����һ���㷨������������ж������߷���
 * ע����ε���������Щ�ϰ����ǲ����ߵġ�
 * ����һ��int[][] map(C++ ��Ϊvector >),��ʾ����ͼ����map[i][j]Ϊ1��˵���õ㲻���ϰ��㣬������Ϊ�ϰ���
 * �������int x,int y����ʾ����Ĵ�С��
 * �뷵�ػ����˴�(0,0)�ߵ�(x - 1,y - 1)���߷�����Ϊ�˷�ֹ������뽫���Mod 1000000007��
 * ��֤x��y��С�ڵ���50
 * 
 * 
 * ���Ըĳ� DP ʧ�� - -
 * 
 * @author:JackBauer
 * @date:2016��10��16��
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
//		System.out.println(test.countWays(20, 20)); // ����
		
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
