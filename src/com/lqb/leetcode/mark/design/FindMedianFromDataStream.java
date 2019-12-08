package com.lqb.leetcode.mark.design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianFromDataStream {

    @Test
    public void test() {
        MedianFinder2 medianFinder = new MedianFinder2();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }

    @Test
    public void test2() {
        MedianFinder2 medianFinder = new MedianFinder2();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
    }

}

/**
 * 方法一：
 * 维护一个数组，每次找到位置插入。时间复杂度O(n)，查询和插入都是O(n)。可以用二分查找来优化查找插入的位置，那么查询效率是O( log(n) )
 * 空间复杂度O(n)。
 * 维护一个中位数，不用每次都去计算，只有添加元素后需要计算。
 */
class MedianFinder {

    private ArrayList<Integer> nums;

    private Double mid;

    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new ArrayList<>(1000);
    }

    public void addNum(int num) {
        for (int i = 0; i < nums.size(); i++) {
            if (num <= nums.get(i)) {
                nums.add(i, num);
                this.mid = findMedian(true);
                return;
            }
        }

        nums.add(num);
        this.mid = findMedian(true);
    }

    public double findMedian() {
        return findMedian(false);
    }

    public double findMedian(boolean calc) {
        if (!calc) {
            return mid;
        }
        int midIdx = nums.size() / 2;
        if (nums.size() % 2 == 0) {
            return (nums.get(midIdx) + nums.get(midIdx - 1)) * 1.0 / 2;
        }
        return nums.get(midIdx);
    }
}

/**
 * 方法二：
 * 维护一个最大堆，一个最小堆，
 * 最大堆相当于排序好的数组的前半段，堆顶为前半段的最后一个元素。
 * 最小堆相当于排序好的数组的后半段，堆顶为后半段的第一个元素。
 * 那么中位数就是两个堆顶元素相加的一半或者是其中一个堆顶元素
 *
 * 时间复杂度O( log(n) )
 * 空间复杂度O( n )
 */
class MedianFinder2 {

    private PriorityQueue<Integer> L = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private PriorityQueue<Integer> R = new PriorityQueue<>();

    /** initialize your data structure here. */
    public MedianFinder2() {

    }

    public void addNum(int num) {
        if (L.isEmpty()) {
            L.add(num);
            return;
        }

        //如果要插入的数比前半段大，那么应该插入到后半段去，但是插入后需要判断两边元素个数差不能超过2
        //如果超过2，需要将后半段的第一个元素（堆顶）弹出插到前半段的堆顶
        if (num > L.peek()) {
            //注意是先插入，再平衡。因为不确定num会不会是R的堆顶，所以不能为了平衡先弹出R的堆顶。
            //如果num比R的堆顶还要大，那么此时弹出的是堆顶；如果比堆顶小，那么弹出是num本身
            R.add(num);
            if (R.size() > L.size() + 1) {
                L.add(R.poll());
            }

            //所以可以来个优化，如果num比R堆顶小且此时R.size>L.size，那num不需要先插入到R了，直接插入到L即可
            //if (R.size() > L.size() && num < R.peek()) {
            //    L.add(num);
            //} else {
            //    R.add(num);
            //    if (R.size() > L.size() + 1) {
            //        L.add(R.poll());
            //    }
            //}

        } else {
            L.add(num);
            if (L.size() > R.size() + 1) {
                R.add(L.poll());
            }

            //这里就没有优化了，因为num肯定比L的堆顶小，所以num不是堆顶元素
        }

    }

    public double findMedian() {
        if (L.size() == R.size()){
            return (L.peek() + R.peek()) * 1.0 / 2;
        } else if (L.size() > R.size()) {
            return L.peek() * 1.0;
        } else {
            return R.peek() * 1.0;
        }
    }
}

//方法三，基于二叉平衡树，比较难理解。。。