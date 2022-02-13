package com.lqb.leetcode;

import com.lqb.util.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 */
public class MinimumDepthOfBinaryTree {

    private int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        minDepth0(root, 0);
        return minDepth;
    }

    public void minDepth0(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth >= minDepth) {
            return;
        }

        if (root.left == null && root.right == null) {
            minDepth = Math.min(minDepth, depth + 1);
            return;
        }

        minDepth0(root.left, depth + 1);
        minDepth0(root.right, depth + 1);

    }
}
