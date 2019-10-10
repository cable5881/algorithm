package com.lqb.leetcode.mark;

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
        //6
        System.out.println(trap5(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

        //3
        System.out.println(trap5(new int[]{2,1,0,2}));
    }


    /**
     * 官方解法1：暴力法。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
     * 时间复杂度：O(n²)，空间复杂度：O(1)
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
     * 官方解法2：
     * 时间复杂度：O(n)，空间复杂度：O(n)
     * 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。
     * 但是我们可以提前存储这个值。因此，可以通过动态编程解决
     */
    int trap2(int[] height) {
        if (height == null) {
            return 0;
        }

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
     * 官方解法3：
     * 时间复杂度：O(n)，因为每个bar访问两次（压栈和出栈）。空间复杂度：O(n)
     * 我们可以不用像方法 2 那样存储最大高度，而是用栈来跟踪可能储水的最长的条形块。
     * 使用栈就可以在一次遍历内完成计算。
     * 我们在遍历数组时维护一个栈。
     * 如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
     * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
     * 因此我们可以弹出栈顶元素并且累加答案到 ans
     */
    int trap3(int[] height) {

        int ans = 0, cur = 0;
        LinkedList<Integer> st = new LinkedList<>();

        while (cur < height.length) {
            //注意这个循环条件，只有当前bar大于最近的bar才有意义，表示可以计算雨水
            //注意：当出现一个较长bar，遍历栈，比当前bar小的都要出栈。
            //只需要保留当前bar和往左比它长的bar就可以了，因为较短bar在下面的循环中已经计算完毕
            //后面的计算不需要用到他们
            while (!st.isEmpty() && height[cur] > height[st.peekFirst()]) {
                int top = st.pop();

                //后面需要访问栈顶元素，也就是最近的靠左的一个bar，如果为空后面没办法计算了，直接退出
                if (st.isEmpty()) {
                    break;
                }

                int distance = cur - st.peekFirst() - 1;
                int bounded_height = min(height[cur], height[st.peekFirst()]) - height[top];
                ans += distance * bounded_height;
            }

            st.push(cur++);
        }

        return ans;
    }

    /**
     * 官方解法4：
     * 和方法 2 相比，我们不从左和从右分开计算，我们想办法一次完成遍历。
     * 从动态编程方法的示意图中我们注意到，只要 right_max[i] > left_max[i]（元素 0 到元素 6），
     * 积水高度将由 left_max 决定，类似地 left_max[i] > right_max[i]（元素 8 到元素 11）。
     * 所以我们可以认为如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。
     * 当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从相反的方向遍历（从右到左）。
     * 我们必须在遍历时维护 left_max 和 right_max，但是我们现在可以使用两个指针交替进行，
     * 实现 1 次遍历即可完成。
     */
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

    /**
     * @author liqibo
     * @date 2019/10/10 14:40
     * @description
     */
    public int trap5(int[] height) {

        int i = 0;
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();

        while (i < height.length) {
            while (!stack.isEmpty() && height[i] > height[stack.peekFirst()]) {
                Integer top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }

                int w = i - stack.peekFirst() - 1;
                int h = Math.min(height[i], height[stack.peekFirst()]) - height[top];
                ans += w * h;
            }

            stack.push(i++);
        }


        return ans;
    }

}
