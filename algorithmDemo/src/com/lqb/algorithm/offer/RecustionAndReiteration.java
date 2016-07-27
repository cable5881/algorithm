package com.lqb.algorithm.offer;

public class RecustionAndReiteration {

	public static void main(String[] args) {
//		System.out.println(fibonacci(1));
//		System.out.println(fibonacci(2));
//		System.out.println(fibonacci(3));
//		System.out.println(fibonacci(4));
//		System.out.println(fibonacci(5));
//		System.out.println(fibonacci(6));
//		System.out.println(fibonacci(7));
		
		System.out.println(JumpFloor(1));
		System.out.println(JumpFloor(2));
		System.out.println(JumpFloor(3));
		System.out.println(JumpFloor(4));
		System.out.println(JumpFloor(5));
		System.out.println(JumpFloor(6));
		
	}

	public static int fibonacci(int n) {

		int first = 0;
		int second = 1;
		int temp = 0;

		if (n == 0) {
			return 0;
		}

		for (int i = 1; i < n; i++) {
			temp = second;
			second += first;
			first = temp;
		}

		return second;
	}

	public static int JumpFloor(int target) {
		if (target < 0) {
			return -1;
		}
		
		if (target == 0) {
			return 1;
		}
		if( target == 1 ){
			return 1;
		}
		
//		return JumpFloor(target - 1) + JumpFloor(target - 2);
		return 2*JumpFloor(target - 1);
	}

}
