package com.lqb.offer;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree {

    @Test
    public void test() {
        TreeNode m1 = new TreeNode(1);
        TreeNode m2 = new TreeNode(2);
        TreeNode m3 = new TreeNode(3);
        TreeNode m4 = new TreeNode(4);
        TreeNode m5 = new TreeNode(5);
        TreeNode m6 = new TreeNode(6);
        TreeNode m7 = new TreeNode(7);
        m1.left = m2;
        m1.right = m3;
        m2.left = m4;
        m2.right = m5;
        m3.left = m6;
        m3.right = m7;

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        System.out.println(hasSubtree2(m1, n1));
    }

    public boolean hasSubtree(TreeNode root1, TreeNode root2) {

        if (root1 == null || root2 == null) {
            return false;
        }

        boolean flag_left = true;
        boolean flag_right = true;

        if (root1.val == root2.val) {
            if (root2.left != null) {
                flag_left = hasSubtree(root1.left, root2.left);
            }
            if (root2.right != null) {
                flag_right = hasSubtree(root1.right, root2.right);
            }

            if (flag_left && flag_right) {
                return true;
            }
        }

        flag_left = hasSubtree(root1.left, root2);
        flag_right = hasSubtree(root1.right, root2);

        if (flag_left || flag_right) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 多年后再次试试，写的更加易懂一些了
     * 20200216
     */
    public boolean hasSubtree2(TreeNode root1, TreeNode root2) {
        //注意题目的条件，空树不是任意一个树的子结构。
        // 所以任意一个为null都是不允许的
        if (root1 == null || root2 == null) {
            return false;
        }

        return hasSubtree2Core(root1, root2) ? true
                : hasSubtree2(root1.left, root2) || hasSubtree2(root1.right, root2);
    }

    public boolean hasSubtree2Core(TreeNode root1, TreeNode root2) {
        //root2已经为null了，说明root2的结构已经遍历完成了
        if (root2 == null) {
            return true;
        }

        //root2都不是null，root1反而是null了，当然返回false
        if (root1 == null) {
            return false;
        }

        return root1.val == root2.val ?
                hasSubtree2Core(root1.left, root2.left)
                        && hasSubtree2Core(root1.right, root2.right) : false;
    }
}