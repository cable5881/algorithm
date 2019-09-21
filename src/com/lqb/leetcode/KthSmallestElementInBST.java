package com.lqb.leetcode;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallestElementInBST {

    @Test
    public void test() {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(1);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t6;
        KthSmallestElementInBST demo = new KthSmallestElementInBST();
        System.out.println(demo.kthSmallest(t1, 3));
    }

    @Test
    public void test2() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(2);

        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        KthSmallestElementInBST demo = new KthSmallestElementInBST();
        System.out.println(demo.kthSmallest(t1, 1));
    }

    int num;
    int kth;

    public int kthSmallest(TreeNode root, int k) {
        num = k;
        kthSmallestCore(root);
        return kth;
    }

    private void kthSmallestCore(TreeNode root) {
        if (root == null) {
            return;
        }

        kthSmallestCore(root.left);
        if (--num == 0) {
            kth = root.val;
            return;
        }
        kthSmallestCore(root.right);
    }


}
