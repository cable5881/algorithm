package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 *
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class FindPeakElement {

    @Test
    public void test() {
        FindPeakElement demo = new FindPeakElement();
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 1, 3, 5, 6, 4})); //1或5
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 4, 3, 5, 6, 4})); //2或5
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 3, 4, 5, 6, 4})); //5或5
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 3, 5, 6, 4})); //4
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 3, 1}));//2
        System.out.println(demo.findPeakElement2(new int[]{1, 2, 3, 4}));//3
        System.out.println(demo.findPeakElement2(new int[]{1,2,3,1}));//2
    }

    /**
     * 自己的解法, 递归二分查找, 时间和空间复杂度都是log(n)
     **/
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        if (nums.length == 1) {
            return 0;
        }

        int result = findPeakElement(nums, 0, nums.length - 1);

        if (result == -1) {
            return nums[0] > nums[nums.length - 1] ? 0 : nums.length - 1;
        }

        return result;
    }

    private int findPeakElement(int[] nums, int start, int end) {
        if (end - start < 2) {
            return -1;
        }

        int mid = (start + end) / 2;

        //中间大于两边，存在峰值，直接返回
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        }

        //左边是否小于中间(上升趋势)?
        //是,则mid - 1, 因为mid-1不可能是峰值.
        //否(下降趋势),则mid, 因为mid-1有可能是峰值,需要将mid-1纳入下一次计算范围中
        int left = nums[mid] > nums[mid - 1] ? findPeakElement(nums, start, mid - 1) : findPeakElement(nums, start, mid);

        if (left > -1) {
            return left;
        }

        return nums[mid] > nums[mid + 1] ? findPeakElement(nums, mid + 1, end) : findPeakElement(nums, mid, end);
    }

    /**
     * 官方解法：迭代二分查找
     * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
     * 根据上述结论，我们就可以使用二分查找找到峰值
     * 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
     * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
     **/
    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            //如果中间大于右边, 说明下降趋势, 左边存在峰值
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                //上升趋势, 右边存在峰值。
                //这里注意mid必须要 + 1， 否则死循环。考虑[1,2,3]的情况
                left = mid + 1;
            }
        }
        return left;
    }

}
