package com.lqb.leetcode.mark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ShuffleAnArray {

    int[] origin;
    int[] array;

    Random r = new Random();

    public ShuffleAnArray(int[] nums) {
        origin = nums.clone();
        array = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = origin.clone();
        return array;
    }

    /** 时间复杂度O(n²) */
    public int[] shuffle() {
        ArrayList<Integer> originList = new ArrayList<>(origin.length);
        for (int i = 0; i < array.length; i++) {
            originList.add(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            int randomN = r.nextInt(originList.size());
            Integer remove = originList.remove(randomN);
            array[i] = remove;
        }

        return array;
    }

    /** 时间复杂度O(n) */
    public int[] shuffle2() {
        for (int i = 0; i < array.length; i++) {
            int randomN = r.nextInt(array.length - i) + i;
            int temp = array[randomN];
            array[randomN] = array[i];
            array[i] = temp;
        }

        return array;
    }

    public static void main(String[] args) {
        ShuffleAnArray demo = new ShuffleAnArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(Arrays.toString(demo.shuffle2()));
        System.out.println(Arrays.toString(demo.reset()));
        System.out.println(Arrays.toString(demo.shuffle2()));
        System.out.println(Arrays.toString(demo.reset()));
    }
}
