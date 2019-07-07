package com.lqb.algorithm;

public class Utils {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addNode(6);
		list.addNode(2);
		list.addNode(9);
		list.addNode(4);
		list.addNode(7);
		list.addNode(1);
		list.addNode(5);
		list.addNode(0);
		list.addNode(44);
		list.addNode(333);
		list.print();
		System.out.println("********************");
		System.out.println(searchMid(list));

	}

	public static Integer searchMid(LinkedList<Integer> list) {
		if (list.length() < 2)
			return list.head.data;

		Node<Integer> fastPtr = list.head.next.next;
		Node<Integer> slowPtr = list.head;

		while (fastPtr.next != null && fastPtr.next.next != null) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
		}

		return slowPtr.next.data;
	}

	public static void orderList(LinkedList<Integer> list) {
		Node<Integer> head = list.head;
		if (head == null)
			return;

		int length = list.length();
		int temp = 0;
		Node<Integer> preNode = null;
		Node<Integer> currentNode = null;

		for (int i = 1; i < length; i++) {
			preNode = head;
			currentNode = head.next;
			for (int j = i + 1; j <= length; j++) {
				if (preNode.data > currentNode.data) {
					temp = currentNode.data;
					currentNode.data = preNode.data;
					preNode.data = temp;
				}
				preNode = currentNode;
				currentNode = currentNode.next;
			}
		}
	}
}
