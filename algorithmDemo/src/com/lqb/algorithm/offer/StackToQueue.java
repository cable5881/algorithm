package com.lqb.algorithm.offer;

import java.util.Stack;

public class StackToQueue {

	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
	
	public static void main(String[] args) {
		StackToQueue sq = new StackToQueue();
		sq.push(1);
		sq.push(3);
		sq.push(5);
		sq.push(7);
		sq.push(9);
		
		System.out.println(sq.pop());
		System.out.println(sq.pop());
		System.out.println(sq.pop());
		System.out.println(sq.pop());
		
		sq.push(11);
		
		System.out.println(sq.pop());
		System.out.println(sq.pop());
	}
	
	
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
		if( stack2.isEmpty() ){
			if( stack1.isEmpty() ){
				return -1;
			}else{
				
				while( !stack1.isEmpty() ){
					stack2.push(stack1.pop());
				}
			}
		}
		
		return stack2.pop();
    }

}
