package com.lqb.algorithm.offer.domain;


public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
    
    
    public void printAll(){
    	ListNode p = this;
    	
    	while( p != null ){
    		System.out.println(p.val);
    		p = p.next;
    	}
    	
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
}
