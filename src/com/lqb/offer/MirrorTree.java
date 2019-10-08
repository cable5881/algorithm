package com.lqb.offer;

import com.lqb.util.TreeNode;

public class MirrorTree {

	public static void main(String[] args) {
//		TreeNode t1 = new TreeNode(1);
//		TreeNode t2 = new TreeNode(2);
//		TreeNode t3 = new TreeNode(3);
//		TreeNode t4 = new TreeNode(4);
//		TreeNode t5 = new TreeNode(5);
//		TreeNode t6 = new TreeNode(6);
//		TreeNode t7 = new TreeNode(7);
//		
//		t1.left = t2;
//		t1.right = t3;
//		
//		t2.left = t4;
//		t2.right = t5;
//		
//		t3.left = t6;
//		t3.right = t7;
//
//		mirror(t1);
		
//		TreeNode.preorder(t1);
		
		TreeNode tNull = null;
		mirror(tNull);
		TreeNode.preorder(tNull);
		
		TreeNode tOne = new TreeNode(100);
		mirror(tOne);
		TreeNode.preorder(tOne);
		
	}

	public static void mirror(TreeNode root) {
		
		if( root == null ){
			return;
		}
		
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		mirror(root.left);
		mirror(root.right);
	}

}
