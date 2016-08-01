package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * ʵ��һ�����������������Ƿ�ƽ�⣬ƽ��Ķ������£��������е�����һ����㣬�����������ĸ߶Ȳ����1��
 * ����ָ����������ָ��TreeNode* root���뷵��һ��bool������������Ƿ�ƽ�⡣
 * @Author:JackBauer
 * @Date:2016��8��1��
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
