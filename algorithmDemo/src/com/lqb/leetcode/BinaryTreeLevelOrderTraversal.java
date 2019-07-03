package com.lqb.leetcode;

import com.lqb.util.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BinaryTreeLevelOrderTraversal {

    @Test
    public void test() {
        TreeNode root = TreeNode.getFullTree();
        BinaryTreeLevelOrderTraversal demo = new BinaryTreeLevelOrderTraversal();
        System.out.println(demo.levelOrder2(root));
    }

    /**
     * @description 使用了两个队列，还可以优化成一个队列
     * @author liqibo
     * @date 2019/7/3 11:29 
     **/
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> levelOrder = new ArrayList<>();

        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<TreeNode> q2 = new LinkedList<>();
        q1.add(root);

        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (!q1.isEmpty()) {
                addToLevelOrder(q1, q2, levelOrder);
            } else{
                addToLevelOrder(q2, q1, levelOrder);
            }
        }

        return levelOrder;
    }

    private void addToLevelOrder(LinkedList<TreeNode> curLevel, LinkedList<TreeNode> nextLevel, List<List<Integer>> levelOrder) {
        List<Integer> level = new ArrayList<>(curLevel.size());
        while (!curLevel.isEmpty()) {
            TreeNode node = curLevel.removeFirst();
            level.add(node.val);
            if (node.left != null) {
                nextLevel.addLast(node.left);
            }

            if (node.right != null) {
                nextLevel.addLast(node.right);
            }
        }

        levelOrder.add(level);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> levelOrder = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>();
            levelOrder.add(levelNodes);
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.removeFirst();
                levelNodes.add(node.val);

                if (node.left != null) {
                    q.addLast(node.left);
                }

                if (node.right != null) {
                    q.addLast(node.right);
                }
            }
        }

        return levelOrder;
    }

}
