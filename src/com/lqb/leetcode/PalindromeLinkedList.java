package com.lqb.leetcode;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PalindromeLinkedList {

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(isPalindrome(n1));
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(isPalindrome(n1));
    }

    @Test
    public void test3() {
        ListNode n1 = new ListNode(1);
        System.out.println(isPalindrome(n1));
    }

    @Test
    public void test4() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);

        n1.next = n2;
        System.out.println(isPalindrome(n1));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
            len++;
        }

        p = head;
        boolean flag = len % 2 == 0;
        for (int i = flag ? 1 : 0; i < len / 2; i++) {
            p = p.next;
        }

        if (flag) {
            ListNode mid = new ListNode(0);
            mid.next = p.next;
            p.next = mid;
            p = mid;
        }

        ListNode last = p;
        p = p.next;
        while (p != null) {
            ListNode temp = p.next;
            p.next = last;
            last = p;
            p = temp;
        }

        ListNode right = last;
        ListNode left = head;

        while (left != right) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }
}
