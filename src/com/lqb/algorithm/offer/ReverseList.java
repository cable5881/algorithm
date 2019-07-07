package com.lqb.algorithm.offer;

import com.lqb.util.ListNode;

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
		//娉ㄦ剰灏捐妭鐐圭殑next瑕佽缃负null锛屽惁鍒欎細杩涘叆姝诲惊鐜紝鍥犱负灏捐妭鐐瑰師涓洪鑺傜偣锛宯ext骞朵笉涓簄ull
		
		while( pNext != null ){
			pTemp = pNext.next;
			pNext.next = pNow;
			pNow = pNext;
			pNext = pTemp;
		}
		
		return pNow;
    }

}
