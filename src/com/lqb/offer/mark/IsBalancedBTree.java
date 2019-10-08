package com.lqb.offer.mark;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树
 *
 * @author:JackBauer
 * @date:2016年6月18日 下午8:02:01
 */
public class IsBalancedBTree {

    @Test
    public void test() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        TreeNode t8 = new TreeNode(6);
        TreeNode t9 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t2.right = t5;

        t3.left = t6;
        t3.right = t7;

        t4.left = t8;
        t4.right = t9;

        IsBalancedBTree test = new IsBalancedBTree();
        System.out.println(test.isBalanced(t1));
    }

    /**
     * @author liqibo
     * @date 2019/10/8 10:12
     * @description 暴力法，自己的解法，需要重复遍历每个节点，不够好
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left_level = getHeight(root.left);
        int right_level = getHeight(root.right);
        int delta = left_level - right_level;
        if (delta > 1 || delta < 1) {
            return false;
        }

        //需要有下面这一步，因为上面的代码只是判断了对于根节点，左右子树的最大深度相差不超过1
        //但是左子树下面的左右子树可能就超过1了，因此需要接着往下调用。
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left_level = getHeight(node.left);
        int right_level = getHeight(node.right);

        return left_level > right_level ? (left_level + 1) : (right_level + 1);
    }

    /**
     * @author liqibo
     * @date 2019/10/8 10:44
     * @description 最佳解法，后序遍历，自底向上累加深度，如果某一层深度相差超过1则返回-1
     */
    public boolean isBalanced2(TreeNode root) {
        return depth(root) > -1;
    }

    private int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left <= -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right <= -1) {
            return -1;
        }
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }


}
