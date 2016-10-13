package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ��n������Ķ��������nֻ���ϣ���Щ����ͬʱ��ʼ���Ŷ���εı����У��������Щ������ײ�ĸ��ʡ�
 * (�������ײ��ָ����������ֻ���ϻ���ײ)
 * ����һ��int n(3<=n<=10000)������n���κ�nֻ���ϣ��뷵��һ��double��Ϊ��ײ�ĸ��ʡ�
 * 
 * @author:JackBauer
 * @date:2016��10��13��
 */
public class AntsCollision {

	public static void main(String[] args) {
		AntsCollision test = new AntsCollision();
		System.out.println(test.antsCollision(3));
		System.out.println(test.antsCollision(4));
		System.out.println(test.antsCollision(5));
		System.out.println(test.antsCollision(10));
		System.out.println(test.antsCollision(10000));
	}
	
	public double antsCollision(int n) {
		return 1 - Math.pow(0.5, n) * 2;
    }

}
