package com.lqb.algorithm.offer.domain;

/**
 * 
 * @Description:���еĽ�㲻�����������ӽ�㣬ͬʱ����ָ�򸸽���ָ�롣
 * @author:JackBauer
 * @date:2016��7��1�� ����4:12:03
 */
public class TreeLinkNode extends TreeNode{
	public TreeNode next = null;

	public TreeLinkNode(int val) {
		super(val);
	}

	public static void main(String[] args) {
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
		
		TreeLinkNode.inorder(t1);
	}
}
