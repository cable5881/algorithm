package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * Լɪ��������һ���ǳ�������Ȥ�⣬����n��������һȦ����˳ʱ����1��ʼ�����Ǳ�š�
 * Ȼ���ɵ�һ���˿�ʼ����������m���˳��֡�������Ҫ��������һ�����ֵ��˵ı�š� 
 * ��������int n��m��������Ϸ���������뷵�����һ�����ֵ��˵ı�š���֤n��mС�ڵ���1000��
 * 
 * @author:JackBauer
 * @date:2016��10��12��
 */
public class Joseph {

	public static void main(String[] args) {
		Joseph test = new Joseph();
		System.out.println(test.getResult(5, 3));
	}
	
	public int getResult(int n, int m) {
		if(n < 1 || m < 1) {
			return -1;
		}
		
		Node head = new Node(1);
		Node cur = head;
		for(int i = 2; i <= n; i++) {
			Node node = new Node(i);
			cur.next = node;
			cur = node;
		}
		cur.next = head;
		
		Node p = cur;
		while(p.next != p) {
			for(int i = 1; i < m; i++) {
				p = p.next;
			}
			p.next = p.next.next;
		}
		
		return p.val;
    }
	
	private class Node {
		int val;
		Node next;
		
		public Node(int val) {
			this.val = val;
		}
	}

}
