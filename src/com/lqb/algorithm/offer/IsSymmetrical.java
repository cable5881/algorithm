package com.lqb.algorithm.offer;

import java.util.ArrayList;

import com.lqb.util.TreeNode;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 
 * @author:JackBauer
 * @date:2016年7月1日 下午5:09:27
 */
public class IsSymmetrical {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(1);
		TreeNode t3 = new TreeNode(1);
		TreeNode t4 = new TreeNode(1);
		TreeNode t5 = new TreeNode(1);
//		TreeNode t6 = new TreeNode(5);
//		TreeNode t7 = new TreeNode (4);

		t1.left = t2;
		t1.right = t3;

		t2.left = t4;
//		t2.right = t5;

		t3.left = t5;
//		t3.right = t7;

		IsSymmetrical test = new IsSymmetrical();
		System.out.println(test.isSymmetrical(t1));// false
		System.out.println(test.isSymmetrical2(t4));// true
		System.out.println(test.isSymmetrical2(null));// true
	}

	public boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}

		ArrayList<Integer> path = new ArrayList<>();
		addValInOrder(pRoot, path);

		int start = 0;
		int end = path.size() - 1;

		while (start < end) {
			if (path.get(start) != path.get(end)) {
				return false;
			}

			start++;
			end--;
		}

		return true;
	}

	private void addValInOrder(TreeNode pRoot, ArrayList<Integer> path) {
		if (pRoot != null) {
			addValInOrder(pRoot.left, path);
			path.add(pRoot.val);
			addValInOrder(pRoot.right, path);
		}else{
			path.add(null);
		}
	}

	public boolean isSymmetrical2(TreeNode pRoot){
		if(pRoot == null){
			return true;
		}
		
		return isSymmetricalCore(pRoot.left, pRoot.right);
	}

	private boolean isSymmetricalCore(TreeNode left, TreeNode right) {
		if(left == null && right == null){
			return true;
		}
		
		if(left == null || right == null){
			return false;
		}
		
		if(left.val != right.val){
			return false;
		}
		
		return isSymmetricalCore(left.left, right.right) && isSymmetricalCore(left.right, right.left);
	}
}
