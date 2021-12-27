package com.lqb.leetcode.mark.tree;

import com.lqb.util.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ValidateBinarySearchTree {

    /**
     * 中序遍历（迭代法），一旦遇到不符合规则的节点则迭代中止
     */
    public boolean isValidBST0(TreeNode root) {
        if(root == null) {
            return true;
        }

        LinkedList<TreeNode> s = new LinkedList<>();
        TreeNode node = root;
        TreeNode last = null;
        s.addFirst(node);
        // 但这个中序遍历迭代太复杂了, 有简单的中序遍历的迭代法
        while (!s.isEmpty()) {
            while (node != null && node.left != null) {
                node = node.left;
                s.addLast(node);
            }
            node = s.pollLast();
            if (last != null && last.val >= node.val) {
                return false;
            }
            last = node;
            System.out.print("#"+node.val);
            if (node.right != null) {
                node = node.right;
                s.addLast(node);
            } else {
                //这个node置位空很容易忘记
                node = null;
            }
        }

        //简单的中序遍历的迭代法
        // LinkedList<TreeNode> s = new LinkedList<>();
        // TreeNode node = root;
        // TreeNode last = null;
        while (!s.isEmpty() || node != null) {
            if (node != null) {
                s.push(node);
                node = node.left;
                continue;
            }
            node = s.pop();
            if (last != null && last.val >= node.val) {
                return false;
            }
            last = node;
            //这里不能有判断，否则node的值还是last无法改变。
            // if (node.right != null) {
            //     node = node.right;
            // }
            node = node.right;
        }

        return true;
    }

    TreeNode lastNode = null;
    /**
     * 上一个数永远比下一个遍历的数大，因此只要保存上一个数就行了
     */
    public boolean isValidBST00(TreeNode root) {
        if(root == null) {
            return true;
        }

        boolean isLeftBST = isValidBST00(root.left);
        if (!isLeftBST) {
            return false;
        }

        if (lastNode != null && lastNode.val >= root.val) {
            return false;
        }

        lastNode = root;

        return isValidBST00(root.right);
    }

    /**
     * @description 错误解法：第一代和第三代的大小没有判断到
     * @author liqibo
     * @date 2019/7/11 21:10
     **/
    public boolean isValidBST_Wrong_1(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.val < root.left.val) {
            return false;
        }

        if (root.right != null && root.val > root.right.val) {
            return false;
        }

        return isValidBST_Wrong_1(root.left) && isValidBST_Wrong_1(root.right);
    }


    /**
     * @description 解法二：中序遍历保存遍历的值到集合中，再遍历集合
     * @author liqibo
     * @date 2019/7/12 9:43
     **/
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<Integer> visits = new ArrayList<>();
        isValidBST2(root, visits);

        for (int i = 1; i < visits.size(); i++) {
            if (visits.get(i) <= visits.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    private void isValidBST2(TreeNode root, List<Integer> visits) {
        if (root == null) {
            return;
        }

        isValidBST2(root.left, visits);
        visits.add(root.val);
        isValidBST2(root.right, visits);
    }

    @Test
    public void test() {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        System.out.println(demo.isValidBST0(t1));
    }

    @Test
    public void test2() {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(1);
        TreeNode t7 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        System.out.println(demo.isValidBST0(t1));
        System.out.println(new ValidateBinarySearchTree().isValidBST0(new TreeNode(Integer.MIN_VALUE)));
    }

    @Test
    public void test3() {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);

        t1.right = t2;

        System.out.println(demo.isValidBST0(t1));
    }

    @Test
    public void test4() {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode t1 = new TreeNode(-2147483648);
        TreeNode t3 = new TreeNode(2147483647);
        t1.right = t3;

        System.out.println(demo.isValidBST0(t1));
    }

    @Test
    public void test5() {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode t1 = new TreeNode(2147483647);
        TreeNode t3 = new TreeNode(-2147483648);
        t1.left = t3;

        System.out.println(demo.isValidBST0(t1));
    }

    @Test
    public void test6() {
        TreeNode t1 = new TreeNode(34);
        TreeNode t2 = new TreeNode(-6);
        TreeNode t3 = new TreeNode(-21);

        t1.left = t2;
        t2.left = t3;

        System.out.println(isValidBST0(t1));
    }
}
