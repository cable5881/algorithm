package com.lqb.leetcode.mark.design;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/12/20 17:25
 **/
public class LRUCache {

    @Test
    public void test1() {
        //测试用例1
        LRUCache1 lruCache = new LRUCache1(1);
        lruCache.put2(2, 1);

        //1
        System.out.println(lruCache.get2(2));

        lruCache.put2(3, 2);

        //-1
        System.out.println(lruCache.get2(2));

        //2
        System.out.println(lruCache.get2(3));

        System.out.println("##########################################");

        //测试用例2
        lruCache = new LRUCache1(2);
        lruCache.put2(1, 1);
        lruCache.put2(2, 2);
        lruCache.put2(3, 3);

        //-1
        System.out.println(lruCache.get2(1));

        //2，访问了一次2，那么此时应该是3,2的顺序
        System.out.println(lruCache.get2(2));

        //此时删掉了3，添加4后是2, 4
        lruCache.put2(4, 4);
        //-1
        System.out.println(lruCache.get2(3));
        //2，访问了一次2，那么此时应该是4,2的顺序
        System.out.println(lruCache.get2(2));

        //此时删掉了4
        lruCache.put2(5, 5);
        //-1
        System.out.println(lruCache.get2(4));

        System.out.println("##########################################");
        //测试用例3
        //[[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        lruCache = new LRUCache1(2);
        lruCache.put2(2, 1);
        lruCache.put2(2, 2);

        //2
        System.out.println(lruCache.get2(2));

        //此时是2,1
        lruCache.put2(1, 1);

        //删掉了2，添加4后是1, 4
        lruCache.put2(4, 1);
        //-1
        System.out.println(lruCache.get2(2));
    }

    @Test
    public void test2() {
        LRUCache2 lruCache = new LRUCache2(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);

        //null
        System.out.println(lruCache.get(1));

        //2，访问了一次2，那么此时应该是3,2的顺序
        System.out.println(lruCache.get(2));

        //此时删掉了3
        lruCache.put(4, 4);
        //null
        System.out.println(lruCache.get(3));
        //2，访问了一次2，那么此时应该是4,2的顺序
        System.out.println(lruCache.get(2));

        //此时删掉了4
        lruCache.put(5, 5);
        //null
        System.out.println(lruCache.get(4));
    }

}

/**
 * @author liqibo
 * @date 2020/3/2 16:28
 * @description 自定义链表，新的元素插到尾部，满了后删除头部元素
 */
class LRUCache1 {

    private Map<Integer, ListNode<Integer, Integer>> cache;

    ListNode<Integer, Integer> head;

    ListNode<Integer, Integer> tail;

    private int capacity;

    private int size = 0;

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);

        //如果不设置一个冗余的head和tail，那考虑的情况会比较复杂
        head = new ListNode<>(-1, -1);
        tail = new ListNode<>(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    //没有冗余head和tail
    public Integer get(int key) {
        ListNode<Integer, Integer> node = cache.get(key);
        if (node == null) {
            return -1;
        }

        ListNode prev = node.prev;
        ListNode next = node.next;

        //存在前置，说明不是头节点
        if (prev != null) {
            if (next == null) {
                //说明是尾节点
                return node.val;
            }
            prev.next = next;
        } else if (next != null) {
            //说明是头结点
            head = next;
            next.prev = null;
        } else {
            //说明当前只有一个节点，不需要走下面的逻辑了，直接返回吧
            return node.val;
        }

        //前面的条件判断已经能够确定此时node不是尾节点，即next肯定不为null了
        //设置一下后置节点的前置节点
        next.prev = prev;

        //最后把node设置为尾节点
        node.prev = tail;
        node.next = null;
        tail.next = node;
        tail = node;

        return node.val;
    }

    //没有冗余head和tail
    public void put(Integer key, Integer value) {

        //如果key已经存在，那么替换一下value，并调用一个get调整其到链表尾部
        if (cache.containsKey(key)) {
            ListNode<Integer, Integer> node = cache.get(key);
            node.val = value;
            get(key);
            return;
        }

        ListNode<Integer, Integer> node = new ListNode<>(key, value);
        cache.put(key, node);
        //如果超过最大容量，此时不需要增加size
        if (size + 1 > capacity) {
            //当前只有1个节点，直接把新节点替换掉老首部和老尾部即可，否则去掉老首部
            ListNode next = head.next;
            cache.remove(head.key);
            if (next == null) {
                head = node;
                tail = node;
                return;
            } else {
                next.prev = null;
                head = next;
            }
        } else {
            size++;
        }

        //如果cache是空的
        if (head == null) {
            head = node;
            tail = head;
        } else {
            //插入节点到尾部
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public Integer get2(int key) {
        ListNode<Integer, Integer> node = cache.get(key);
        if (node == null) {
            return -1;
        }

        remove(node);
        //注意上面remove后会从缓存删除，因此下面要加回来
        cache.put(node.key, node);
        setTail(node);
        return node.val;
    }

    private void setTail(ListNode<Integer, Integer> node) {
        ListNode prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(ListNode<Integer, Integer> node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;

        cache.remove(node.key);
    }

    public void put2(Integer key, Integer value) {
        ListNode<Integer, Integer> oldNode = cache.get(key);
        if (oldNode != null) {
            oldNode.val = value;
            remove(oldNode);
            //注意上面remove后会从缓存删除，因此下面要加回来
            cache.put(key, oldNode);
            setTail(oldNode);
            return;
        }

        if ((size + 1) > capacity) {
            removeHead();
        } else {
            size++;
        }
        ListNode<Integer, Integer> newNode = new ListNode<>(key, value);
        cache.put(key, newNode);
        setTail(newNode);
    }

    private void removeHead() {
        ListNode firstNode = head.next;
        ListNode secondNode = firstNode.next;

        head.next = secondNode;
        secondNode.prev = head;

        cache.remove(firstNode.key);
    }

    private static class ListNode<K, V> {

        public K key;
        public V val;
        public ListNode next = null;
        public ListNode prev = null;

        public ListNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public void printAll() {
            ListNode p = this;

            while (p != null) {
                System.out.println(p.val);
                p = p.next;
            }
        }
    }
}

/**
 * @author liqibo
 * @date 2020/3/2 16:35
 * @description 继承LinkedHashMap
 */
class LRUCache2 {

    private Map<Integer, Integer> cache;

    public LRUCache2(int capacity) {
        //注意要按照访问顺序
        this.cache = new LinkedHashMap(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > capacity;
            }
        };
    }

    public int get(int key) {
        return this.cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        this.cache.put(key, value);
    }
}
