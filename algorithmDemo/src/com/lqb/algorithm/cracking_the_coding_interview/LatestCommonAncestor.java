package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ��һ���������������������㰴�����һ��һ��ش����������α�ţ��������Ϊ1���������������a��b��
 * �����һ���㷨�����a��b�������������ȵı�š�
 * ��������int a,b��Ϊ�������ı�š�
 * �뷵��a��b������������ȵı�š�
 * ע�������㱾��Ҳ����Ϊ�������ȡ�
 * 
 * @author:JackBauer
 * @date:2016��10��13��
 */
public class LatestCommonAncestor {

	public static void main(String[] args) {
		LatestCommonAncestor test = new LatestCommonAncestor();
		System.out.println(test.getLCA(12,15));
	}
	
	public int getLCA(int a, int b) {
		if(a < 1 || b < 1) {
			return -1;
		}
		
		int aP = getDirectAncestor(a);
		int bP = getDirectAncestor(b);
		int biggerP;
		int smallerP;
		
		if(aP > bP) {
			biggerP = aP;
			smallerP = bP;
		} else {
			biggerP = bP;
			smallerP = aP;
		}
		
		int bigLayer = getLayer(biggerP);
		int smallLayer = getLayer(smallerP);
		int diffLayer = bigLayer - smallLayer;
		for(int i = 0; i < diffLayer; i++) {
			biggerP = getDirectAncestor(biggerP);
		}
		
		while(biggerP != smallerP) {
			biggerP = getDirectAncestor(biggerP);
			smallerP = getDirectAncestor(smallerP);
		}
		
		return smallerP;
    }
	
	private int getLayer(int n) {
		return (int) (Math.log(n) / Math.log(2));
	}
	
	private int getDirectAncestor(int n) {
		return (n & 1) == 0 ? n / 2 : (n - 1) / 2;
	}

}
