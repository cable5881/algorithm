package com.lqb.algorithm.cracking_the_coding_interview;

import com.lqb.algorithm.offer.domain.ListNode;

/**
 * 有两个用链表表示的整数，每个结点包含一个数位。 这些数位是反向存放的，也就是个位排在链表的首部。 编写函数对这两个整数求和，并用链表形式返回结果。
 * 给定两个链表ListNode* A，ListNode* B，请返回A+B的结果(ListNode*)。 测试样例：
 * 
 * {1,2,3},{3,2,1}
 * 
 * 返回：{4,4,4}
 * 
 * @Author:JackBauer
 * @Date:2016年7月27日
 */
public class Plus {

	public static void main(String[] args) {
		ListNode a1 = new ListNode(5);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(3);
		
		a1.next = a2;
		a2.next = a3;
		
		ListNode b1 = new ListNode(5);
		ListNode b2 = new ListNode(2);
		ListNode b3 = new ListNode(1);
		
		b1.next = b2;
		b2.next = b3;
		
		Plus test = new Plus();
		ListNode c = test.plusAB(a1, b1);
		c.printAll();
	}

	public ListNode plusAB(ListNode a, ListNode b) {
		ListNode c = new ListNode(0);

		if (a == null || b == null) {
			return c;
		}

		int forward = plusABCore(a, b, c);
		ListNode pA = a.next;
		ListNode pB = b.next;
		ListNode pC = c;

		while (pA != null && pB != null) {
			ListNode newC = new ListNode(forward);
			forward = plusABCore(pA, pB, newC);
			pC.next = newC;
			pC = newC;
			pA = pA.next;
			pB = pB.next;
		}
		
		if(pA != null || pB != null){
			return new ListNode(0);
		}
		
		if(forward > 0){
			ListNode newC = new ListNode(forward);
			pC.next = newC;
		}

		return c;
	}

	private int plusABCore(ListNode a, ListNode b, ListNode c) {
		int cV = a.val + b.val + c.val;
		int forWard = 0;

		if (cV >= 10) {
			cV -= 10;
			forWard = 1;
		}

		c.val = cV;

		return forWard;
	}

}
