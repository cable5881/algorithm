package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 *
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheDuplicateNumber {

    @Test
    public void test() {
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate(new int[]{1, 2, 4, 3, 5, 2}));
    }

    /**
     * 自己的解法，时间复杂度O(n)，空间复杂度O(1)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1) {
                i++;
                continue;
            }

            if (nums[nums[i] - 1] == nums[i]) {
                return nums[i];
            }

            int tmp = nums[i];
            nums[i] = nums[tmp - 1];
            nums[tmp - 1] = tmp;
        }

        return 0;
    }

    /**
     * 官方解法，时间复杂度O(n)，空间复杂度O(1)
     * 首先，我们可以很容易地证明问题的约束意味着必须存在一个循环。
     * 因为 nums 中的每个数字都在 11 和 nn 之间，所以它必须指向存在的索引。
     * 此外，由于 00 不能作为 nums 中的值出现，nums[0] 不能作为循环的一部分。
     */
    public int findDuplicate2(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

}
