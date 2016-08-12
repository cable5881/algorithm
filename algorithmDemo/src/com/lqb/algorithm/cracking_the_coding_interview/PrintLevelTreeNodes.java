package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;

import com.lqb.algorithm.offer.domain.ListNode;
import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * ����һ�ö������������һ���㷨����������ĳһ��������н�������
 * �����������ĸ����ָ��TreeNode* root���Լ������Ͻ�����ȣ�
 * �뷵��һ������ListNode���������������н���ֵ���밴���ϴ������ҵ�˳�����ӣ�
 * ��֤��Ȳ��������ĸ߶ȣ����Ͻ���ֵΪ�Ǹ������Ҳ�����100000��
 * @Author:JackBauer
 * @Date:2016��8��12��
 */
public class PrintLevelTreeNodes {

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
		
		PrintLevelTreeNodes test = new PrintLevelTreeNodes();
		ListNode head = test.getTreeLevel(t1, 3);
		head.printAll();
	}

	public ListNode getTreeLevel(TreeNode root, int dep) {
		if (root == null || dep < 1) {
			return null;
		}
		
		ArrayList<Integer> levelNodes = new ArrayList<>();
		getTreeLevel(root, levelNodes, dep);
		ListNode levelHead = new ListNode(levelNodes.get(0));
		ListNode levelCur = levelHead;
		for(int i = 1; i < levelNodes.size(); i++){
			levelCur.next = new ListNode(levelNodes.get(i));
			levelCur = levelCur.next;
		}
		return levelHead;
	}

	private void getTreeLevel(TreeNode node, ArrayList<Integer> levelNodes,int dep) {
		if (node == null) {
			return;
		}

		if (--dep == 0) {
			levelNodes.add(node.val);
		} else {
			getTreeLevel(node.left, levelNodes, dep);
			getTreeLevel(node.right, levelNodes, dep);
		}
	}

}
