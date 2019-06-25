package com.lqb.leetcode.util;

public class TreeLinkNode {
    public int val;
    public TreeLinkNode left, right, next;
    public TreeLinkNode(int x) {
        val = x;
    }

    public void print() {
        TreeLinkNode f = this;
        TreeLinkNode c = this;
        while (f != null) {
            System.out.println(f.val);
            c = f;
            while (c.next != null) {
                c = c.next;
                System.out.println(c.val);
            }
            f = f.left;
        }
    }
}