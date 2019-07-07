package com.lqb.leetcode;

import com.lqb.util.TreeLinkNode;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set toNULL.
 * Initially, all next pointers are set toNULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 1
 /  \
 2    3
 / \  / \
 4  5  6  7

 * After calling your function, the tree should look like:

 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \  / \
 4->5->6->7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode {

    public static void populateNextPointer(TreeLinkNode root) {
        if(root == null) {
            return;
        }

        root.next = null;
        TreeLinkNode first = root;
        TreeLinkNode cur;
        TreeLinkNode last;

        while (first.left != null) {
            first.left= first.right;
            cur = first;
            while (cur.next != null) {
                last = cur;
                cur = cur.next;
                last.right.next = cur.left;
                cur.left.next = cur.right;
            }
            cur.right.next = null;
            first = first.left;
        }
    }

    TreeLinkNode root;

    @Before
    public void before() {
        TreeLinkNode t1 = new TreeLinkNode(1);
        TreeLinkNode t2 = new TreeLinkNode(2);
        TreeLinkNode t3 = new TreeLinkNode(3);
        TreeLinkNode t4 = new TreeLinkNode(4);
        TreeLinkNode t5 = new TreeLinkNode(5);
        TreeLinkNode t6 = new TreeLinkNode(6);
        TreeLinkNode t7 = new TreeLinkNode(7);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t2.right = t5;

        t3.left = t6;
        t3.right = t7;

        root = t1;
    }

    @Test
    public void test1() {
        populateNextPointer(root);
    }
}
