package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 给定一颗二叉搜索树，请找出其中的第k大的结点。
 * 例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。 
 * @Author:JackBauer
 * @Date:2016年7月3日 下午3:59:43
 */
public class KthBiggestNodeOfBTree {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(3);
		TreeNode t3 = new TreeNode(7);
		TreeNode t4 = new TreeNode(2);
		TreeNode t5 = new TreeNode(4);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(8);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;

		KthBiggestNodeOfBTree test = new KthBiggestNodeOfBTree();
		TreeNode node = test.kthNode(t1, 1);
		node.print();
	}

	TreeNode kthNode(TreeNode pRoot, int k) {
		if(pRoot == null || k < 1){
			return null;
		}
		
		return kthNodeCore(pRoot, k);
	}

	int n = 1;
	
	private TreeNode kthNodeCore(TreeNode pRoot, int k) {
		if(pRoot == null){
			return null;
		}

		TreeNode node = null;
		
		if(pRoot.left != null){
			node = kthNodeCore(pRoot.left, k);
		}
		
		if(n++ == k){
			return pRoot;
		}
		
		return node == null ? kthNodeCore(pRoot.right, k) : node;
	}

}
