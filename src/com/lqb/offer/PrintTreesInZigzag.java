package com.lqb.offer;

import java.util.ArrayList;
import java.util.Stack;

import com.lqb.util.TreeNode;

/**
 * 请实现一个函数按照之字形打印二叉树，
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * @Author:JackBauer
 * @Date:2016年7月3日 下午3:40:01
 */
public class PrintTreesInZigzag {

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

		PrintTreesInZigzag test = new PrintTreesInZigzag();
//		test.printTreesInZigzag(t1);
		ArrayList<ArrayList<Integer>> paths = test.print(t1);
		System.out.println(paths);
	}

	public void printTreesInZigzag(TreeNode root) {
		if (root == null) {
			return;
		}

		int flag = 1;
		int currentLevelNodes = 1;
		int nextLevelNodes = 0;

		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();

		stack1.push(root);

		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			TreeNode node = null;
			if (flag == 1) {
				node = stack1.pop();
				nextLevelNodes += pushNodesIntoStack(stack2, node, PushOrder.LEFT);
			} else {
				node = stack2.pop();
				nextLevelNodes += pushNodesIntoStack(stack1, node, PushOrder.RIGHT);
			}

			node.print();
			if (--currentLevelNodes == 0) {
				nextLine();
				flag = flag == 1 ? 2 : 1;
				currentLevelNodes = nextLevelNodes;
				nextLevelNodes = 0;
			}
		}
	}

	private void nextLine() {
		System.out.println();
	}

	private int pushNodesIntoStack(Stack<TreeNode> stack, TreeNode node, PushOrder order) {
		int validNodes = 0;

		switch (order) {
		case LEFT:
			if (node.left != null) {
				stack.push(node.left);
				validNodes++;
			}

			if (node.right != null) {
				stack.push(node.right);
				validNodes++;
			}
			break;

		case RIGHT:
			if (node.right != null) {
				stack.push(node.right);
				validNodes++;
			}
			if (node.left != null) {
				stack.push(node.left);
				validNodes++;
			}

		}
		return validNodes;
	}

	enum PushOrder {
		LEFT, RIGHT
	}
	
	public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
		
		if (pRoot == null) {
			return paths;
		}

		int flag = 1;
		int currentLevelNodes = 1;
		int nextLevelNodes = 0;

		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();

		stack1.push(pRoot);

		ArrayList<Integer> path = new ArrayList<>();
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			TreeNode node = null;
			if (flag == 1) {
				node = stack1.pop();
				nextLevelNodes += pushNodesIntoStack(stack2, node, PushOrder.LEFT);
			} else {
				node = stack2.pop();
				nextLevelNodes += pushNodesIntoStack(stack1, node, PushOrder.RIGHT);
			}

			path.add(node.val);
			if (--currentLevelNodes == 0) {
				paths.add(path);
				path = new ArrayList<>();
				flag = flag == 1 ? 2 : 1;
				currentLevelNodes = nextLevelNodes;
				nextLevelNodes = 0;
			}
		}
		
		return paths;
	}

}
