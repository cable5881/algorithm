package com.lqb.offer;

import java.util.ArrayList;
import java.util.LinkedList;

import com.lqb.util.TreeNode;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
 * @Author:JackBauer
 * @Date:2016年7月3日 下午3:48:42
 */
public class PrintTreesInLines {

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
		t3.left = t5;
		t4.left = t6;
		t5.right = t7;

		PrintTreesInLines test = new PrintTreesInLines();
		test.printTreesInLines(t1);
		ArrayList<ArrayList<Integer>> paths = test.print(t7);
		System.out.println(paths);
	}

	public void printTreesInLines(TreeNode root) {
		if (root == null) {
			return;
		}

		int currentLevelNodes = 1;
		int nextLevelNodes = 0;

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			if (currentLevelNodes == 0) {
				nextLine();
				currentLevelNodes = nextLevelNodes;
				nextLevelNodes = 0;
			}

			currentLevelNodes--;
			TreeNode node = queue.pop();
			node.print();
			nextLevelNodes += pushNodesIntoQ(queue, node);
		}

	}

	private void nextLine() {
		System.out.println();
	}

	private int pushNodesIntoQ(LinkedList<TreeNode> queue, TreeNode node) {
		int validNodes = 0;

		if (node.left != null) {
			validNodes++;
			queue.add(node.left);
		}

		if (node.right != null) {
			validNodes++;
			queue.add(node.right);
		}

		return validNodes;
	}
	
	
	public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
		if (pRoot == null) {
			return paths;
		}

		int currentLevelNodes = 1;
		int nextLevelNodes = 0;

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(pRoot);

		ArrayList<Integer> path = new ArrayList<>();
		while (!queue.isEmpty()) {
			if (currentLevelNodes == 0) {
				paths.add(path);
				path = new ArrayList<>();
				currentLevelNodes = nextLevelNodes;
				nextLevelNodes = 0;
			}

			currentLevelNodes--;
			TreeNode node = queue.pop();
			path.add(node.val);
			nextLevelNodes += pushNodesIntoQ(queue, node);
		}
		paths.add(path);
		return paths;
	}
}
