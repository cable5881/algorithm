package com.lqb.algorithm.offer;

/**
 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ��+��-��*��/����������š� 
 * @author:JackBauer
 * @date:2016��6��25�� ����9:38:58
 */
public class AddWithoutSymbolPlus {

	public static void main(String[] args) {
		AddWithoutSymbolPlus test = new AddWithoutSymbolPlus();
		System.out.println(test.add(-3, -3));
	}
	
	public int add(int num1, int num2){
		/*
		 * Ҫ���Ǹ��������
		 */
		while(num2 != 0){
			int addWithoutMoveBit = num1 ^ num2;
			System.out.println(Integer.toBinaryString(num1));
			System.out.println("^");
			System.out.println(Integer.toBinaryString(num2));
			System.out.println(Integer.toBinaryString(addWithoutMoveBit));
			System.out.println("bitMoving...");
			
			int bitMove = (num1 & num2) << 1;
			System.out.println(Integer.toBinaryString(bitMove));
			System.out.println("---------------------------------");
			num1 = addWithoutMoveBit;
			num2 = bitMove;
		}
		
		return num1;
	}

}
