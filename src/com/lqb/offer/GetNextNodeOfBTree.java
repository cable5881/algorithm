package com.lqb.offer;

import com.lqb.util.TreeLinkNode;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。 
 * @author:JackBauer
 * @date:2016年7月1日 下午5:08:00
 */
public class GetNextNodeOfBTree {

	public static void main(String[] args) {
		TreeLinkNode t1 = new TreeLinkNode(1);
		TreeLinkNode t2 = new TreeLinkNode(2);
		TreeLinkNode t3 = new TreeLinkNode(3);
		TreeLinkNode t4 = new TreeLinkNode(4);
		TreeLinkNode t5 = new TreeLinkNode(5);
		TreeLinkNode t6 = new TreeLinkNode(6);
		TreeLinkNode t7 = new TreeLinkNode(7);
		TreeLinkNode t8 = new TreeLinkNode(8);
		
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
		t5.right = t8;
		t8.next = t5;
		
		t6.next = t3;
		t7.next = t3;
		
		TreeLinkNode t = new GetNextNodeOfBTree().GetNext(t8);
		if(t != null)
			System.out.println(t.val);
	}

	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if (pNode == null) {
			return null;
		}

		if (pNode.right != null) {
			return getMostLeft((TreeLinkNode) pNode.right);
		}

		if (pNode.next == null) {
			return null;
		}

		if (pNode.next.left == pNode) {
			return (TreeLinkNode) pNode.next;
		} else {
			return getRoot(pNode);
		}
	}

	private TreeLinkNode getRoot(TreeLinkNode pNode) {
		if (pNode.next != null) {
			if (pNode.next.right == pNode) {
				return getRoot((TreeLinkNode) pNode.next);
			} else {
				return (TreeLinkNode) pNode.next;
			}
		} else {
			return null;
		}
	}

	private TreeLinkNode getMostLeft(TreeLinkNode pNode) {
		if(pNode.left != null){
			return getMostLeft((TreeLinkNode) pNode.left);
		}else return pNode;
	}

}
