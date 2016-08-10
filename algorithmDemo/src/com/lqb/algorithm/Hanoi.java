package com.lqb.algorithm;

/**
 * @Description:��ŵ��
 * @Author:JackBauer
 * @Date:2016��8��10��
 */
public class Hanoi {

	public static void main(String[] args) {
		Hanoi test = new Hanoi();
		test.move(3, 'a', 'b', 'c');
	}

	private void move(int n, char origin, char buffer, char destination) {
		if (n == 1) {
			System.out.println(origin + "-->" + destination);
		} else {
			//��n - 1 �������Ƶ�buffer
			//����buffer��ΪĿ�ĵ�destination
			//������Ϊ������n - 1�����ӵ��ƶ�����,ֻ����Ŀ�ĵز�ͬ
			move(n - 1, origin, destination, buffer);
			
			//n - 1 �������ƶ���֮������һ�������ƶ���Ŀ�ĵ�
			System.out.println(origin + "-->" + destination);
			
			//ʣ�µ�n - 1�����Ӵ�buffer�ƶ���destination
			//������Ϊ������n - 1�����ӵ��ƶ�����,ֻ������㲻ͬ
			move(n - 1, buffer, origin, destination);
		}
	}
}
