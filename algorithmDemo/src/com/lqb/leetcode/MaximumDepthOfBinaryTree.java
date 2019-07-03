package com.lqb.leetcode;


import com.lqb.util.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
//        TreeNode node0 = new TreeNode(1);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(1);
//        TreeNode node3 = new TreeNode(1);
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(1);
//        TreeNode node6 = new TreeNode(1);
//        TreeNode node7 = new TreeNode(1);
//
//        node0.left =
//
//        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left) + 1;
        int rightMaxDepth = maxDepth(root.right) + 1;

        return leftMaxDepth > rightMaxDepth ? leftMaxDepth : rightMaxDepth;
    }

}
