package com.lqb.offer;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）。
 * 
 * @author:JackBauer
 * @date:2016年6月14日 下午1:33:47
 */
public class CopyComplexList {

	public static void main(String[] args) {
		RandomListNode n1 = new RandomListNode(1);
		RandomListNode n2 = new RandomListNode(2);
		RandomListNode n3 = new RandomListNode(3);
		RandomListNode n4 = new RandomListNode(4);
		RandomListNode n5 = new RandomListNode(5);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		n1.random = n3;
		n4.random = n2;

		RandomListNode n = Clone(n1);
		while (n != null) {
			System.out.print(n.label);
			if (n.random != null) {
				System.out.print("-->" + n.random.label);
			}
			System.out.println();
			n = n.next;
		}

	}

	public static RandomListNode Clone(RandomListNode pHead) {

		if (pHead == null) {
			return null;
		}

		RandomListNode p = pHead;

		while (p != null) {
			RandomListNode r = new RandomListNode(p.label);
			r.next = p.next;
			p.next = r;
			p = r.next;
		}

		p = pHead;
		RandomListNode q = p.next;

		while (true) {
			//random可能为null
			if (p.random != null) {
				q.random = p.random;
			}

			p = q.next;
			if (p != null) {
				q = p.next;
			}else{
				break;
			}
		}

		p = pHead;
		q = p.next;
		RandomListNode qHead = q;

		while (true) {
			p.next = q.next;
			p = q.next;

			if (p != null) {
				q.next = p.next;
				q = p.next;
			}else{
				break;
			}
		}

		return qHead;
	}

}

class RandomListNode {
	int label;
	RandomListNode next = null;
	RandomListNode random = null;

	RandomListNode(int label) {
		this.label = label;
	}
}
