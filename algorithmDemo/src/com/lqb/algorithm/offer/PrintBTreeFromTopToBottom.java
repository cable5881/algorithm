package com.lqb.algorithm.offer;

import java.util.ArrayList;

import com.lqb.algorithm.offer.domain.TreeNode;

public class PrintBTreeFromTopToBottom {

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
		
		ArrayList<Integer> arr = PrintFromTopToBottom(t1);
		System.out.println(arr);
	}

	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<TreeNode> queue = new ArrayList<>();
		
		if( root == null ){
			return arr;
		}
		
		queue.add(root);
		
		while( !queue.isEmpty() ){
			TreeNode node =queue.get(0);
			queue.remove(0);
			arr.add(node.val);
			
			if( node.left != null ){
				queue.add(node.left);
			}
			
			if( node.right != null ){
				queue.add(node.right);
			}
		}
		
		return arr;
	}
	
}
