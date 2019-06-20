package com.lqb.leetcode;

import com.lqb.algorithm.offer.domain.ListNode;
import org.junit.Test;

/**
 * @Description TODO
 * @Author liqibo
 * @Date 2019/6/18 17:33
 **/
public class ReverseKGroup {

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

        ReverseKGroup demo = new ReverseKGroup();
        ListNode head = demo.reverseKGroup(node1, 3);
        head.printAll();
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 1) {
            return head;
        }

        ListNode node = head;
        ListNode last = head;

        //先获取头节点
        int tempK = k;
        while (tempK > 0 && node != null) {
            node = node.next;
            tempK--;
        }
        if (tempK > 0) {
            return head;
        }
        head = reverse(head, k);

        boolean reverse = true;
        while (true) {
            tempK = k;
            ListNode tempHead = node;

            while (tempK > 0) {
                if (node == null && tempK > 0) {
                    last.next = tempHead;
                    last = node;
                    reverse = false;
                    break;
                }
                node = node.next;
                tempK--;
            }

            if (reverse) {
                last.next = reverse(tempHead, k);
                last = tempHead;
            } else {
                break;
            }

            if (node == null) {
                break;
            }
        }

        if (last != null) {
            last.next = null;
        }

        return head;
    }

    private ListNode reverse(ListNode head, int k) {

        ListNode last = head;
        ListNode node;
        if (head.next != null) {
            node = head.next;
        } else {
            return head;
        }

        ListNode next = node.next;

        //处理尾节点
        node.next = last;
        last = node;
        node = next;
        k--;

        while (k > 1 && node != null) {
            next = node.next;
            node.next = last;
            last = node;
            node = next;
            k--;
        }

        return  last;
    }

}
