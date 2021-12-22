package com.lqb.offer;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 *
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 *
 * 示例1
 * 输入：{1,2,3,4,5},2
 * 返回值：{4,5}
 * 说明：返回倒数第2个节点4，系统会打印后面所有的节点来比较。
 *
 * 示例2
 * 输入：{2},8
 * 返回值：{}
 *
 */
public class FindKthToTail {

    @Test
    public void main() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(findKthToTail2(n1, 1));
        System.out.println(findKthToTail2(n1, 2));
        System.out.println(findKthToTail2(n1, 3));
        System.out.println(findKthToTail2(n1, 4));
        System.out.println(findKthToTail2(n1, 5));
        System.out.println(findKthToTail2(n1, 6));
    }

	/**
	 * 自己的解法，没想到快慢指针
	 */
    public ListNode findKthToTail(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        if (length < k) {
            return null;
        }

        int stop = length - k;
        cur = head;
        for (int i = 0; i < stop; i++) {
            cur = cur.next;
        }
        return cur;
    }

	/**
	 * 快慢指针
	 */
	public ListNode findKthToTail2(ListNode head, int k) {

		if (head == null) {
			return null;
		}

		ListNode p1 = head;
		for (int i = 0; i < k; i++) {
			if (p1 == null) {
				return null;
			}
			p1 = p1.next;
		}

		ListNode p2 = head;
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}






