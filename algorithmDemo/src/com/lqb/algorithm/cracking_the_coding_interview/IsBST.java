package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 请实现一个函数，检查一棵二叉树是否为二叉查找树。
 * 给定树的根结点指针TreeNode* root，请返回一个bool，代表该树是否为二叉查找树。
 * @Author:JackBauer
 * @Date:2016年8月12日
 */
public class IsBST {

	public static void main(String[] args) {
//		BST
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(3);
		TreeNode t3 = new TreeNode(7);
		TreeNode t4 = new TreeNode(1);
		TreeNode t5 = new TreeNode(4);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(2);
//		TreeNode t8 = new TreeNode(5);

		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t4.right = t7;
		
//		t5.right = t8;

		IsBST test = new IsBST();
		System.out.println(test.checkBST(t1));
		System.out.println(checkBST_1(t1));
		System.out.println(checkBST_2(t1));
	}
	
	//错误解法。没有考虑到子节点小于所有父节点和祖宗结点的情况
	public boolean checkBST(TreeNode root) {
		if(root == null){
			return true;
		}
		
		return checkBSTCore(root.left, root.val, -1) && checkBSTCore(root.right, root.val, 1);
    }
	
	public boolean checkBSTCore(TreeNode node, int rootV, int flag) {
		if(node == null){
			return true;
		}
		
		//warning!!!
		//如果是根元素的左子树，则左子树下的所有元素都不能大于根元素；根元素的右子树以此类推
		if(flag == -1 && node.val > rootV){
			return false;
		}else if(flag == 1 && node.val < rootV){
			return false;
		}
		
		boolean leftResult = true;
		boolean rightResult = true;
		
		if(node.left != null){
			if(node.val >= node.left.val){
				leftResult = checkBSTCore(node.left, rootV, flag);
			}else return false;
		}
		
		if(node.right != null){
			if(node.val <= node.right.val){
				rightResult = checkBSTCore(node.right, rootV, flag);
			}else return false;
		}
		
		return leftResult && rightResult;
	}

	
	private static int lastPrinted = Integer.MIN_VALUE;
	/**
	 * @Description:中序遍历解法
	 * @Author:JackBauer
	 * @Date:2016年8月12日
	 */
	public static boolean checkBST_1(TreeNode root) {
		if(root == null){
			return true;
		}
		
		boolean left = checkBST_1(root.left);
		/*
		 * 	可以这样改进，只要是false就不必继续执行了
		 
		 	if(!checkBST_1(root.left)){
		 		return false;
		 	}
		 	
		 */
		
		if(root.val <= lastPrinted){
			return false;
		}
		
		lastPrinted = root.val;
		
		boolean right = checkBST_1(root.right);
		
		return left && right;
	}
	
	public static boolean checkBST_2(TreeNode root) {
		if(root == null){
			return true;
		}
		
		return checkBST_2_Core(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * @Description:传递最小值和最大值法
	 * @Author:JackBauer
	 * @Date:2016年8月12日
	 */
	public static boolean checkBST_2_Core(TreeNode node, int min, int max){
		if(node == null){
			return true;
		}
		
		if(node.val <= min || node.val >= max){
			return false;
		}
		
		if(!checkBST_2_Core(node.left, min, node.val)){
			return false;
		}
		
		if(!checkBST_2_Core(node.right, node.val, max)){
			return false;
		}
		
		return true;
	}
}
