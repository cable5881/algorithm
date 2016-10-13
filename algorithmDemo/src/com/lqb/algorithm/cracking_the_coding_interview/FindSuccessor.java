package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * Ѱ����һ�����
 * 
 * �����һ���㷨��Ѱ�Ҷ�������ָ��������һ����㣨����������ĺ�̣���
 * �������ĸ����ָ��TreeNode* root�ͽ���ֵint p ,�뷵��ֵΪp�Ľ��ĺ�̽���ֵ��
 * ��֤����ֵ���ڵ�����С�ڵ���100000��û���ظ�ֵ���������ں�̷���-1��
 * 
 * @author:JackBauer
 * @date:2016��10��13��
 */
public class FindSuccessor {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(8);
		TreeNode t9 = new TreeNode(9);
		TreeNode t10 = new TreeNode(10);
		TreeNode t11 = new TreeNode(11);
		TreeNode t12 = new TreeNode(12);
		TreeNode t13 = new TreeNode(13);
		TreeNode t14 = new TreeNode(14);
		TreeNode t15 = new TreeNode(15);

		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		t4.left = t8;
		t4.right = t9;
		t5.left = t10;
		t5.right = t11;
		t6.left = t12;
		t6.right = t13;
		t7.left = t14;
		t7.right = t15;

		// ���Һ��ӣ���һ���ڵ�Ϊ9
		System.out.println(new FindSuccessor().findSucc(t1, 4));

		// ���Һ���,��Ϊ���ڵ������,��һ���ڵ�Ϊ4
		System.out.println(new FindSuccessor().findSucc(t1, 8));

		// ���Һ���,��Ϊ���ڵ�Ϊү���ӵ�����,��һ���ڵ�Ϊ2
		System.out.println(new FindSuccessor().findSucc(t1, 9));

		// ���Һ���,��Ϊ���ڵ�Ϊү���ӵ��Һ���,��һ���ڵ�Ϊ���ڵ�1
		System.out.println(new FindSuccessor().findSucc(t1, 11));

		// ���Һ���,��Ϊ���ҽڵ�,û����һ���ڵ�,����-1
		System.out.println(new FindSuccessor().findSucc(t1, 15));

	}

	public int findSucc(TreeNode root, int p) {
		inOrder(root, p);
		return successor == null ? -1 : successor.val;
	}

	private boolean isFound = false;
	private TreeNode successor = null;

	private void inOrder(TreeNode node, int p) {
		if (node == null) {
			return;
		}

		inOrder(node.left, p);

		if (isFound == true) {
			if (successor == null) {
				successor = node;
			}

			return;
		}

		if (node.val == p) {
			isFound = true;
		}

		inOrder(node.right, p);
	}

}
