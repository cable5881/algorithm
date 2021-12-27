package com.lqb.leetcode.mark.tree;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * @author liqibo
 * @description 二叉树的最近公共祖先
 * @date 2020/3/6 15:26
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 **/
public class LowestCommonAncestorOfBinaryTree {

    @Test
    public void test() {
        TreeNode fullTree = TreeNode.getFullTree();
        //          1
        //         / \
        //       2     3
        //      / \   /  \
        //    4    5  6   7
        System.out.println(lowestCommonAncestor2(fullTree, new TreeNode(2), new TreeNode(3)).val);//1
        System.out.println(lowestCommonAncestor2(fullTree, new TreeNode(1), new TreeNode(7)).val);//1
        System.out.println(lowestCommonAncestor2(fullTree, new TreeNode(4), new TreeNode(5)).val);//2
        System.out.println(lowestCommonAncestor2(fullTree, new TreeNode(4), new TreeNode(7)).val);//1
    }

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {

        if (node == null) {
            return null;
        }

        boolean leftExistForP = exist(node.left, p);
        boolean leftExistForQ = exist(node.left, q);
        if (leftExistForP && leftExistForQ) {
            return lowestCommonAncestor(node.left, p, q);
        }

        boolean rightExistForP = exist(node.right, p);
        boolean rightExistForQ = exist(node.right, q);
        if (rightExistForP && rightExistForQ) {
            return lowestCommonAncestor(node.right, p, q);
        }

        return node;
    }

    private boolean exist(TreeNode node, TreeNode p) {
        if (node == null) {
            return false;
        }

        if (node.val == p.val) {
            return true;
        }

        return exist(node.left, p) || exist(node.right, p);
    }

    /**
     * @author liqibo
     * @date 2020/3/6 16:11
     * @description 官方解法
     *       1
     *      / \
     *    2     3
     *   / \   /  \
     * 4    5  6   7
     * 需要找到一个规律，对于一个节点：
     * 1.如果左子树找到了任意其一，右子树一个都没找到，则肯定公共祖先肯定在左子树上
     * 2.如果右子树找到了任意其一，左子树一个都没找到，则肯定公共祖先肯定在右子树上
     * 3.如果左右子树都找到了任意其一，则肯定公共祖先肯定在当前节点
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        //关键点，找到了任意其一直接返回
        if (root == p || root == q) {
            return root;
        }

        TreeNode leftAncestor = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor2(root.right, p, q);
        if (leftAncestor != null && rightAncestor != null) {
            return root;
        } else if (leftAncestor != null) {
            return leftAncestor;
        } else if (rightAncestor != null) {
            return rightAncestor;
        }

        return null;
    }

}
