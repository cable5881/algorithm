package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 寻找下一个结点
 * 
 * 请设计一个算法，寻找二叉树中指定结点的下一个结点（即中序遍历的后继）。
 * 给定树的根结点指针TreeNode* root和结点的值int p ,请返回值为p的结点的后继结点的值。
 * 保证结点的值大于等于零小于等于100000且没有重复值，若不存在后继返回-1。
 * 
 * @author:JackBauer
 * @date:2016年10月13日
 */
public class FindSuccessor {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(8);
		TreeNode t9 = new TreeNode(9);
		TreeNode t10 = new TreeNode(10);
		TreeNode t11 = new TreeNode(11);
		TreeNode t12 = new TreeNode(12);
		TreeNode t13 = new TreeNode(13);
		TreeNode t14 = new TreeNode(14);
		TreeNode t15 = new TreeNode(15);

		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		t4.left = t8;
		t4.right = t9;
		t5.left = t10;
		t5.right = t11;
		t6.left = t12;
		t6.right = t13;
		t7.left = t14;
		t7.right = t15;

		// 有右孩子，下一个节点为9
		System.out.println(new FindSuccessor().findSucc(t1, 4));

		// 无右孩子,其为父节点的左孩子,下一个节点为4
		System.out.println(new FindSuccessor().findSucc(t1, 8));

		// 无右孩子,其为父节点为爷孩子的左孩子,下一个节点为2
		System.out.println(new FindSuccessor().findSucc(t1, 9));

		// 无右孩子,其为父节点为爷孩子的右孩子,下一个节点为根节点1
		System.out.println(new FindSuccessor().findSucc(t1, 11));

		// 无右孩子,且为最右节点,没有下一个节点,返回-1
		System.out.println(new FindSuccessor().findSucc(t1, 15));

	}

	public int findSucc(TreeNode root, int p) {
		inOrder(root, p);
		return successor == null ? -1 : successor.val;
	}

	private boolean isFound = false;
	private TreeNode successor = null;

	private void inOrder(TreeNode node, int p) {
		if (node == null) {
			return;
		}

		inOrder(node.left, p);

		if (isFound == true) {
			if (successor == null) {
				successor = node;
			}

			return;
		}

		if (node.val == p) {
			isFound = true;
		}

		inOrder(node.right, p);
	}

}
