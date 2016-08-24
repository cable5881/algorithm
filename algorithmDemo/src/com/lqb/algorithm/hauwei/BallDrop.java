package com.lqb.algorithm.hauwei;

import java.util.Scanner;

/**
 * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 * 
 * @Author:JackBauer
 * @Date:2016年8月24日
 */
public class BallDrop {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int high = in.nextInt();
			System.out.println(getJourneyByMath(high));
			System.out.println(getTenthHighByMath(high));
		}
		in.close();
	}

	private static int DROP_TIMES = 5;

	/**
	 * 统计出第5次落地时，共经过多少米?
	 * 
	 * @param high
	 *            球的起始高度
	 * @return 英文字母的个数
	 */
	public static double getJourney(int high) {
		double s = high;
		double h = high;

		for (int i = 1; i < DROP_TIMES; i++) {
			s += h;
			h /= 2;
		}

		return s;
	}

	// 数学公式法
	public static double getJourneyByMath(int high) {
		return high + high * (2 - Math.pow(0.5, DROP_TIMES - 2));
	}

	/**
	 * 统计出第5次反弹多高?
	 * 
	 * @param high
	 *            球的起始高度
	 * @return 空格的个数
	 */
	public static double getTenthHigh(int high) {
		double h = high;

		for (int i = 0; i < DROP_TIMES; i++) {
			h /= 2;
		}

		return h;
	}

	// 数学公式法
	public static double getTenthHighByMath(int high) {
		return high * Math.pow(0.5, DROP_TIMES);
	}

}
