package com.lqb.algorithm.cracking_the_coding_interview;

import java.util.LinkedList;

import com.lqb.algorithm.offer.domain.ListNode;

/**
 * ���дһ����������������Ƿ�Ϊ���ġ�
 * ����һ������ListNode* pHead���뷵��һ��bool�����������Ƿ�Ϊ���ġ�
 * ����������
 * 
 * {1,2,3,2,1}
 * ���أ�true
 * 
 * {1,2,3,2,3}
 * ���أ�false
 * 
 * @Author:JackBauer
 * @Date:2016��8��1��
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
			//��������ƶ��ȽϺ�
			//�����һ��ֵҪ��ѭ����ʼǰ������ӽ�ջ���һ�Ҫ��һЩ�������
			pFast = pFast.next.next;
			pSlow = pSlow.next;
		}
		
		//���ż������ʱ�����
		//��1,2,3,3,2,1����ʱ��pFastΪ�ڶ���2��pSlowΪ��һ��3����3δ��ӽ�ջ���Ѿ��˳���ѭ��
		//��ʱ��ѵ�һ��3Ҳ��ӽ�ȥ
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
