package com.lqb.algorithm.cracking_the_coding_interview;
/*
 * 
	����ֱ������ϵ�ϵ�����ֱ�ߣ�ȷ��������ֱ�߻᲻���ཻ��
	
	�߶���б�ʺͽؾ����ʽ��������double s1��double s2��double y1��double y2���ֱ����ֱ��1��2��б��(��s1,s2)�ͽؾ�(��y1,y2)���뷵��һ��bool���������������ֱ���Ƿ��ཻ��������ֱ���غ�Ҳ��Ϊ�ཻ��
	����������
	
	3.14,1,3.14,2
	
	���أ�false
 * 
 */
public class CrossLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean checkCrossLine(double s1, double s2, double y1, double y2) {
		return s1 == s2 ? (y1 == y2 ? true : false) : true;
	}

}
