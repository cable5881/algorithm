package com.lqb.offer;

import com.lqb.util.ListNode;
import org.junit.Test;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
 *
 * 1.此题对比原题有改动
 * 2.题目保证链表中节点的值互不相同
 * 3.该题只会输出返回的链表和结果做对比，所以若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 * 示例1
 * 输入：
 * {2,5,1,9},5
 * 返回值：
 * {2,1,9}
 * 说明：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 2 -> 1 -> 9
 *
 * 示例2
 * 输入：
 * {2,5,1,9},1
 * 返回值：
 * {2,5,9}
 * 说明：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 2 -> 5 -> 9
 *
 * https://www.nowcoder.com/practice/f9f78ca89ad643c99701a7142bd59f5d?tpId=13&tqId=2273171&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class DeleteNode {


    @Test
    public void test() {
        ListNode<Integer> node0 = getDemoList();
        deleteNode(node0, 3).printAll();

        node0 = getDemoList();
        deleteNode(node0, 1).printAll();

        node0 = getDemoList();
        deleteNode(node0, 6).printAll();

        node0 = getDemoList();
        deleteNode(node0, 7).printAll();
    }

    private ListNode<Integer> getDemoList() {
        ListNode<Integer> node0 = new ListNode<>(1);
        ListNode<Integer> node1 = new ListNode<>(2);
        ListNode<Integer> node2 = new ListNode<>(3);
        ListNode<Integer> node3 = new ListNode<>(4);
        ListNode<Integer> node4 = new ListNode<>(5);
        ListNode<Integer> node5 = new ListNode<>(6);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node0;
    }

    public ListNode deleteNode (ListNode head, int val) {

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode next = head;
        ListNode last = dummy;

        while (next != null) {
            if ((int)next.val == val) {
                last.next = next.next;
                break;
            }
            last = next;
            next = next.next;
        }

        return dummy.next;
    }

}
