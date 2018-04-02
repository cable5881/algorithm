package com.lqb.ctrip;

/**
 * 假如一个数组中存储了一个股票，在一天交易窗口内各时间点的股票价格（正整数）。只允许一次买入和一次卖出，请提供一个算法，
 * 计算出通过卖出和买入可以得到的最大利润
 * 
 * @Date:2016年9月17日 下午8:16:49
 */
public class Stock {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// int[] a = null;
		// String[] parts = in.nextLine().split(",");
		// a = convertToint(parts);
		// System.out.println(getMaxProfit(a));
		int[] a = { 3, 4, 5, 9, 1, 2, 6 };
		int[] b = { 9, 8, 7, 6, 5, 4, 3 };
		System.out.println(getMaxProfit2(b));
	}

	public static int getMaxProfit(int[] a) {
		if (a == null || a.length < 1) {
			return 0;
		}

		int min = a[0];
		int max = a[0];

		for (int i : a) {
			if (i < min) {
				min = i;
			} else if (i > max) {
				max = i;
			}
		}

		return max - min;
	}

	public static int getMaxProfit2(int[] a) {
		// 不用TreeMap是因为重复价格会被去掉
		int[][] b = new int[2][a.length];
		setIntoSorted2DArray(b, a);
		
		int max = 0;
		
		for(int i = 0; i < a.length - 1; i++) {
			
			int low = i;
			int high = a.length - 1;
			
			while(low < high) {
				int t = b[0][high] - b[0][low];
				if(b[1][low] < b[1][high] && t > max) {
					max = t;
				}
				
				high--;
			}
		}
		
		return max;
	}

	private static void setIntoSorted2DArray(int[][] b, int[] a) {
		for(int i = 0; i < a.length; i++){
			b[0][i] = a[i];
			b[1][i] = i;
		}
		
		quickSort(b);
	}
	
	private static void qSort(int[][] a, int low, int high) {
		if (high <= low) {
			return;
		}

		int t1 = a[0][low];
		int t2 = a[1][low];
		int l = low;
		int r = high;

		while (l < r) {

			while (l < r && t1 < a[0][r]) {
				r--;
			}
			if (l < r) {
				a[0][l] = a[0][r];
				a[1][l++] = a[1][r];
			}

			while (l < r && t1 >= a[0][l]) {
				l++;
			}
			if (l < r) {
				a[0][r] = a[0][l];
				a[1][r--] = a[1][l];
			}

		}

		a[0][r] = t1;
		a[1][r] = t2;
		
		qSort(a, low, r - 1);
		qSort(a, r + 1, high);
	}

	private static void quickSort(int[][] b) {
		qSort(b, 0, b[0].length - 1);
	}

	private static int[] convertToint(String[] parts) {
		int[] a = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			a[i] = Integer.valueOf(parts[i]);
		}

		return a;
	}

}
