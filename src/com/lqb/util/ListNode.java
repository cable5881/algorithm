package com.lqb.util;


public class ListNode<T> {

    public T val;
    public ListNode<T> next = null;
    public ListNode<T> prev = null;

    public ListNode(T val) {
        this.val = val;
    }

    public void printAll(){
    	ListNode p = this;
    	
    	while( p != null ){
    		System.out.print(p.val + ",");
    		p = p.next;
    	}
		System.out.println();
    }
    
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
	}

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
