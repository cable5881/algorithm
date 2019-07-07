package com.lqb.algorithm.offer;

import com.lqb.util.TreeNode;

public class HasSubtree {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;
		
		TreeNode t8 = new TreeNode(9);

		System.out.println(hasSubtree(t1, t8));
	}

	public static boolean hasSubtree(TreeNode root1, TreeNode root2) {

		if (root1 == null || root2 == null) {
			return false;
		}

		boolean flag_left = true;
		boolean flag_right = true;

		if (root1.val == root2.val) {
			if (root2.left != null) {
				flag_left = hasSubtree(root1.left, root2.left);
			}
			if (root2.right != null) {
				flag_right = hasSubtree(root1.right, root2.right);
			}

			if (flag_left && flag_right) {
				return true;
			}
		}
		
		flag_left = hasSubtree(root1.left, root2);
		flag_right = hasSubtree(root1.right, root2);

		if (flag_left || flag_right) {
			return true;
		} else {
			return false;
		}

	}
}
