package com.lqb.leetcode;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MergeTwoSortedLists {

    @Test
    public void test1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);

        n4.next = n5;
        n5.next = n6;

        mergeTwoLists(n1, n4).printAll();
    }

    public ListNode mergeTwoLists(ListNode<Integer> L1, ListNode<Integer> L2) {

        if (L1 == null) {
            return L2;
        }

        if (L2 == null) {
            return L1;
        }

        ListNode<Integer> head = new ListNode(0);
        ListNode<Integer> cur = head;

        while (L1 != null && L2 != null) {
            int L1V = L1.val;
            int L2V = L2.val;

            if (L1V < L2V) {
                cur.next = L1;
                cur = L1;
                L1 = L1.next;
            } else {
                cur.next = L2;
                cur = L2;
                L2 = L2.next;
            }
        }

        while (L1 != null) {
            cur.next = L1;
            cur = L1;
            L1 = L1.next;
        }

        while (L2 != null) {
            cur.next = L2;
            cur = L2;
            L2 = L2.next;
        }

        return head.next;
    }

}
