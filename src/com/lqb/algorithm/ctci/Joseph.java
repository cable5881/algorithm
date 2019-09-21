package com.lqb.algorithm.ctci;

/**
 * 约瑟夫问题是一个非常著名的趣题，即由n个人坐成一圈，按顺时针由1开始给他们编号。
 * 然后由第一个人开始报数，数到m的人出局。现在需要求的是最后一个出局的人的编号。 
 * 给定两个int n和m，代表游戏的人数。请返回最后一个出局的人的编号。保证n和m小于等于1000。
 * 
 * @author:JackBauer
 * @date:2016年10月12日
 */
public class Joseph {

	public static void main(String[] args) {
		Joseph test = new Joseph();
		System.out.println(test.getResult(5, 3));
	}
	
	public int getResult(int n, int m) {
		if(n < 1 || m < 1) {
			return -1;
		}
		
		Node head = new Node(1);
		Node cur = head;
		for(int i = 2; i <= n; i++) {
			Node node = new Node(i);
			cur.next = node;
			cur = node;
		}
		cur.next = head;
		
		Node p = cur;
		while(p.next != p) {
			for(int i = 1; i < m; i++) {
				p = p.next;
			}
			p.next = p.next.next;
		}
		
		return p.val;
    }
	
	private class Node {
		int val;
		Node next;
		
		public Node(int val) {
			this.val = val;
		}
	}

}
