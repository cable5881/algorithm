package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。 
 * @author:JackBauer
 * @date:2016年6月14日 下午1:39:20
 */
public class ConvertBST_IntoBList {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(10);
		TreeNode t2 = new TreeNode(6);
		TreeNode t3 = new TreeNode(14);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(8);
		TreeNode t6 = new TreeNode(12);
		TreeNode t7 = new TreeNode(16);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;

		TreeNode t = Convert(t1);
		
		
//		TreeNode t = t4;
		while(t != null){
			System.out.println(t.val);
			t = t.right;
		}
		
//		TreeNode v = t7;
//		while(v != null){
//			System.out.println(v.val);
//			v = v.left;
//		}
	}
	
	static TreeNode pre = null;
	
	public static TreeNode Convert(TreeNode root) {
		if( root == null ){
			return null;
		}
		
		TreeNode head = _Convert(root);
		
		//注意要返回头部
		while( head.left != null ){
			head = head.left;
		}
		
		return head;
	}
	
	private static TreeNode _Convert(TreeNode root) {
		if( root == null ){
			return null;
		}
		
		if( root.left != null ){
			Convert(root.left);
//			root.left = pre;
		}
		
		root.left = pre;
		
		if( pre != null ){
			pre.right = root;
//			root.left = pre;
		}
		
		pre = root;
		
		if( root.right != null ){
			Convert(root.right);
//			root.right = pre;
		}
		
		return root;
	}
	
	
	

}
