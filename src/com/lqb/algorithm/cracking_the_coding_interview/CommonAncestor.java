package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.LinkedList;

import com.lqb.util.TreeNode;

/**
 * @Description:公共祖先
 * @Author:JackBauer
 * @Date:2016年8月31日 下午12:46:03
 */
public class CommonAncestor {

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
		
		CommonAncestor test = new CommonAncestor();
		test.getLCommonAncestor(t1, t4, t5).print();
	}

	public TreeNode getLCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null) {
			return null;
		}

		LinkedList<TreeNode> path_p = new LinkedList<>();
		LinkedList<TreeNode> path_q = new LinkedList<>();

		if (findPath(root, q, path_p) && findPath(root, p, path_q)) {
			TreeNode ancestor = root;
			while (true) {
				TreeNode pNode = path_p.pollFirst();
				TreeNode qNode = path_q.pollFirst();
				if (pNode != qNode) {
					return ancestor;
				} else {
					ancestor = pNode;
				}
			}
		}

		return null;
	}

	private boolean findPath(TreeNode root, TreeNode s, LinkedList<TreeNode> path) {
		if (root == null) {
			return false;
		}

		path.add(root);

		if (root.val == s.val) {
			return true;
		}

		if (findPath(root.left, s, path) || findPath(root.right, s, path)) {
			return true;
		}
		
		path.pollLast();
		
		return false;
	}

}
