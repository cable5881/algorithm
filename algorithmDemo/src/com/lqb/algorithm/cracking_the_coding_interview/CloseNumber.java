package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.Arrays;

import com.lqb.algorithm.offer.NumberOf1;

/**
 * 
 * ��һ�������������ҳ�������Ʊ�ʾ��1�ĸ�����ͬ���Ҵ�С��ӽ�������������(һ���Դ�һ����С) ����������int
 * x���뷵��һ��vector�������������������С����ǰ������֤�𰸴��ڡ�
 * 
 * ���������� 2 ���أ�[1,4]
 * 
 * @Author:JackBauer
 * @Date:2016��8��11��
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
		// 1.��ת���ұߵ�0Ϊ1�������ұ߱���Ҫ��1��������β0.
		int c1 = 0;// ����1�ĸ���
		int c0 = 0;// ����0�ĸ���
		int t = n;

		// �ȼ���0�ĸ���
		// ��Ҫ�ж�t = 0 �����, ��Ϊ t & 1 ��һ��Ϊ0��
		while ((t & 1) == 0 && t != 0) {
			c0++;
			t >>= 1;
		}

		// �ټ���1�ĸ���
		// ��ʱ���tΪ0��ôt & 1Ҳ�������1,Ҳ�Ͳ��ý���t != 0 ���ж���
		while ((t & 1) == 1 && t != 0) {
			c1++;
			t >>= 1;
		}

		if (c0 + c1 > 31 || c0 + c1 == 0) {
			return -1;
		}

		int p = c0 + c1;// ��Ҫ��ת��λ��
		int mask = 1 << p;
		n = n | mask;

		// 2.����λ�ұߵ�������0������Ӧ�ã�
		mask -= 1;
		mask = ~mask;
		n = n & mask;

		// 3.����λ�ұ߲���C1 - 1 �� 1��������߿�ʼ����
		int fix = 1 << (c1 - 1);
		fix -= 1;
		n |= fix;

		return n;
	}

	public static int getPre(int n) {
		// 1.��ת���ұߵ�1Ϊ0�������ұ߱���Ҫ��0��������β1
		int c1 = 0;// ����1�ĸ���
		int c0 = 0;// ����0�ĸ���
		int t = n;

		// �ȼ���1�ĸ���
		while ((t & 1) == 1) {
			c1++;
			t >>= 1;
		}

		if (t == 0) {
			return -1;
		}

		// �ټ���0�ĸ���
		while ((t & 1) == 0 && t != 0) {
			c0++;
			t >>= 1;
		}

		// 2.����λ�ұߵ�������0������Ӧ�ã�
		int p = c0 + c1;// ��Ҫ��ת��λ��
		int mask = ~0 << (p + 1);
		n = n & mask;

		// 3.����λ�ұ߲���C1 + 1 �� 1(�����Ÿ�λ)
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
