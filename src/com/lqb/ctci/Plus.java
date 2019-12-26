package com.lqb.ctci;

import com.lqb.util.ListNode;

/**
 * 有两个用链表表示的整数，每个结点包含一个数位。 这些数位是反向存放的，也就是个位排在链表的首部。 编写函数对这两个整数求和，并用链表形式返回结果。
 * 给定两个链表ListNode* A，ListNode* B，请返回A+B的结果(ListNode*)。 测试样例：
 * 
 * {1,2,3},{3,2,1}
 * 
 * 返回：{4,4,4}
 * 
 * @Author:JackBauer
 * @Date:2016年7月27日
 */
public class Plus {

	public static void main(String[] args) {
		ListNode a1 = new ListNode(0);
		ListNode a2 = new ListNode(0);
		ListNode a3 = new ListNode(5);
		
		a1.next = a2;
		a2.next = a3;
		
		ListNode b1 = new ListNode(0);
		ListNode b2 = new ListNode(0);
		ListNode b3 = new ListNode(6);
		ListNode b4 = new ListNode(9);
		ListNode b5 = new ListNode(9);
		
		b1.next = b2;
		b2.next = b3;
		b3.next = b4;
		b4.next = b5;
		
//		ListNode a1 = new ListNode(9);
//		ListNode a2 = new ListNode(2);
//		ListNode a3 = new ListNode(0);
//		ListNode a4 = new ListNode(7);
//		
//		a1.next = a2;
//		a2.next = a3;
//		a3.next = a4;
//		
//		ListNode b1 = new ListNode(6);
//		ListNode b2 = new ListNode(3);
//		ListNode b3 = new ListNode(7);
//		ListNode b4 = new ListNode(9);
//		ListNode b5 = new ListNode(3);
//		
//		b1.next = b2;
//		b2.next = b3;
//		b3.next = b4;
//		b4.next = b5;
		
		Plus test = new Plus();
		ListNode c = test.plusAB(a1, b1);
		c.printAll();
	}

	public ListNode plusAB(ListNode a, ListNode b) {
		ListNode c = new ListNode(0);

		if (a == null || b == null) {
			return c;
		}

		//先处理第一位，即个位
		int forward = plusABCore(a, b, c);
		ListNode pA = a.next;
		ListNode pB = b.next;
		ListNode pC = c;

		while (pA != null && pB != null) {
			ListNode newC = new ListNode(forward);//先把将进位值付给newC,可能为1或0
			forward = plusABCore(pA, pB, newC);//可以返回进位循环相加
			pC.next = newC;
			pC = newC;
			pA = pA.next;
			pB = pB.next;
		}
		
		//可能A要长一些,或者B长一些,也可能一样长,但是最高位产生了进位
		//于是要对尾巴进行处理
		if(pA != null){
			fixPlus(pA, pC, forward);
		}else if(pB != null){
			fixPlus(pB, pC, forward);
		}else if(forward > 0){
			fixPlus(null, pC, forward);
		}

		return c;
	}

	private void fixPlus(ListNode p, ListNode pC, int forward) {
		//相当于和0相加
		ListNode zero = new ListNode(0);
		
		while(p != null){
			ListNode newC = new ListNode(forward);
			forward = plusABCore(p, zero, newC);
			pC.next = newC;
			pC = newC;
			p = p.next;
		}
		
		//这种情形分两种情况
		//第一种如99600 + 500,算到这里c链表为00100(1)
		//因为上面的while循环在p = p.next退出,于是少了最高位1
		//第二种如55 +　50,最高位产生了进位，没有经过上面的while循环，到这里c链表为50(1),少了最高位1
		//无论是哪种情况最高位都为1
		if(forward > 0){
			ListNode newC = new ListNode(forward);
			pC.next = newC;
		}
	}

	//两位相加,如果进位则返回1,否则返回0
	private int plusABCore(ListNode<Integer> a, ListNode<Integer> b, ListNode<Integer> c) {
		int cV = a.val + b.val + c.val;
		int forWard = 0;

		if (cV >= 10) {
			cV -= 10;
			forWard = 1;
		}

		c.val = cV;

		return forWard;
	}

}
