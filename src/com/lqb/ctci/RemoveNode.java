package com.lqb.ctci;

/**
 * 实现一个算法，删除单向链表中间的某个结点，假定你只能访问该结点。
 * 给定带删除的节点，请执行删除操作，若该节点为尾节点，返回false，否则返回true
 */
import com.lqb.util.ListNode;

public class RemoveNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean removeNode(ListNode pNode) {
		if(pNode == null || pNode.next == null){
			return false;
		}
		
		ListNode next = pNode.next;
		pNode.val = next.val;
		pNode.next = next.next;
		next = null;
		
		return true;
	}

}
