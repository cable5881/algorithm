package com.lqb.leetcode;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 自己的思路：
 *  算法复杂度：O(n)，空间复杂度：O(n)
 *  ①递归中序遍历存入数组[3, 2, 4, 1, 4, 2, 3]
 *  ②判断数组是否对称
 *
 **/
public class SymmetricTree {

    @Test
    public void test() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        SymmetricTree demo = new SymmetricTree();
        System.out.println(demo.isSymmetric(t1));
    }

    @Test
    public void test2() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        //TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        //TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);

        t1.left = t2;
        t1.right = t3;
        //t2.left = t4;
        t2.right = t5;
        //t3.left = t6;
        t3.right = t7;

        SymmetricTree demo = new SymmetricTree();
        System.out.println(demo.isSymmetric(t1));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricCore(root, root);
    }

    public boolean isSymmetricCore(TreeNode leftTree, TreeNode rightTree) {

        if (leftTree == null && rightTree == null) {
            return true;
        }

        if (leftTree != null && rightTree != null) {
            if (leftTree.val == rightTree.val) {
                return isSymmetricCore(leftTree.right, rightTree.left)
                        && isSymmetricCore(leftTree.left, rightTree.right);
            }
        }

        return false;
    }

}
