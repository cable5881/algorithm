package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.ListNode;

public class ReverseList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		ListNode p = reverseList(n1);
		p.printAll();

	}
	
	public static ListNode reverseList(ListNode head) {
		
		if( head == null ){
			return null;
		}
		
		ListNode pNow = head;
		ListNode pTemp;
		ListNode pNext = head.next;
		
		head.next = null;
		//注意尾节点的next要设置为null，否则会进入死循环，因为尾节点原为首节点，next并不为null
		
		while( pNext != null ){
			pTemp = pNext.next;
			pNext.next = pNow;
			pNow = pNext;
			pNext = pTemp;
		}
		
		return pNow;
    }

}
