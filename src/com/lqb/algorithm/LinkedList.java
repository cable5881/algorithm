package com.lqb.algorithm;


class LinkedList<E> {

	Node<E> head = null;

	public void addNode(E data) {
		Node<E> node = new Node<>(data);
		if (head == null) {
			head = node;
		} else {
			// node.next = head.next;
			// head.next = node;

			Node<E> temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
		}
	}

	public void deleteNode(int index){
		if(index > 0 && index < length()){
			Node<E> temp = head;
			int i = 2;

			if(index == 1){
				head = head.next;
				return;
			}

			while(i < index){
				temp = temp.next;
				i++;
			}
			Node<E> deleteOne = temp.next;
			temp.next = deleteOne.next;
			
			// no need to free deleteOne 'cause GC works
		}
	}

	public int length(){
		if(head == null)
			return 0;

		int length = 0;
		Node<E> temp = head;
		while(temp != null){
			length++;
			temp = temp.next;
		}

		return length;
	}


	public void print() {
		Node<E> temp = head;
		while (temp != null){
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addNode(6);
		list.addNode(2);
		list.addNode(9);
		list.addNode(4);
		list.addNode(7);
		list.addNode(1);
		list.addNode(5);
		list.addNode(8);
		list.print();
		System.out.println("********************");
		//System.out.println(list.length());
		//list.deleteNode(4);
		//list.print();
		System.out.println("********************");
		//Utils.orderList(list);
		//SortUtils.selectSort(list);
		list.print();
	}
}
