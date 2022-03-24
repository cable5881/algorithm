package com.lqb.offer;

import com.lqb.util.TreeNode;
import org.junit.Test;

public class ReconstructBTree {

    @Test
    public void test() {
        //正常
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode node = reConstructBinaryTree2(pre, in);
        TreeNode.preOrderRecursively(node);

        //只有左子树
        pre = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        in = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        node = reConstructBinaryTree2(pre, in);
        inorder(node);

        //只有右子树
        pre = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        in = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        node = reConstructBinaryTree2(pre, in);
        inorder(node);

        //左右子树不匹配
        pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        in = new int[]{9, 7, 2, 1, 5, 3, 8, 6};
        node = reConstructBinaryTree2(pre, in);
        inorder(node);
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        if (pre.length == 0 || pre == null || in.length == 0 || in == null) {
            return null;
        }

        return constructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public static TreeNode constructBinaryTree(int[] pre, int preStart, int preEnd,
                                               int[] in, int inStart, int inEnd) {

        TreeNode root;
        int rootStart = inStart;

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        //在中序遍历中找到根节点的值
        while (inStart < inEnd && pre[preStart] != in[rootStart] && rootStart <= preEnd) {
            rootStart++;
        }

        if (inStart <= inEnd && pre[preStart] == in[rootStart]) {
            root = new TreeNode(pre[preStart]);
        } else {
            return null;
        }

        int newPreEnd = rootStart - inStart + preStart;
        int newInEnd = rootStart - 1;

        root.left = constructBinaryTree(pre, preStart + 1, newPreEnd, in, inStart, newInEnd);
        root.right = constructBinaryTree(pre, newPreEnd + 1, preEnd, in, rootStart + 1, inEnd);

        return root;
    }

    private void inorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inorder(treeNode.left);
        System.out.println(treeNode.val);
        inorder(treeNode.right);
    }

    public TreeNode reConstructBinaryTree2(int[] pre, int[] in) {

        if (pre.length == 0 || pre == null || in.length == 0 || in == null) {
            return null;
        }

        return reConstructBinaryTree2(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //pre[1,2,4,7,3,5,6,8], vin[4,7,2,1,5,3,8,6]
    private TreeNode reConstructBinaryTree2(int[] pre, int preStart, int preEnd,
                                           int[] vin, int vinStart, int vinEnd) {
        if (preStart > preEnd || vinStart > vinEnd || preEnd >= pre.length || vinEnd > vin.length) {
            return null;
        }

        int rootVal = pre[preStart];
        int i = vinStart;
        for (; i <= vinEnd; i++) {
            if (vin[i] == rootVal) {
                break;
            }
        }
        if (i >= vin.length) {
            return null;
        }

        int cnt = i - vinStart;
        TreeNode root = new TreeNode(rootVal);

        int leftPreStart = preStart + 1;
        int leftPreEnd = preStart + cnt;
        int leftVinStart = vinStart;
        int leftVinEnd = i - 1;
        root.left = reConstructBinaryTree2(pre, leftPreStart, leftPreEnd, vin, leftVinStart, leftVinEnd);

        int rightPreStart = leftPreEnd + 1;
        int rightPreEnd = preEnd;
        int rightVinStart = i + 1;
        int rightVinEnd = vinEnd;
        root.right = reConstructBinaryTree2(pre, rightPreStart, rightPreEnd, vin, rightVinStart, rightVinEnd);

        return root;
    }
}

