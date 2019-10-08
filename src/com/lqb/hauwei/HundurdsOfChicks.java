package com.lqb.hauwei;

import java.util.Scanner;

public class HundurdsOfChicks {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			in.nextInt();
			fun2();
		}
	}

	public static void fun() {
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 33; j++) {
				for (int k = 0; k <= 99; k += 3) {
					if ((i * 5 + j * 3 + k / 3) == 100 && (i + j + k == 100)) {
						System.out.println(i + " " + j + " " + k);
					}
				}
			}
		}
	}

	public static void fun2() {
		for (int i = 0; i < 100 / 7; i++) {
			for (int j = 0; j <= 100 / 4; j++) {
				int k = 100 - i - j;
				if ((i * 5 + j * 3 + k / 3) == 100 && (k % 3 == 0)) {
					System.out.println(i + " " + j + " " + k);
				}
			}
		}
	}

}
