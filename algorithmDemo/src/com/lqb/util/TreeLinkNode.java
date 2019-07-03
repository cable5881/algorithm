package com.lqb.util;

/**
 * 
 * @Description:树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * @author:JackBauer
 * @date:2016年7月1日 下午4:12:03
 */
public class TreeLinkNode {
	public int val = 0;
	public TreeLinkNode left = null;
	public TreeLinkNode right = null;
	public TreeLinkNode next = null;

	public TreeLinkNode(int val) {
		this.val = val;
	}

	public static TreeLinkNode getFullTree() {
		TreeLinkNode t1 = new TreeLinkNode(1);
		TreeLinkNode t2 = new TreeLinkNode(2);
		TreeLinkNode t3 = new TreeLinkNode(3);
		TreeLinkNode t4 = new TreeLinkNode(4);
		TreeLinkNode t5 = new TreeLinkNode(5);
		TreeLinkNode t6 = new TreeLinkNode(6);
		TreeLinkNode t7 = new TreeLinkNode(7);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		t2.next = t1;
		
		t3.left = t6;
		t3.right = t7;
		t3.next = t1;
		
		t4.next = t2;
		t5.next = t2;
		
		t6.next = t3;
		t7.next = t3;

		return t1;
	}
}
