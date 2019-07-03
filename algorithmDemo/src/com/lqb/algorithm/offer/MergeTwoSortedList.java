package com.lqb.algorithm.offer;

import com.lqb.util.ListNode;

public class MergeTwoSortedList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		
		ListNode q1 = new ListNode(2);
		ListNode q2 = new ListNode(4);
		ListNode q3 = new ListNode(6);
		ListNode q4 = new ListNode(8);
		ListNode q5 = new ListNode(10);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		q1.next = q2;
		q2.next = q3;
		q3.next = q4;
		q4.next = q5;
		
//		ListNode node = merge(n1, q1);
//		ListNode node = merge(null, q1);
		ListNode node = merge(n1, null);
		node.printAll();
	}

	public static ListNode merge(ListNode list1, ListNode list2) {
		if (list1 == null && list2 == null) {
			return null;
		} else if (list1 == null) {
			return list2;
		} else if (list2 == null) {
			return list1;
		}

		ListNode pHead = null;
		ListNode pList1 = list1;
		ListNode pList2 = list2;

		if (list1.val > list2.val) {
			pHead = list2;
			pList2 = list2.next;
		} else {
			pHead = list1;
			pList1 = list1.next;
		}

		pHead.next =  merge(pList1, pList2);
		
		return pHead;
	}

}
