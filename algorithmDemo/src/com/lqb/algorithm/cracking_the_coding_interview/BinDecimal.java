package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ��һ������0��1֮���ʵ��������Ϊdouble���������Ķ����Ʊ�ʾ�� ����������޷���ȷ����32λ���ڵĶ����Ʊ�ʾ�����ء�Error����
 * ����һ��double num����ʾ0��1��ʵ�����뷵��һ��string����������Ķ����Ʊ�ʾ���ߡ�Error����
 * 
 * ���������� 0.625 ���أ�0.101
 * 
 * @Author:JackBauer
 * @Date:2016��8��8��
 */
public class BinDecimal {

	public static void main(String[] args) {
		BinDecimal test = new BinDecimal();
		System.out.println(test.printBin(0.625));
	}

	public String printBin(double num) {
		StringBuilder sb = new StringBuilder("0.");
		
		while(num != 0){
			if((num = num * 2) >= 1){
				sb.append(1);
				num -= 1;
			}else{
				sb.append(0);
			}
			
			if(sb.length() > 32){
				return "ERROR";
			}
		}
		
		return sb.toString();
	}

}
