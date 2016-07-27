package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.TreeNode;

public class ReconstructBTree {

	public static void main(String[] args) {
		//正常
//		int[] pre = {1,2,4,7,3,5,6,8};
//		int[] in = {4,7,2,1,5,3,8,6};
		
		//只有左子树
//		int[] pre = {1,2,3,4,5,6,7,8};
//		int[] in = {8,7,6,5,4,3,2,1};
		
		//只有右子树
//		int[] pre = {1,2,3,4,5,6,7,8};
//		int[] in = {1,2,3,4,5,6,7,8};
		
		//左右子树不匹配
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {9,7,2,1,5,3,8,6};
		
		TreeNode  node = reConstructBinaryTree(pre, in);
		inorder(node);
		
	}

	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {

		// Arrays.copyOfRange(original, from, to);
		
		if( pre.length == 0 || pre == null || in.length == 0 || in == null ){
			return null;
		}

		return constructBinaryTree(pre,0,pre.length - 1,in, 0, in.length - 1);
	}

	public static TreeNode constructBinaryTree(int[] pre,int preStart,int preEnd,
			int[] in, int inStart,int inEnd) {

		TreeNode root = null;
		int rootStart = inStart;
		
		if( preStart > preEnd || inStart > inEnd ){
			return null;
		}
		
		//注意rootStart有可能越界
		while( inStart < inEnd && pre[preStart] != in[rootStart] && rootStart <= preEnd){
			rootStart++;
		}
		
		if( inStart <= inEnd &&  pre[preStart] == in[rootStart]){
			root = new TreeNode(pre[preStart]);
		}else{
			return null;
		}
		
		int newPreEnd = rootStart - inStart + preStart;
		int newInEnd = rootStart - 1;
		
		root.left = constructBinaryTree(pre, preStart + 1, newPreEnd,
				in, inStart, newInEnd);
		root.right = constructBinaryTree(pre, newPreEnd + 1, preEnd,
				in, rootStart + 1, inEnd);

		return root;
	}

	public static void inorder( TreeNode treeNode){
		if( treeNode == null ){
			return;
		}
		
		inorder(treeNode.left);
		System.out.println(treeNode.val);
		inorder(treeNode.right);
	}
}

