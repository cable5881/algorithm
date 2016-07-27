package com.lqb.algorithm.offer.domain;

public class TreeNode {
	public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
    
    public static void inorder( TreeNode treeNode){
		if( treeNode == null ){
			return;
		}
		
		inorder(treeNode.left);
		System.out.println(treeNode.val);
		inorder(treeNode.right);
	}
    
    public static void preorder( TreeNode treeNode){
		if( treeNode == null ){
			return;
		}
		
		System.out.println(treeNode.val);
		preorder(treeNode.left);
		preorder(treeNode.right);
	}
    
    
    public static void main(String[] args) {
    	TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		
//		BST
//		TreeNode t1 = new TreeNode(5);
//		TreeNode t2 = new TreeNode(3);
//		TreeNode t3 = new TreeNode(7);
//		TreeNode t4 = new TreeNode(2);
//		TreeNode t5 = new TreeNode(4);
//		TreeNode t6 = new TreeNode(6);
//		TreeNode t7 = new TreeNode(8);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;
	}
    
    /**
     * @Description: print current node's value
     * @Author:JackBauer
     * @Date:2016年7月2日下午3:17:08
     */
    public void print(){
    	System.out.print(val);
    }
}
