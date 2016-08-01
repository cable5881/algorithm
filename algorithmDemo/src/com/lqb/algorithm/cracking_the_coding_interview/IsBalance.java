package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 实现一个函数，检查二叉树是否平衡，平衡的定义如下，对于树中的任意一个结点，其两颗子树的高度差不超过1。
 * 给定指向树根结点的指针TreeNode* root，请返回一个bool，代表这棵树是否平衡。
 * @Author:JackBauer
 * @Date:2016年8月1日
 */
public class IsBalance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean isBalance(TreeNode root) {
		if(root == null){
			return true;
		}else{
			int left_level = isBalanceCore(root.left);
			int right_level = isBalanceCore(root.right);
			return Math.abs(left_level - right_level) <= 1;
		}
    }
	
	 
	private int isBalanceCore(TreeNode node){
		if(node == null){
			return 0;
		}
		
		int left_level = isBalanceCore(node.left);
		int right_level = isBalanceCore(node.right);
		
		return left_level > right_level ? left_level + 1 : right_level + 1;
	}

}
