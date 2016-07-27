package com.lqb.algorithm.offer;

import java.util.Stack;

public class StackWithMin {

	private Stack<Integer> stack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public static void main(String[] args) {
		StackWithMin s = new StackWithMin();
		
		s.push(3);
		s.push(6);
		s.push(9);
		s.push(5);
		s.push(1);
		s.push(2);
		
		System.out.println(s.top());
		System.out.println(s.min());
		
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		
		System.out.println(s.min());

	}

	public void push(int node) {
		if (minStack.isEmpty() || minStack.peek() > node) {
			minStack.push(node);
		}
		
		stack.push(node);
	}

	public void pop() {
		if (stack.isEmpty()) {
			throw new RuntimeException("The stack is empty");
		}

		if (stack.peek() == minStack.peek()) {
			minStack.pop();
		}

		stack.pop();
	}

	public int top() {
		if (stack.isEmpty()) {
			throw new RuntimeException("The stack is empty");
		}

		return stack.peek();
	}

	public int min() {

		if (minStack.isEmpty()) {
			throw new RuntimeException("The stack is empty");
		}

		return minStack.peek();
	}

}
