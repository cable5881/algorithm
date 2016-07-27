package com.lqb.algorithm.offer;

import com.lqb.algorithm.offer.domain.ListNode;

/**
 * ÿ����һ��ͯ��,NowCoder����׼��һЩС����ȥ�����¶�Ժ��С����,����������ˡ�
 * HF��ΪNowCoder������Ԫ��,��ȻҲ׼����һЩС��Ϸ��
 * ����,�и���Ϸ��������:����,��С������Χ��һ����Ȧ��Ȼ��,�����ָ��һ����m,�ñ��Ϊ0��С���ѿ�ʼ������
 * ÿ�κ���m���Ǹ�С����Ҫ���г��׸�,Ȼ���������Ʒ�����������ѡ����,���Ҳ��ٻص�Ȧ��,��������һ��С���ѿ�ʼ,����0...m-1����
 * ....������ȥ....ֱ��ʣ�����һ��С����,���Բ��ñ���,�����õ�NowCoder����ġ�����̽���ϡ���ذ�(��������Ŷ!!^_^)��
 * ������������,�ĸ�С���ѻ�õ������Ʒ�أ� 
 * @author:JackBauer
 * @date:2016��6��23�� ����5:15:53
 */
public class LastRemaining {

	public static void main(String[] args) {
		LastRemaining test = new LastRemaining();
		int num = test.lastRemaining(5, 3);
		System.out.println(num);
	}
	
	public int lastRemaining(int n, int m){
		if(n < 1 || n < m || m < 1){
			return 0;
		}
		
		ListNode head = new ListNode(0);
		ListNode p = head;
		for(int i = 1; i < n; i++){
			ListNode node = new ListNode(i);
			p.next = node;
			p = node;
		}
		
		if(m == 1){
			p.next = head.next;
			head = head.next;
		}else{
			p.next = head;
		}
		
		p = head;
		
		while(p.next != p){
			int i = 1;
			while( i < m - 1){
				p = p.next;
				i++;
			}
			
			p.next = p.next.next;
			p = p.next;
		}
		
		return p.val;
	}

}
