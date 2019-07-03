package com.lqb.algorithm.offer;

import com.lqb.util.TreeNode;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树
 * 
 * @author:JackBauer
 * @date:2016年6月18日 下午8:02:01
 */
public class IsBalancedBTree {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		
		TreeNode t8 = new TreeNode(6);
		TreeNode t9 = new TreeNode(7);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;
		
		t4.left = t8;
		t4.right = t9;
		
		IsBalancedBTree test = new IsBalancedBTree();
		System.out.println(test.IsBalanced_Solution(t1));
	}

	public boolean IsBalanced_Solution(TreeNode root) {
		if (root == null) {
			return true;
		}

		int difference = getDifferenceBeteenLtreeAndRtree(root);

		if (difference <= 1 && difference >= -1) {
			return true;
		} else {
			return false;
		}

	}

	public int getDifferenceBeteenLtreeAndRtree(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int left_level = getMaxBTreeLevel(root.left);
		int right_level = getMaxBTreeLevel(root.right);

		return left_level - right_level;
	}

	public int getMaxBTreeLevel(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int left_level = getMaxBTreeLevel(node.left);
		int right_level = getMaxBTreeLevel(node.right);

		return left_level > right_level ? (left_level + 1) : (right_level + 1);
	}

}
