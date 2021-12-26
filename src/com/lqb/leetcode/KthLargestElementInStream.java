package com.lqb.leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *  
 *
 * 示例：
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 *
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargestElementInStream {

    public static void main(String[] args) {
        KthLargestElementInStream demo = new KthLargestElementInStream(3, new int[]{3, 4, 5});
        System.out.println(demo.add(6)); //4
        System.out.println(demo.add(1)); //4
        System.out.println(demo.add(7)); //5
        System.out.println(demo.add(5)); //5
    }

    private PriorityQueue<Integer> q;
    private int k;

    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (q.size() < this.k) {
            q.add(val);
            return q.peek();
        }

        if (q.peek() < val) {
            q.poll();
            q.add(val);
        }
        return q.peek();
    }
}
