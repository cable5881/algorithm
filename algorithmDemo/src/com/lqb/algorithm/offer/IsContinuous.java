package com.lqb.algorithm.offer;

import java.util.Arrays;

/**
 * LL���������ر��,��Ϊ��ȥ����һ���˿���,���������Ȼ��2������,2��С��(һ����ԭ����54��^_^)...
 * ��������г����5����,�����Լ�������,�����ܲ��ܳ鵽˳��,����鵽�Ļ�,������ȥ��������Ʊ,�ٺ٣���
 * ������A,����3,С��,����,��Ƭ5��,��Oh My God!������˳��.....
 * LL��������,��������,������\С �����Կ����κ�����,����A����1,JΪ11,QΪ12,KΪ13��
 * �����5���ƾͿ��Ա�ɡ�1,2,3,4,5��(��С���ֱ���2��4),��So Lucky!����LL����ȥ��������Ʊ����
 * ����,Ҫ����ʹ�������ģ������Ĺ���,Ȼ���������LL��������Ρ�Ϊ�˷������,�������Ϊ��С����0�� 
 * @author:JackBauer
 * @date:2016��6��23�� ����5:14:12
 */
public class IsContinuous {

	public static void main(String[] args) {
		IsContinuous test = new IsContinuous();
		int[] numbers = {0,0,3,4,7};
		System.out.println(test.isContinuous(numbers));
	}

	public boolean isContinuous(int[] numbers) {
		if (numbers == null || numbers.length != 5) {
			return false;
		}

		Arrays.sort(numbers);

		int numbersOfGap = 0;
		int numbersOfZero = 0;

		for (int num : numbers) {
			if (num == 0) {
				numbersOfZero++;
			}
		}

		int small = numbersOfZero;
		int big = small + 1;

		while (big < 5) {
			if(numbers[big] - numbers[small] == 0){
				return false;
			}
			
			numbersOfGap += numbers[big] - numbers[small] - 1;
			small++;
			big++;
		}
		
		return numbersOfGap > numbersOfZero ? false : true;
	}

}
