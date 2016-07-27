package com.lqb.algorithm.offer;

import java.util.ArrayList;

import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * ����һ�Ŷ�������һ����������ӡ���������н��ֵ�ĺ�Ϊ��������������·����
 * ·������Ϊ�����ĸ���㿪ʼ����һֱ��Ҷ����������Ľ���γ�һ��·���� 
 * @author:JackBauer
 * @date:2016��6��14�� ����1:26:42
 */
public class FindValuePathOfBTree {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(4);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(6);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;

		FindValuePathOfBTree f = new FindValuePathOfBTree();
		f.FindPath(t1, 10);
		System.out.println(f.paths);
	}

	ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {

		if (root == null || root.val > target) {
			return paths;
		}

		addPath(root, 0, target, new ArrayList<Integer>());

		return paths;
	}

	private void addPath(TreeNode node, int sum, int target, ArrayList<Integer> path) {

		if (node == null) {
			return;
		}

		sum += node.val;

		if (sum > target) {
			return;
		}

		path.add(node.val);

		if (sum == target) {
			//ע��û�е������ĸ������γɵ�·������·��
			if( node.left == null || node.right == null ){
				//ע�ⲻ��ֱ�Ӱ�path������ֱ����ӽ�������Ҫ�����µ�
				ArrayList<Integer> pathCopy = new ArrayList<>(path); 
				paths.add(pathCopy);
			}
		} else {
			addPath(node.left, sum, target, path);
			addPath(node.right, sum, target, path);
		}
		
		path.remove(path.size() - 1);
	}
}
