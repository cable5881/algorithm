package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.LinkedList;

import com.lqb.util.ListNode;

/**
 * 请编写一个函数，检查链表是否为回文。
 * 给定一个链表ListNode* pHead，请返回一个bool，代表链表是否为回文。
 * 测试样例：
 * 
 * {1,2,3,2,1}
 * 返回：true
 * 
 * {1,2,3,2,3}
 * 返回：false
 * 
 * @Author:JackBauer
 * @Date:2016年8月1日
 */
public class IsPalindrome {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(3);
		ListNode n7 = new ListNode(2);
		ListNode n8 = new ListNode(1);
//		ListNode n9 = new ListNode(1);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
//		n8.next = n9;
		
		IsPalindrome test = new IsPalindrome();
		System.out.println(test.isPalindrome(n1));
	}
	
	public boolean isPalindrome(ListNode pHead) {
		if(pHead == null){
			return false;
		}
		
		LinkedList<ListNode> stack = new LinkedList<>();
		ListNode pFast = pHead;
		ListNode pSlow = pHead;
		
		while(pFast.next != null && pFast.next.next != null){
			stack.push(pSlow);
			//先添加再移动比较好
			//否则第一个值要在循环开始前另外添加进栈而且还要多一些额外操作
			pFast = pFast.next.next;
			pSlow = pSlow.next;
		}
		
		//解决偶数长度时的情况
		//如1,2,3,3,2,1。这时候pFast为第二个2，pSlow为第一个3，但3未添加进栈就已经退出了循环
		//这时候把第一个3也添加进去
		if(pFast.next != null){
			stack.push(pSlow);
		}
		
		pSlow = pSlow.next;
		
		while(!stack.isEmpty() && pSlow != null){
			if(pSlow.val != stack.pop().val){
				return false;
			}
			pSlow = pSlow.next;
		}
		
		return stack.isEmpty() && pSlow == null;
    }

}
