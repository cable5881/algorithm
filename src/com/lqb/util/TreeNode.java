package com.lqb.util;

import java.util.LinkedList;

public class TreeNode {

    public int val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 前序遍历（递归）
     */
    public static void preOrderRecursively(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.print("#" + treeNode.val);
        preOrderRecursively(treeNode.left);
        preOrderRecursively(treeNode.right);
    }

    /**
     * 中序遍历（递归）
     */
    public static void inOrderRecursively(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrderRecursively(treeNode.left);
        System.out.print("#" + treeNode.val);
        inOrderRecursively(treeNode.right);
    }

    /**
     * 后序遍历（递归）
     */
    public static void postOrderRecursively(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }

        postOrderRecursively(treeNode.left);
        postOrderRecursively(treeNode.right);
        System.out.print("#" + treeNode.val);
    }

    /**
     * 前序遍历（迭代）
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        LinkedList<TreeNode> s = new LinkedList<>();

        while (node != null || !s.isEmpty()) {
            //两种方法都可以

            //if (node != null) {
            //    System.out.print("#" + node.val);
            //    s.push(node.right);
            //    node = node.left;
            //} else {
            //    node = s.pop();
            //}

            if (node == null) {
                node = s.pop();
            }
            System.out.print("#" + node.val);
            if (node.right != null) {
                s.push(node.right);
            }
            node = node.left;
        }
    }

    /**
     * 中序遍历（迭代）
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        LinkedList<TreeNode> s = new LinkedList<>();

        while (node != null || !s.isEmpty()) {
            if (node != null) {
                s.push(node);
                node = node.left;
                continue;
            }

            node = s.pop();
            System.out.print("#" + node.val);
            node = node.right;
        }
    }

    /**
     * 后序遍历（迭代）
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        //需要通过一个set来区分是否访问过右子树，下面还有更好的
        //LinkedList<TreeNode> s = new LinkedList<>();
        //HashSet<TreeNode> visit = new HashSet<>();
        //while (node != null || !s.isEmpty()) {
        //    if (node != null) {
        //        s.push(node);
        //        node = node.left;
        //        continue;
        //    }
        //
        //    node = s.peekFirst();
        //    if (node.right != null && !visit.contains(node)) {
        //        visit.add(node);
        //        node = node.right;
        //    } else {
        //        s.pop();
        //        System.out.print("#" + node.val);
        //        node = null;
        //    }
        //}

        LinkedList<TreeNode> s = new LinkedList<>();
        //记录上一个节点(这个是关键)
        TreeNode last = null;

        //先收集所有最左节点
        while (node != null) {
            s.push(node);
            last = node;
            node = node.left;
        }

        while (!s.isEmpty()) {
            node = s.peek();
            //此时队列中的每一个节点都可以认为是父节点（可能有右孩子），如果右孩子不为空当然要先访问右孩子
            if (node.right != null && node.right != last) {
                node = node.right;
                //开始添加右子树到栈中
                while (node != null) {
                    s.push(node);
                    last = node;
                    node = node.left;
                }
            } else {
                last = s.pop();
                System.out.print("#" + node.val);
            }
        }
    }

    public static TreeNode getFullTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        //TreeNode t8 = new TreeNode(8);
        //TreeNode t9 = new TreeNode(9);
        //TreeNode t10 = new TreeNode(10);
        //TreeNode t11 = new TreeNode(11);
        //TreeNode t12 = new TreeNode(12);
        //TreeNode t13 = new TreeNode(13);
        //TreeNode t14 = new TreeNode(14);
        //TreeNode t15 = new TreeNode(15);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        //t4.left = t8;
        //t4.right = t9;
        //t5.left = t10;
        //t5.right = t11;
        //t6.left = t12;
        //t6.right = t13;
        //t7.left = t14;
        //t7.right = t15;

        return t1;
    }

    public static TreeNode getBalanceTree() {
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

        return t1;
    }

    /**
     * @Description: print current node's value
     * @Author:JackBauer
     * @Date:2016年7月2日下午3:17:08
     */
    public void print() {
        System.out.print(val);
    }

    public static void main(String[] args) {
        TreeNode node = getFullTree();
        preOrderRecursively(node);
        System.out.println();
        System.out.println("###########");
        inOrderRecursively(node);
        System.out.println();
        System.out.println("###########");
        postOrderRecursively(node);

        System.out.println();
        System.out.println();
        System.out.println("###########");
        preOrder(node);
        System.out.println();
        System.out.println("###########");
        inOrder(node);
        System.out.println();
        System.out.println("###########");
        postOrder(node);

    }
}
