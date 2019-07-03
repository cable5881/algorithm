package com.lqb.leetcode;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RemoveNthNodeFromEndOfList {

    @Test
    public void test() {
        ListNode n1 = getListNode();
        RemoveNthNodeFromEndOfList demo = new RemoveNthNodeFromEndOfList();
        demo.removeNthFromEnd(n1, 1).printAll();
    }

    @Test
    public void test1() {
        ListNode n1 = getListNode();
        RemoveNthNodeFromEndOfList demo = new RemoveNthNodeFromEndOfList();
        demo.removeNthFromEnd(n1, 3).printAll();
    }

    @Test
    public void test2() {
        ListNode n1 = getListNode();
        RemoveNthNodeFromEndOfList demo = new RemoveNthNodeFromEndOfList();
        demo.removeNthFromEnd(n1, 6).printAll();
    }

    private ListNode getListNode() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        return n1;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n < 1) {
            return head;
        }

        ListNode fastNode = head;

        for (int i = n; i > 0; i--) {
            if (fastNode != null) {
                fastNode = fastNode.next;
            } else {
                return null;
            }
        }

        ListNode slowNode = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode lastNode = dummy;

        while (fastNode != null) {
            lastNode = slowNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }

        lastNode.next = slowNode.next;

        return dummy.next;
    }




}
