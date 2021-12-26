package com.lqb.offer.mark;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @Description:给定一个数组和滑动窗口的大小，
 * 找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}，
 * {2,3,4,2,6,[2,5,1]}。
 *
 * @Author:JackBauer
 * @Date:2016年7月13日 下午3:41:43
 */
public class MaxInWindows {

    @Test
    public void test() {
        //[4, 4, 6, 6, 6, 5]
        System.out.println(maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3));

        //[16, 14, 12]
        System.out.println(maxInWindows(new int[]{16, 14, 12, 10, 8, 6, 4}, 5));

        //[]
        System.out.println(maxInWindows(new int[]{10, 14, 12, 11}, 5));
    }

    /**
     * 用一个队列保存当前最大值和未来的最大值。
     * 每当窗口移动一位，新加进窗口的有可能是未来的最大值或者当前最大值，遍历队列，把比它值小的全部剔除。
     * 所以这个队列经过剔除后肯定是一个排好序的。队首元素最大。
     * 然后判断当前的最大值是否已经在窗口外了，是就从队列中剔除。
     */
    public ArrayList<Integer> maxInWindows(int[] num, int windowSize) {
        ArrayList<Integer> res = new ArrayList<>();

        if (num == null || num.length < windowSize || windowSize < 1) {
            return res;
        }

        LinkedList<Integer> maxQueue = new LinkedList<>();
        int windowRight = 0;
        //先添加第一个元素
        maxQueue.add(windowRight++);

        //先填充满第一个窗口
        while (windowRight < windowSize) {
            while (!maxQueue.isEmpty() && num[windowRight] > num[maxQueue.peekLast()]) {
                maxQueue.removeLast();
            }
            maxQueue.add(windowRight++);
        }

        while (windowRight < num.length) {
            res.add(num[maxQueue.peek()]);

            //从底开始向上遍历，碰到比当前最新值num[windowRight]小的则全部剔除掉
            while (!maxQueue.isEmpty() && num[windowRight] > num[maxQueue.peekLast()]) {
                maxQueue.removeLast();
            }
            //num[windowRight]可能是未来的最大值，也有可能已经是当前的最大值了
            maxQueue.add(windowRight);

            //如果最大值已经在窗口外了，那么剔除
            if (windowRight - maxQueue.peek() == windowSize) {
                maxQueue.removeFirst();
            }

            windowRight++;
        }

        //注意最后还要再添加一次或者上面的while第一次也不成立，那么这里是添加第一个窗口
        res.add(num[maxQueue.peek()]);

        return res;
    }

    /**
     * 2021/12/25 更新
     * 用了大顶堆，堆中把每次数组中遍历的元素都加入进去，然后统计窗口最大时弹出，之前入堆但已经不在窗口的就忽略本次继续弹出
     * 时间复杂度：nlog(k)
     * 空间复杂度：O(n)
     */
    public ArrayList<Integer> maxInWindows2(int[] num, int windowSize) {
        ArrayList<Integer> res = new ArrayList<>();
        if(num == null || num.length <= 0 || windowSize <= 0 || windowSize > num.length) {
            return res;
        }

        PriorityQueue<Pair> windows = new PriorityQueue<>(windowSize,(o1, o2) -> o2.val - o1.val);
        for(int i = 0; i < num.length; i++) {
            windows.add(new Pair(num[i], i));
            if(windows.size() < windowSize) {
                continue;
            }
            while(windows.peek().idx + windowSize <= i) {
                windows.poll();
            }
            res.add(windows.peek().val);
        }
        return res;
    }

    private static class Pair {
        public int val;
        public int idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    /**
     * 和第一种方式是一样的，但是代码更加简洁.
     * 注意：windowsMax存储的是数组下标，而不是实际的值。只有这样才能知道下标是否过期(不在窗口内)
     * 算法复杂度O(n*1)
     */
    public ArrayList<Integer> maxInWindows3(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if(num == null || num.length <= 0 || size <= 0 || size > num.length) {
            return res;
        }

        LinkedList<Integer> windowsMax = new LinkedList<>();
        for(int i = 0; i < num.length; i++) {
            //校验最大值是否已经过期，过期则移除，老二上位
            if(!windowsMax.isEmpty() && windowsMax.peekFirst() <= i - size) {
                windowsMax.removeFirst();
            }
            //当前值都可能是未来最大的，于是杀掉比自己老又没有自己大的老臣
            while(!windowsMax.isEmpty() && num[windowsMax.peekLast()] <= num[i]) {
                windowsMax.removeLast();
            }
            //新来的都到末尾，未来没有被杀掉就有可能登顶成为老大
            windowsMax.addLast(i);
            //添加到结果集的时机
            if(i >= size - 1) {
                res.add(num[windowsMax.peekFirst()]);
            }
        }
        return res;
    }
}
