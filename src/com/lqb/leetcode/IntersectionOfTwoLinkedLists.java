package com.lqb.leetcode;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class IntersectionOfTwoLinkedLists {

    /**
     * 方法一：算出A和B的长度（假设A比B长N），然后A先走N步，接着对比A和B的每一个节点是否相同
     * 方法二（官方）：创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B);
     * 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     *
     **/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        int lengthA = 0;
        while (curA != null) {
            lengthA++;
            curA = curA.next;
        }

        ListNode curB = headB;
        int lengthB = 0;
        while (curB != null) {
            lengthB++;
            curB = curB.next;
        }

        ListNode longer;
        ListNode shorter;
        if (lengthA > lengthB) {
            longer = headA;
            shorter = headB;
        } else {
            longer = headB;
            shorter = headA;
        }

        int forward = Math.abs(lengthA - lengthB);
        while (forward > 0) {
            longer = longer.next;
            forward--;
        }

        while (shorter != null) {
            if (shorter == longer) {
                return shorter;
            }

            shorter = shorter.next;
            longer = longer.next;
        }

        return null;
    }

    @Test
    public void test1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode n11 = new ListNode(1);
        n11.next = n3;

        System.out.println(getIntersectionNode(n1 ,n11).val);
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode n11 = new ListNode(1);
        ListNode n22 = new ListNode(2);
        n11.next = n22;

        System.out.println(getIntersectionNode(n1 ,n11));
    }
}
