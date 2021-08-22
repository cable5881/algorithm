package com.lqb.offer.mark;

/**
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {

    public static void main(String[] args) {
        System.out.println("1:  " + findMin(new int[]{1}));
        System.out.println("2:  " + findMin(new int[]{1,3}));
        System.out.println("3:  " + findMin(new int[]{3,1}));
        System.out.println("4:  " + findMin(new int[]{3,1,2}));
        System.out.println("5:  " + findMin(new int[]{1,2,3}));
        System.out.println("6:  " + findMin(new int[]{4,6,8,9,10,1,2,3}));
        System.out.println("7:  " + findMin(new int[]{8,9,10,1,2,3,4,6}));
        System.out.println("8:  " + findMin(new int[]{9,10,1,2,3,4,6,8}));
        System.out.println("9:  " + findMin(new int[]{6,8,9,10,1,2,3,4}));
        System.out.println("10:  " + findMin(new int[]{1,2,3,4,6,8,9,10}));
        System.out.println("11:  " + findMin2(new int[]{2,2,2,1,2}));
    }

    /**
     * [8,9,10,1,2,3,4,6] mid=1 < left && mid < right >>> right=mid
     * [9,10,1,2,3,4,6,8] mid=2 < left && mid < right >>> right=mid
     * [1,2,3,4,6,8,9,10] mid=4 > left && mid < right >>> right=mid
     * [4,6,8,9,10,1,2,3] mid=9 > left && mid > right >>> left=mid
     * [6,8,9,10,1,2,3,4] mid=10 > left && mid > right >>> left=mid
     * 可以总结出规律，凡是nums[mid] < nums[right]，都是需要right=mid的
     */
    public static int findMin(int[] nums) {

        if (nums.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                //这里注意需要mid + 1。不然像[3,1]这种会一直死循环
                left = mid + 1;
            }
        }

        return nums[right];
    }

    /**
     * 考虑重复的情况
     * [2,2,2,1,2]
     *
     * 实际提示复杂度有点高
     */
    public static int findMin2(int[] nums) {

        if (nums.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            //考虑提前退出
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = (left + right) / 2;
            if (mid > left && nums[mid] == nums[left]) {
                left = filterDuplicate(nums, left, mid);
            } else if (mid < right && nums[mid] == nums[right]) {
                right = filterDuplicate(nums, mid, right);
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                //这里注意需要mid + 1。不然像[3,1]这种会一直死循环
                left = mid + 1;
            }
        }

        return nums[right];
    }

    private static int filterDuplicate(int[] nums, int start, int end) {
        int i = start;
        while (i < end) {
            if (nums[i] == nums[end]) {
                i++;
            } else {
                return i;
            }
        }

        return end;
    }

    //官方解法
    public static int findMin3(int[] nums) {

        if (nums.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            //考虑提前退出
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]){
                //这里注意需要mid + 1。不然像[3,1]这种会一直死循环
                left = mid + 1;
            } else {
                //考虑重复的情况
                //1 0 1 1 1
                //1 1 1 0 1
                //这种情况，不能确定答案在左边还是右边，那么就让 right = right - 1;慢慢缩少区间，同时也不会错过答案。
                right--;
            }
        }

        return nums[right];
    }
}
