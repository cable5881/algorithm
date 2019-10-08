package com.lqb.ctci;

/**
 * 有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶、3阶。 请实现一个方法，计算小孩有多少种上楼的方式。 为了防止溢出，请将结果Mod
 * 1000000007 给定一个正整数int n，请返回一个数，代表上楼的方式数。保证n小于等于100000。
 * 
 * @Author:JackBauer
 * @Date:2016年8月12日
 */
public class GoUpstairs {

	public static void main(String[] args) {
		System.out.println(countWays(3));
		System.out.println(countWays(10));
		System.out.println(countWays(20));
		System.out.println(countWays(30));
		System.out.println(countWays(40));
		System.out.println(countWays(50));
		System.out.println(countWays(100));
		System.out.println(countWays(1000));
		System.out.println(countWaysCore2(10000));
		System.out.println(countWaysCore2(100000));

	}

	static int[] arr = new int[100000];

	public static int countWays(int n) {
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;

		return countWaysCore(n);
	}

	public static int countWaysCore(int n) {
		if (n <= 0) {
			return arr[0];
		} else if (n <= 3) {
			return arr[n];
		} else if (arr[n] > 0) {
			return arr[n];
		}

		arr[n] = (((countWays(n - 1) % 1000000007 + countWays(n - 2) % 1000000007) % 1000000007
				+ countWays(n - 3) % 1000000007)) % 1000000007;

		return arr[n];
	}

	public static int countWaysCore2(int n) {
		int[] pre = { 1, 2, 4 };
		
		if (n <= 0)
			return 0;
		else if (n <= 3)
			return pre[n - 1];
		else {
			for (int i = 4; i <= n; i++) {
				int tmp = ((pre[0] + pre[1]) % 1000000007 + pre[2]) % 1000000007;
				pre[0] = pre[1];
				pre[1] = pre[2];
				pre[2] = tmp;
			}
		}
		return pre[2];
	}

}
