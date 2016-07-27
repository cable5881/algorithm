package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.ListNode;

/**
 * һ�������а����������ҳ�������Ļ�����ڽ�㡣
 * 
 * @author:JackBauer
 * @date:2016��6��25�� ����9:40:31
 */
public class EntryNodeOfLoop {

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
		n5.next = n3;

		ListNode node = entryNodeOfLoop(n1);
		System.out.println(node.val);
	}
	
	public static ListNode entryNodeOfLoop(ListNode pHead){
		if(pHead == null){
			return null;
		}
		
		ListNode loopNode = getNodeOfLoop(pHead);
		if(loopNode == null){
			return null;
		}
		
		int loopLength = getLengthOfLoop(loopNode);
		ListNode pFast = getKthNode(pHead, loopLength);
		ListNode pSlow = pHead;
		
		while(pSlow != pFast){
			pSlow = pSlow.next;
			pFast = pFast.next;
		}
		
		return pSlow;
	}

	public static ListNode getNodeOfLoop(ListNode pHead) {
		if (pHead == null) {
			return null;
		}

		ListNode pSlow = pHead;
		ListNode pFast = pHead.next;

		while (pFast != null && pFast.next != null) {
			if (pFast == pSlow || pFast.next == pSlow) {
				return pSlow;
			}
			pSlow = pSlow.next;
			pFast = pFast.next.next;
		}

		return null;
	}

	public static int getLengthOfLoop(ListNode loopHead) {
		ListNode p = loopHead.next;
		int length = 1;
		
		while(p != loopHead){
			length++;
			p = p.next;
		}
		
		return length;
	}

	public static ListNode getKthNode(ListNode pHead, int k){
		ListNode p = pHead;
		int i = 0;
		while(i < k){
			p = p.next;
			i++;
		}
		
		return p;
	}
}
