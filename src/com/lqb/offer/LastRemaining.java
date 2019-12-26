package com.lqb.offer;

import com.lqb.util.ListNode;

/**
 * 每年六一儿童节,NowCoder都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为NowCoder的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数
 * ....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到NowCoder名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？ 
 * @author:JackBauer
 * @date:2016年6月23日 下午5:15:53
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
		
		ListNode<Integer> head = new ListNode(0);
		ListNode<Integer> p = head;
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
