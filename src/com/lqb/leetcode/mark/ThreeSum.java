package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ThreeSum {

    @Test
    public void test1() {
        ThreeSum demo = new ThreeSum();
        System.out.println(demo.threeSum2(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}));
        System.out.println(demo.threeSum2(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(demo.threeSum2(new int[]{
                -12, -1, 4, -14, 0, 10, 7, -7, -6, 9, 6, -2, 7, 13, 9, -1, 4, 12, 9, 4, 14, 0, -4, 0, 0, 10, 2, -7, 7,
                -4, -11, 10, 2, 8, 4, -12, -4, -12, -4, -3, 12, 9, 11, 4, -1, -3, 10, -12, -6, -4, -1, -14, 3,
                2, -7, -11, -3, 10, -11, -10, 13, -15, 7, 0, 0, -4, -5, 11, 0, -2, -14, -11, -8, 12, 1, -1, -14,
                -12, -6, -5, 0, 9, -4, -12, -4, 4, 14, 9, -9, 10, 14, -11, 10, 6, -3, -4, 10, -1, 14, -13, 13,
                7, -9, 12, 4, -1, -4, 5, 3, 6, 8, 10, 0, 11, 13, 11, -7, 5, -3, -1, 0, -4, -4, -4, 10, 0
        }));
    }

    /**
     * @description Map缓存两数相加的结果
     * @author liqibo
     * @date 2019/6/27 15:52
     **/
    public List<List<Integer>> threeSum(int[] nums) {

        HashMap<Integer, Integer> indexMap = new HashMap<>(nums.length);
        HashSet<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {

                int twoSum = nums[i] + nums[j];
                int need = 0 - twoSum;
                if (indexMap.containsKey(need)) {
                    int index = indexMap.get(need);
                    if (index != i && index != j) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], need);
                        Collections.sort(list);
                        res.add(list);
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }

    /**
     * @description 先排序，固定一个数A，左指针指向A的下一个数B，右指针指向最后一个C。
     * 如果A + B + C > 0 则右指针往左移动；反之左指针往右移动
     * 注意移动的同时，如果还是同一个数则可以不用比较直接跳过
     * @author liqibo
     * @date 2019/6/27 17:38
     **/
    public List<List<Integer>> threeSum2(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            //当前固定的数也需要去重(这个去重容易忘记)
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                //如果最左两个数比较小，则右边递减
                if (nums[r] > -(nums[i] + nums[l])) {
                    //右边去重
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                } else if (nums[r] < -(nums[i] + nums[l])) {
                    //左边去重
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;
                    r--;
                }
            }

            //这个去重放在循环一开始了
            // while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
            //     i++;
            // }
        }

        return ans;
    }


}
