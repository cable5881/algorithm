package com.lqb.offer;

import com.lqb.util.ListNode;

public class FindKthToTail {

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
		
//		ListNode p = findKthToTail(n1, 2);
		ListNode p = findKthToTail(n1, 6);
		System.out.println(p.val);
	}
	
	public static ListNode findKthToTail(ListNode head,int k) {
		
		if( head == null || k <= 0 ){
			return null;
		}
		
		ListNode p1 = head;
		ListNode p2 = null;
		int i = 1;
		
		while( p1 != null && i < k ){
			p1 = p1.next;
			i++;
		}
		
		if( p1 == null && i <= k ){
			return null;
		}
		
		p2 = head;
		
		while( (p1 = p1.next) != null ){
			p2 = p2.next;
		}
		
		
		return p2;
    }
}






