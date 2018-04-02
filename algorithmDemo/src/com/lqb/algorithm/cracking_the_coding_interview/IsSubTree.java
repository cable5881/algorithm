package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * @Description:t2(几百个结点)是否为t1（几百万个）的子树
 * @Author:JackBauer
 * @Date:2016年8月10日
 */
public class IsSubTree {

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
		
		TreeNode s1 = new TreeNode(3);
		TreeNode s2 = new TreeNode(6);
		TreeNode s3 = new TreeNode(7);
		
		s1.left = s2;
		s1.right = s3;
		
		IsSubTree test = new IsSubTree();
		System.out.println(test.isSubTree(t1, s1, s1));
	}
	
	public boolean isSubTree(TreeNode f, TreeNode s, TreeNode sOrigin){
		if(s == null){
			return true;
		}else if(f == null){
			return false;
		}
		
		if(s.val == f.val){
			return isSubTree(f.left, s.left, sOrigin) && isSubTree(f.right, s.right, sOrigin);
		}else{
			return isSubTree(f.left, sOrigin, sOrigin) || isSubTree(f.right, sOrigin, sOrigin);
		}
	}
}
