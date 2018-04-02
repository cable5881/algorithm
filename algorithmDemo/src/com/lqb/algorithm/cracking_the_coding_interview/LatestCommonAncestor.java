package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 有一棵无穷大的满二叉树，其结点按根结点一层一层地从左往右依次编号，根结点编号为1。现在有两个结点a，b。
 * 请设计一个算法，求出a和b点的最近公共祖先的编号。
 * 给定两个int a,b。为给定结点的编号。
 * 请返回a和b的最近公共祖先的编号。
 * 注意这里结点本身也可认为是其祖先。
 * 
 * @author:JackBauer
 * @date:2016年10月13日
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
