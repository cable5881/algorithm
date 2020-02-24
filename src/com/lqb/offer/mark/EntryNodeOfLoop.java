package com.lqb.offer.mark;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 一个链表中包含环，请找出该链表的环的入口结点。
 *
 * @author:JackBauer
 * @date:2016年6月25日 上午9:40:31
 */
public class EntryNodeOfLoop {

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3;

        ListNode node = entryNodeOfLoop2(n1);
        System.out.println(node.val);
    }

    /**
     * 1.先通过快慢指针找到相遇的节点
     * 2.计算出环的长度
     * 3.让快指针先跑loopLength的长度，然后快指针和慢指针以相同的速度出发，那么会到圈起点处相遇
     *
     * 第三步为什么就能相遇呢。
     * 假设起点到环入口处长度是s1, 然后环长度是s2
     * ①假设s1>s2，即环的长度较短时，那么p1先走s2的长度，离下次相遇就还要走s1-s2) + s2 = s1
     * 此时p1和p2同时出发，到相遇点时，p1走了(s1-s2) + s2 = s1, 而p2也是刚好走了s1的距离
     *
     * ②假设s2>s1时，即环的长度较长时，那么p1先走s2的长度，离下次相遇就还要走s2-(s2-s1)=s1
     * 此时p1和p2同时出发，到相遇点时，p1走了s1, 而p2也是刚好走了s1的距离
     */
    public ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        //先通过快慢指针找到相遇的节点
        ListNode loopNode = getNodeOfLoop(pHead);
        if (loopNode == null) {
            return null;
        }

        //此时该节点已经在圈内了，那么通过在圈内兜一圈可以计算出环的长度，即节点数量
        int loopLength = getLengthOfLoop(loopNode);
        //让快指针先跑loopLength的长度，然后快指针和慢指针以相同的速度出发，那么会到圈起点处相遇
        ListNode pFast = getKthNode(pHead, loopLength);
        ListNode pSlow = pHead;

        while (pSlow != pFast) {
            pSlow = pSlow.next;
            pFast = pFast.next;
        }

        return pSlow;
    }

    public ListNode getNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode pSlow = pHead;
        ListNode pFast = pHead.next;

        while (pFast != null && pFast.next != null) {
            if (pFast == pSlow || pFast.next == pSlow) {
                return pSlow;
            }
            pSlow = pSlow.next;
            pFast = pFast.next.next;
        }

        return null;
    }

    public int getLengthOfLoop(ListNode loopHead) {
        ListNode p = loopHead.next;
        int length = 1;

        while (p != loopHead) {
            length++;
            p = p.next;
        }

        return length;
    }

    public ListNode getKthNode(ListNode pHead, int k) {
        ListNode p = pHead;
        int i = 0;
        while (i < k) {
            p = p.next;
            i++;
        }

        return p;
    }

    /**
     * @author liqibo
     * @date 2020/2/24 15:12
     * @description 发现自己还是不会，hhh
     */
    public ListNode entryNodeOfLoop2(ListNode pHead) {

        if (pHead == null) {
            return null;
        }

        if (pHead.next == null) {
            return null;
        }

        ListNode pSlow = pHead;
        ListNode pFast = pHead.next.next;

        while (pFast != pSlow && pFast.next != pSlow) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }

        int lenOfLoop = 1;
        ListNode p = pFast.next;
        while (p != pFast) {
            p = p.next;
            lenOfLoop++;
        }

        ListNode p1 = pHead;
        //注意是从0开始而不是1
        for (int i = 0; i < lenOfLoop; i++) {
            p1 = p1.next;
        }

        ListNode p2 = pHead;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}
