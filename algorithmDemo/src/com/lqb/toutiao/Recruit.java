package com.lqb.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ͷ����2017У�п�ʼ�ˣ�Ϊ�����У�У�������֯��һ����ģ���ĳ����Ŷӡ� ÿ�������˶�����һЩ��Ȥ����Ŀ�����������������Щ��Ŀ��ϳ����ɳ����Գ�����
 * ��ѡ��֮ǰ�����Ƕ���Ŀ������ä�󣬲�������ÿ������Ѷ�ϵ���� һ�����԰���3����������Ŀ���������ǵ��Ѷȴ�С����ֱ�Ϊa, b, c��
 * ����ϣ����3�������������������� a <= b <= c b - a <= 10 c - b <= 10 ���г�����һ������n����������Ŀ��
 * �������������n����ֲ������ɳ������У�1����ೡ��ÿ���ⶼ����ʹ����ֻ����һ�Σ���Ȼ�������������������ƣ�
 * ������һЩ����û���չ�3���⣬��˳����˾���Ҫ���һЩ�ʵ��Ѷȵ���Ŀ����ÿ�����Զ��ﵽҪ��
 * Ȼ�����ǳ����Ѿ����ú����ˣ����ܼ�����������ٻ���Ҫ�ٳ���������
 * 
 * ����ĵ�һ�а���һ������n����ʾĿǰ�Ѿ����õ���Ŀ������ �ڶ��и���ÿ����Ŀ���Ѷ�ϵ�� d1, d2, ��, dn��
 * ���ֻ����һ�У�������Ĵ𰸡�
 * 
 * �������� 
 * 4 
 * 20 35 23 40 
 * 
 * ������� 
 * 2
 * 
 * Hint 
 * �������� 
 * �������У�һ�ֿ��еķ��������2���Ѷȷֱ�Ϊ20��50����Ŀ������������ϳ��������ԣ�(20, 20, 23)��(35, 40, 50)��
 * 
 * ���ݷ�Χ ����30%�����ݣ�1 <= n, di <= 5�� 
 * ����100%�����ݣ�1 <= n <= 10^5, 1 <= di <= 100��
 * 
 * @author:JackBauer
 * @date:2016��9��21�� ����7:49:32
 */
public class Recruit {
	
	private static final int delta = 10;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			
			System.out.println(fun(a));
		}
		
		in.close();
	}
	
	public static int fun(int[] a) {
		if(a == null || a.length < 1) {
			return -1;
		} else if(a.length < 3) {
			return 3 - a.length;
		}
		
		Arrays.sort(a);
		
		int sum = 0;
		int tmp = 0;
		int i = 0;
		while(i < a.length) {
			tmp = count(a, i, i+1, i+2);
			sum += tmp;
			if(tmp == 0) {
				i += 3;
			} else if(tmp == 1) {
				i += 2;
			} else if(tmp == 2) {
				i += 1;
			}
		}
		
		return sum;
	}
	
	private static int count(int[] a, int i, int j, int k) {
		if(i > a.length - 1) {
			return 0;
		} else if(j > a.length - 1) {
			return 2;
		} else if(k > a.length - 1) {
			return 1;
		}
		
		if(a[j] - a[i] > delta) {
			return 2;
		} else if(a[k] - a[j] > delta) {
			return 1;
		} else {
			return 0;
		}
	}

}
