package com.lqb.offer;

import java.util.Stack;

public class IsPopOrder {

	public static void main(String[] args) {
		int[] popA = { 4, 5, 3, 2, 1 };
		int[] pushA = { 1, 2, 3, 4, 5 };

		IsPopOrder ipo = new IsPopOrder();
		// System.out.println(ipo.isPopOrder(null, popA));
		// System.out.println(ipo.isPopOrder(null, null));
		// System.out.println(ipo.isPopOrder(pushA, null));
		// System.out.println(ipo.isPopOrder(new int[0], popA));
		//
		// int[] pushB = {1,2,3};
		// System.out.println(ipo.isPopOrder(pushB, popA));
		System.out.println(ipo.isPopOrder(pushA, popA));

		// int[] popB = { 1, 2, 4, 3, 5 };
		// System.out.println(ipo.isPopOrder(pushA, popB));

		// int[] popB = { 3, 5, 4, 2, 1 };
		// System.out.println(ipo.isPopOrder(pushA, popB));

		int[] popB = { 4, 5, 3, 2, 1 };
		System.out.println(ipo.isPopOrder(pushA, popB));
	}

	public boolean isPopOrder(int[] pushA, int[] popA) {

		if (isArrayEmpty(pushA) || isArrayEmpty(popA)) {
			return false;
		}

		if (!isTwoArrLengthEqual(pushA, popA)) {
			return false;
		}

		Stack<Integer> stack = new Stack<>();

		int i = 0;
		int j = 0;

		for (; j < pushA.length; j++) {
			if (pushA[j] != popA[i]) {
				stack.push(pushA[j]);
			} else {
				// break;
				i++;
			}
		}

		// while (j < pushA.length) {
		// if (pushA[j] != popA[i]) {
		// return false;
		// }
		// j++;
		// i++;
		// }

		while (i < popA.length) {
			if (stack.pop() != popA[i]) {
				return false;
			}

			i++;
		}

		return true;
	}

	public boolean isArrayEmpty(int[] arr) {
		return arr == null ? true : (arr.length <= 0 ? true : false);
	}

	public boolean isTwoArrLengthEqual(int[] pushA, int[] popA) {
		return pushA.length == popA.length;
	}

}
