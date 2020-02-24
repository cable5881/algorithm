package com.lqb.offer;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *           8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *
 *     	镜像二叉树
 *     	     8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class MirrorTree {

    @Test
    public void main() {
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

		mirror2(t1);

		TreeNode.inOrder(t1);

    }

    public void mirror(TreeNode root) {

        if (root == null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 多年后重新写的，20200216
     */
    public void mirror2(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode oldLeft = root.left;
        TreeNode oldRight = root.right;

        root.left = oldRight;
        root.right = oldLeft;

        mirror2(root.left);
        mirror2(root.right);
    }

}
