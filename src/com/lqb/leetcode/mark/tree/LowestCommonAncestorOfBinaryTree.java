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
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        recurseTree(root, p, q);
        return ans;
    }

    private TreeNode ans;

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        if (currentNode == null) {
            return false;
        }

        int left = recurseTree(currentNode.left, p, q) ? 1 : 0;
        int right = recurseTree(currentNode.right, p, q) ? 1 : 0;

        //mid == 1表示找到了其中之一
        int mid = (currentNode.val == p.val || currentNode.val == q.val) ? 1 : 0;
        //这里可能有几种情况
        //    1
        //  2   3
        // 4 5 6 7
        //2，有可能是left为0，right为1，然后mid为1，如p为1，q为3，当前为1
        //2，有可能是left为1，right为1，然后mid为0，表示两个节点都在当前节点的子节点中找到了，如p为2，q为3，当前为1
        //1，有可能是left为1，right为0，然后mid为0，表示两个节点都在当前节点的左节点的子节点中找到了，如p为4，q为5，当前为1
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        //结果可能是0,2,3，只要是大于0表示至少找到了其中之一
        return (mid + left + right > 0);
    }

}
