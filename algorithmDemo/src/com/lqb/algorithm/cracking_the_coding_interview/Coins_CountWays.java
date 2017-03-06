package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * 
 * 有数量不限的硬币，币值为25分、10分、5分和1分，请编写代码计算n分有几种表示法。
 * 
 * 给定一个int n，请返回n分有几种表示法。保证n小于等于100000，为了防止溢出，请将答案Mod 1000000007。
 * 
 * 测试样例：
 * 6
 * 返回：2
 * 
 * @author:JackBauer
 * @date:2016年9月23日 下午4:06:28
 */
public class Coins_CountWays {

	public static void main(String[] args) {
		Coins_CountWays test = new Coins_CountWays();
		System.out.println(test.countWays(6));
		System.out.println(test.countWays(10));
		System.out.println(test.countWays(100));
//		System.out.println(test.countWays(1000));
//		System.out.println(test.countWays(10000));
		
		System.out.println(test.countWays2(6));
		System.out.println(test.countWays2(10));
		System.out.println(test.countWays2(100));
//		System.out.println(test.countWays2(1000));
//		System.out.println(test.countWays2(10000));
//		System.out.println(test.countWays2(100000));
	}
	
	int count = 0;
	
	//此方法不能连续两次调用，因为count是全局变量，会累加
	public int countWays(int n) {
		if(n < 1) {
			return -1;
		}
		
		countWaysCore(n, 25);
		
		return count;
    }
	
	
	public void countWaysCore(int cVal, int denom) {
		if(cVal < 0) {
			return;
		} else if(cVal == 0) {
			count = (count + 1) % 1000000007;
			return;
		} 
		
		if(denom >= 25) {
			countWaysCore(cVal - 25, 25);
		} 
		
		if(denom >= 10) {
			countWaysCore(cVal - 10, 10);
		} 
		
		if(denom >= 5) {
			countWaysCore(cVal - 5, 5);
		} 
		
		if(denom >= 1) {
			countWaysCore(cVal - 1, 1);
		}
		
	}
	
	int[] arr = new int[100000 + 1];
	
	/**
	 * 4 490 5733745 982645680 783786011
	 */
	public int countWays2(int n) {
		if(n < 1) {
			return 0;
		}
		
		return countWays2Core(n, 25);
	}
	
	private int countWays2Core(int n, int denom) {
		int next_denom = 0;
		
//		if(arr[n] > 0) {
//			return arr[n];
//		}
		
		switch (denom) {
		case 25:
			next_denom = 10;
			break;
		case 10:
			next_denom = 5;
			break;
		case 5:
			next_denom = 1;
			break;
		default:
			return 1;
		}
		
		int ways = 0;
		for(int i = 0; i * denom <= n; i++) {
			ways = (ways + countWays2Core(n - i * denom, next_denom)) % 1000000007;
		}
		
//		arr[n] = ways;
		
		return ways;
	}
}
