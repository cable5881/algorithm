package com.lqb.ctci;

import com.lqb.util.ListNode;

/**
 * 编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前 给定一个链表的头指针 ListNode*
 * pHead，请返回重新排列后的链表的头指针。 注意：分割以后保持原来的数据顺序不变。
 * 
 * @Author:JackBauer
 * @Date:2016年7月26日
 */
public class Partition {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(5);
		ListNode n4 = new ListNode(7);
		ListNode n5 = new ListNode(9);
		ListNode n6 = new ListNode(2);
		ListNode n7 = new ListNode(4);
		ListNode n8 = new ListNode(6);
		ListNode n9 = new ListNode(8);
		ListNode n10 = new ListNode(10);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n10;

		Partition test = new Partition();
		ListNode node = test.partition(n1, 5);
		node.printAll();
	}

	public ListNode partition(ListNode pHead, int x) {
		if(pHead == null){
			return null;
		}
		
		ListNode p = pHead;
		ListNode head = null;
		ListNode xLeft = null;
		ListNode xRight = null;
		ListNode leftHead = null;
		ListNode rightHead = null;
		
		while(p != null){
			ListNode nodeL = new ListNode(p.val);
			if(nodeL.val < x){
				if(xLeft == null){
					xLeft = nodeL;
					leftHead = nodeL;
				}else{
					xLeft.next = nodeL;
					xLeft = nodeL;
				}
			}else{
				if(xRight == null){
					xRight = nodeL;
					rightHead = nodeL;
				}else{
					xRight.next = nodeL;
					xRight = nodeL;
				}
			}
			
			p = p.next;
		}
		
		if(xLeft != null){
			xLeft.next = rightHead;
			head = leftHead;
		}else{
			head = rightHead;
		}
		
		return head;
	}

}
