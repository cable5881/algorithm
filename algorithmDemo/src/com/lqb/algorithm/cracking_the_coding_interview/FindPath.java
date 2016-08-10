package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.ArrayList;
import com.lqb.algorithm.offer.domain.TreeNode;

/**
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。 
 * @Author:JackBauer
 * @Date:2016年8月10日
 */
public class FindPath {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(0);
		TreeNode t9 = new TreeNode(0);
		
		t1.left = t2;
		t1.right = t3;
		
		t2.left = t4;
		t2.right = t5;
		
		t3.left = t6;
		t3.right = t7;
				
		t4.left = t8;
		t8.left = t9;

		FindPath f = new FindPath();
		f.findPath(t1, 7);
		System.out.println(f.paths);
		
		//official version test
		findSum(t1, 7);
	}
	
	private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
	
	public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
		if(root == null || root.val > target){
			return paths;
		}
		
		addPath(root, 0, target, new ArrayList<Integer>());
		findPath(root.left,target);
		findPath(root.right,target);
		return paths;
	}
	
	private void addPath(TreeNode node, int sum, int target, ArrayList<Integer> path) {
		if(node == null){
			return;
		}
		
		sum += node.val;
		
		if(sum > target){
			return;
		}
		
		path.add(node.val);
		
		if(sum == target){
			ArrayList<Integer> pathCp = new ArrayList<>(path);
			paths.add(pathCp);
		}
		
		//no else 'cause the comming path may also true
		
//		else{
//			addPath(node.left, sum, target, path);
//			addPath(node.right, sum, target, path);
//		}
		
		addPath(node.left, sum, target, path);
		addPath(node.right, sum, target, path);
		
		path.remove(path.size() - 1);
	}
	
	
	/*****************official version*********************/
	
	public static void findSum(TreeNode node, int sum, int[] path, int level) {
		if (node == null) {
			return;
		}
		
		/* Insert current node into path */
		path[level] = node.val; 
		
		int t = 0;
		for (int i = level; i >= 0; i--){
			t += path[i];
			if (t == sum) {
				print(path, i, level);
			}
		}
		
		findSum(node.left, sum, path, level + 1);
		findSum(node.right, sum, path, level + 1);
		
		/* Remove current node from path. Not strictly necessary, since we would
		 * ignore this value, but it's good practice.
		 */
		path[level] = Integer.MIN_VALUE; 
	}
	
	public static int depth(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(depth(node.left), depth(node.right));
		}
	}
	
	public static void findSum(TreeNode node, int sum) {
		int depth = depth(node);
		int[] path = new int[depth];
		findSum(node, sum, path, 0);
	}

	private static void print(int[] path, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}

	/*****************official version*********************/
}
