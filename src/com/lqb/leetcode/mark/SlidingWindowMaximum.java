package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SlidingWindowMaximum {

    @Test
    public void test() {

        //[3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

        //[7, 4]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{7, 2, 4}, 2)));

        //[3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

        //[3, 3, 2, 5]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1, 3, 1, 2, 0, 5}, 3)));

        //[3, 5, 5, 6, 7, 7, 7, 8]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7, 4, 5, 8}, 4)));
    }

    /**
     * @author liqibo
     * @date 2019/12/2 17:24
     * @description 自己的解法，通过最大堆来求窗口最大值
     * 时间复杂度O(n * logk)，空间复杂度O(k)
     * 执行用时：45ms，击败了25.31%的用户
     * 内存消耗：41MB，击败了95.61%的用户
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length <= 0 || k <= 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        PriorityQueue<Integer> q = new PriorityQueue(k, (Comparator<Integer>) (o1, o2) -> o2 - o1);
        for (int i = 0; i < k - 1; i++) {
            q.add(nums[i]);
        }

        for (int i = k - 1, j = 0; j < res.length ; i++, j++) {
            q.add(nums[i]);
            res[j] = q.peek();
            q.remove(nums[i - k + 1]);
        }

        return res;
    }

    /**
     * @author liqibo
     * @date 2019/12/2 20:16
     * @description 官方解法，时间复杂度O(n)，空间复杂度O(k)
     * 双向队列法，该队列可以存储滑动窗口内最大的元素
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        //一个存储元素下标的双向队列，头节点是窗口内的最大元素，后面的节点是下一个窗口的最大元素
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        //第一阶段先窗口内添加满元素
        for (int i = 0; i < k; i++) {
            cleanDeque(deq, nums, i, k);
            deq.addLast(i);
        }

        int[] res = new int[n - k + 1];
        //先写入结果集第一个窗口最大元素
        res[0] = nums[deq.getFirst()];
        for (int i = k; i < n; i++) {
            cleanDeque(deq, nums, i, k);
            deq.addLast(i);
            res[i - k + 1] = nums[deq.getFirst()];
        }

        return res;
    }

    private void cleanDeque(ArrayDeque<Integer> deq, int [] nums, int i, int k) {

       //清除不是窗口内的元素
        if (!deq.isEmpty() && deq.getFirst() + k <= i) {
            deq.removeFirst();
        }

        //有些窗口内的元素不可能是这个窗口内的最大值，需要去掉，只保留最大的元素
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    /**
     * @author liqibo
     * @date 2019/12/2 20:35
     * @description 官方解法2，动态规划，时间复杂度O(n)，空间复杂度O(n)
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) {
                // block_start
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            int j = n - i - 1;
            if ((j + 1) % k == 0) {
                // block_end
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        //拿第五个测试用例举例子
        //left =   [1, 3, 3, 3, 5, 5,  6,  7, 4, 5, 8]
        //right =           [3, 3, -1, -3, 7, 7, 7, 7, 8, 8, 8]
        //output =          [3, 5, 5,  6,  7, 7, 7, 8]

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            output[i] = Math.max(left[i + k - 1], right[i]);
        }

        return output;
    }

}
