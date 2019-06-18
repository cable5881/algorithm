package com.lqb.leetcode;

import org.junit.Test;

/**
 * @Description 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * @Author liqibo
 * @Date 2019/6/14 17:43
 **/
public class MaxArea {

    @Test
    public void test() {
        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        MaxArea maxArea = new MaxArea();
        System.out.println(maxArea.maxArea(ints));
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int start = 0;
        int end = height.length - 1;
        int maxSize = 0;

        while (start < end) {
            int size = getSize(height, start, end);
            maxSize = maxSize > size ? maxSize : size;

            if(height[start] > height[end]) {
                end--;
            } else {
                start++;
            }
        }

        return maxSize;
    }

    private int getSize(int[] height, int start, int end) {
        return (end - start) * Math.min(height[start], height[end]);
    }


}
