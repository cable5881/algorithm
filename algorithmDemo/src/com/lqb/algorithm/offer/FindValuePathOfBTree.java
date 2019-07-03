package com.lqb.algorithm.offer;

import java.util.ArrayList;

import com.lqb.util.TreeNode;

/**
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。 
 * @author:JackBauer
 * @date:2016年6月14日 下午1:26:42
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
		f.findPath(t1, 10);
		System.out.println(f.paths);
	}

	private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {

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
			//注意没有到达树的叶子结点就形成的路径不叫路径（除非题目特殊说明可以不需要到达根节点）
			if( node.left == null || node.right == null ){
				//注意不能直接把path的引用直接添加进来！需要复制新的
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
