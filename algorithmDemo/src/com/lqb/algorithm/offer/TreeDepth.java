package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * ����һ�ö����������������ȡ�
 * �Ӹ���㵽Ҷ������ξ����Ľ�㣨������Ҷ��㣩�γ�����һ��·�����·���ĳ���Ϊ������ȡ�
 * @author:JackBauer
 * @date:2016��6��17�� ����2:47:57
 */
public class TreeDepth {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(8);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;
		
		t4.left = t8;
		
		System.out.println(treeDepth(t1));

	}
	
    public static int treeDepth(TreeNode pRoot){

    	return treeDepth(pRoot, 0);

    }
	
	private static int treeDepth(TreeNode pRoot,int depth){
		if( pRoot == null ){
			return 0;
		}
		
		int left = treeDepth(pRoot.left, 0) + 1;
		int right = treeDepth(pRoot.right, 0) + 1;
		
		return  left > right ? left : right;
	}
}
