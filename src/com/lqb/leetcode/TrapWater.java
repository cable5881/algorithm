package com.lqb.leetcode;

import org.junit.Test;

import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrapWater {

    @Test
    public void test1() {
        TrapWater demo = new TrapWater();
        int[] height = {1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(demo.trap4(height));
    }


    /**
     * 对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
     */
    int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 0; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = max(max_right, height[j]);
            }
            ans += min(max_left, max_right) - height[i];
        }
        return ans;
    }

    /**
     * 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。
     * 但是我们可以提前存储这个值。因此，可以通过动态编程解决
     */
    int trap2(int[] height) {
        if (height == null)
            return 0;

        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];

        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = max(height[i], left_max[i - 1]);
        }

        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = max(height[i], right_max[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += min(left_max[i], right_max[i]) - height[i];
        }

        return ans;
    }

    /**
     * 我们可以不用像方法 2 那样存储最大高度，而是用栈来跟踪可能储水的最长的条形块。
     * 使用栈就可以在一次遍历内完成计算。
     * <p>
     * 我们在遍历数组时维护一个栈。
     * 如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
     * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
     * 因此我们可以弹出栈顶元素并且累加答案到 \text{ans}ans
     */
    int trap3(int[] height) {

        int ans = 0, current = 0;
        LinkedList<Integer> st = new LinkedList<>();

        while (current < height.length) {
            while (!st.isEmpty() && height[current] > height[st.peekFirst()]) {
                int top = st.peekFirst();
                st.pop();
                if (st.isEmpty())
                    break;

                int distance = current - st.peekFirst() - 1;
                int bounded_height = min(height[current], height[st.peekFirst()]) - height[top];
                ans += distance * bounded_height;
            }

            st.push(current++);
        }

        return ans;
    }

    int trap4(int[] height) {

        if (height == null || height.length <= 2) {
            return 0;
        }

        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int count = 0;

        while (left < right) {

            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    count += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    count += rightMax - height[right];
                }
                right--;
            }
        }

        return count;
    }

}
