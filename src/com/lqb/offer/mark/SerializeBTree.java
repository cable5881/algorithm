package com.lqb.offer.mark;

import com.lqb.util.TreeNode;

/**
 * @Description:序列化和反序列化二叉树
 * @Author:JackBauer
 * @Date:2016年7月13日 下午2:59:08
 */
public class SerializeBTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
//		TreeNode t7 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t3.left = t5;
        t3.right = t6;

//		TreeNode.preOrderRecursively(t1);
        SerializeBTree test = new SerializeBTree();
        String serlizedStr = test.serialize(t1);
        System.out.println(serlizedStr);
        TreeNode root = test.deserialize(serlizedStr);
        TreeNode.preOrderRecursively(root);
    }

    String serialize(TreeNode root) {
        if (root == null) {
            return "$";
        }

        StringBuilder sb = new StringBuilder(String.valueOf(root.val));
        serialize(root.left, sb);
        serialize(root.right, sb);

        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(",$");
            return;
        }

        sb.append("," + node.val);
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    TreeNode deserialize(String str) {
        if (str == null || str.equals("") || str.equals("$") || str.equals(",")) {
            return null;
        }

        return deserialize0(str);
    }

    int index = 0;

    private TreeNode deserialize0(String str) {
        if (index >= str.length()) {
            return null;
        }

        if (str.charAt(index) == '$') {
            index += 2;
            return deserialize0(str);
        }

        //假设数字只占一位
        TreeNode root = new TreeNode(Integer.valueOf(str.charAt(index) + ""));
        index += 2;

        root.left = deserialize0(str);
        root.right = deserialize0(str);

        return root;
    }
}
