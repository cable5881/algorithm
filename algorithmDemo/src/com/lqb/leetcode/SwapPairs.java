package com.lqb.leetcode;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SwapPairs {

    @Test
    public void test() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        SwapPairs demo = new SwapPairs();
        ListNode head = demo.swapPairs(node1);
        head.printAll();
    }


    public ListNode swapPairs(ListNode head) {

        ListNode node = head;
        ListNode last = null;

        if (node != null && node.next != null) {
            ListNode one = node;
            ListNode two = node.next;
            ListNode three = two.next;

            two.next = one;
            one.next = three;

            head = two;
            last = one;
            node = three;
        }

        while (node != null && node.next != null) {
            ListNode one = node;
            ListNode two = node.next;
            ListNode three = two.next;

            last.next = two;
            two.next = one;
            one.next = three;
            node = three;
            last = one;
        }

        return head;
    }


}
