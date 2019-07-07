package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.Arrays;

import com.lqb.algorithm.offer.NumberOf1;

/**
 * 
 * 有一个正整数，请找出其二进制表示中1的个数相同、且大小最接近的那两个数。(一个略大，一个略小) 给定正整数int
 * x，请返回一个vector，代表所求的两个数（小的在前）。保证答案存在。
 * 
 * 测试样例： 2 返回：[1,4]
 * 
 * @Author:JackBauer
 * @Date:2016年8月11日
 */
public class CloseNumber {

	public static void main(String[] args) throws Exception {
		// two solutions
		System.out.println(Arrays.toString(getCloseNumber(2)));
		System.out.println(Arrays.toString(getCloseNumber2(76351)));
	}

	// bit operation
	public static int[] getCloseNumber2(int n) {
		int[] closeNumbers = new int[2];
		closeNumbers[0] = getPre(n);//76415 ---- > 76284
		closeNumbers[1] = getNext(n);
		return closeNumbers;
	}

	public static int getNext(int n) {
		// 1.翻转最右边的0为1，且它右边必须要有1，即非拖尾0.
		int c1 = 0;// 最右1的个数
		int c0 = 0;// 最右0的个数
		int t = n;

		// 先计算0的个数
		// 需要判断t = 0 的情况, 因为 t & 1 是一定为0的
		while ((t & 1) == 0 && t != 0) {
			c0++;
			t >>= 1;
		}

		// 再计算1的个数
		// 此时如果t为0那么t & 1也不会等于1,也就不用进行t != 0 的判断了
		while ((t & 1) == 1 && t != 0) {
			c1++;
			t >>= 1;
		}

		if (c0 + c1 > 31 || c0 + c1 == 0) {
			return -1;
		}

		int p = c0 + c1;// 需要翻转的位置
		int mask = 1 << p;
		n = n | mask;

		// 2.将该位右边的所有清0（掩码应用）
		mask -= 1;
		mask = ~mask;
		n = n & mask;

		// 3.将该位右边补上C1 - 1 个 1（靠右最边开始补）
		int fix = 1 << (c1 - 1);
		fix -= 1;
		n |= fix;

		return n;
	}

	public static int getPre(int n) {
		// 1.翻转最右边的1为0，且它右边必须要有0，即非拖尾1
		int c1 = 0;// 最右1的个数
		int c0 = 0;// 最右0的个数
		int t = n;

		// 先计算1的个数
		while ((t & 1) == 1) {
			c1++;
			t >>= 1;
		}

		if (t == 0) {
			return -1;
		}

		// 再计算0的个数
		while ((t & 1) == 0 && t != 0) {
			c0++;
			t >>= 1;
		}

		// 2.将该位右边的所有清0（掩码应用）
		int p = c0 + c1;// 需要翻转的位置
		int mask = ~0 << (p + 1);
		n = n & mask;

		// 3.将该位右边补上C1 + 1 个 1(紧挨着该位)
		int fix = 1 << (c1 + 1);
		fix -= 1;
		n |= fix << (c0 - 1);

		return n;
	}

	// brutal force
	public static int[] getCloseNumber(int x) {
		int numOf1 = NumberOf1.numberOf1(x);
		int big = x + 1;
		int small = x - 1;

		while (true) {
			if (NumberOf1.numberOf1(big) == numOf1) {
				break;
			}
			big++;
		}

		while (true) {
			if (NumberOf1.numberOf1(small) == numOf1) {
				break;
			}
			small--;
		}

		return new int[] { small, big };
	}
}
