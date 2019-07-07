package com.lqb.algorithm.offer;

import com.lqb.util.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共结点。 
 * @author:JackBauer
 * @date:2016年6月17日 下午8:10:24
 */
public class FindFirstCommonNodeOfTwoList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(7);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		ListNode n6 = new ListNode(4);
		ListNode n7 = new ListNode(5);
		
		n6.next = n7;
		n7.next = n4;
		
		ListNode n = FindFirstCommonNode(n1, n6);
		System.out.println(n.val);
	}

	public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

		if (pHead1 == null || pHead2 == null) {
			return null;
		}

		int length_pHead1 = getListLength(pHead1);
		int length_pHead2 = getListLength(pHead2);

		int step = 0;
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;

		if (length_pHead1 > length_pHead2) {
			step = length_pHead1 - length_pHead2;
			while( step > 0 ){
				p1 = p1.next;
				step--;
			}
		}else{
			step = length_pHead2 - length_pHead1;
			while( step > 0 ){
				p2 = p2.next;
			}
		}
		
		while( p1 != null ){
			if( p1 == p2){
				return p1;
			}else{
				p1 = p1.next;
				p2 = p2.next;
			}
		}

		return null;
	}

	private static int getListLength(ListNode node) {
		int length = 0;
		ListNode p = node;

		while (p != null) {
			length++;
			p = p.next;
		}

		return length;
	}

}
