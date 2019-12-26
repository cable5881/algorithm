package com.lqb.offer;

import com.lqb.util.ListNode;

import java.util.ArrayList;

public class ReplaceSpace {

	public static void main(String[] args) {
//		System.out.println(replaceSpace(new StringBuffer("We Are Happy")));
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		ArrayList<Integer> arr1 = new ReplaceSpace().printListFromTailToHead(node1);
		System.out.println(arr1);
	}

	public static String replaceSpace(StringBuffer str) {

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				str.replace(i, i + 1, "%20");
			}
		}

		return str.toString();
	}

	private ArrayList<Integer> arr = new ArrayList<>();
	
	public ArrayList<Integer> printListFromTailToHead(ListNode<Integer> listNode) {
		
		if( listNode == null ){
			return null;
		}

		printListFromTailToHead(listNode.next);
		arr.add(listNode.val);
		return arr;
	}

}
