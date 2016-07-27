package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.ListNode;

/**
 * ��һ������������У������ظ��Ľ�㣬��ɾ�����������ظ��Ľ�㣬�ظ��Ľ�㲻��������������ͷָ�롣 
 * ���磬����1->2->3->3->4->4->5 �����Ϊ 1->2->5 
 * @author:JackBauer
 * @date:2016��6��25�� ����4:38:22
 */
public class DeleteDuplication {

	public static void main(String[] args) {
		ListNode n0 = new ListNode(1);
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(5);
		
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		DeleteDuplication test = new DeleteDuplication();
		ListNode n = test.deleteDuplication(n6);
		n.printAll();
	}

	public ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null) {
			return null;
		}

		ListNode deleteEnd = null;

		//�ȴ���ͷ����ͬ�����
		while (pHead.next != null && pHead.next.val == pHead.val) {
			deleteEnd = getDeletedEnd(pHead);

			if (deleteEnd.next != null) {
				pHead = deleteEnd.next;
			} else {
				return null;
			}
		}

		ListNode pre = pHead;
		ListNode now = pHead.next;

		while (now != null) {
			deleteEnd = null;
			ListNode pNext = now.next;

			if (pNext != null && now.val == pNext.val) {
				deleteEnd = getDeletedEnd(now);
				pre.next = deleteEnd.next;
				now = deleteEnd.next;
			} else {
				pre = now;
				now = pNext;
			}
		}

		return pHead;
	}

	/**
	 * @Description: �ҵ���Ҫɾ�������һ���ڵ�
	 * @author:JackBauer
	 * @Date:2016��6��25������4:37:47
	 */
	private ListNode getDeletedEnd(ListNode node) {
		ListNode deleteEnd = node.next;

		while (deleteEnd.next != null && deleteEnd.next.val == node.val) {
			deleteEnd = deleteEnd.next;
		}

		return deleteEnd;
	}

}
