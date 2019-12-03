package com.lqb.leetcode.mark;

import com.lqb.util.TreeNode;
import org.junit.Test;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BinaryTreeMaximumPathSum {

    @Test
    public void test0() {
        TreeNode t1 = new TreeNode(-1);
        //-1
        System.out.println(maxPathSum2(t1));
    }

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.left = t2;
        t1.right = t3;

        //6
        System.out.println(maxPathSum2(t1));
    }

    @Test
    public void test2() {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;

        t3.left = t4;
        t3.right = t5;

        //42
        System.out.println(maxPathSum2(t1));
    }

    private int maxPath = Integer.MIN_VALUE;
    private int tmp = 0;

    /**
     * @author liqibo
     * @date 2019/12/3 19:19
     * @description 自己的解法：暴力法(没有解决nums=[-1]这种情况)
     * 联想：类似数组的最大的累加和
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        maxPathSumCore(root);

        return maxPath;
    }

    private void maxPathSumCore(TreeNode root) {

        if (root == null) {
            maxPath = Math.max(maxPath, tmp);
            return;
        }

        maxPath = Math.max(maxPath, tmp);
        tmp += root.val;
        if (tmp < 0) {
            if (root.val > 0) {
                Math.max(maxPath, root.val);
                tmp = root.val;
            } else {
                tmp = 0;
            }
            maxPathSumCore(root.left);
            tmp = 0;
            maxPathSumCore(root.right);
        } else {
            maxPathSumCore(root.left);
            if (tmp < 0) {
                tmp = 0;
            }
            maxPathSumCore(root.right);
        }
    }

    /**
     * @author liqibo
     * @date 2019/12/3 20:12
     * @description 官方解法
     */
    public int maxPathSum2(TreeNode root) {
        max_gain(root);
        return maxPath;
    }
    private int max_gain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 先递归求最左节点
        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);

        // the price to start a new path where `node` is a highest node
        int price_newpath = node.val + left_gain + right_gain;

        // update max_sum if it's better to start a new path
        maxPath = Math.max(maxPath, price_newpath);

        // for recursion :
        // return the max gain if continue the same path
        // 只返回一条路径
        return node.val + Math.max(left_gain, right_gain);
    }

    //联想数组
    @Test
    public void testMaxSum() {
        int[] nums1 = new int[]{-1, 2, 3, -1, -4, -5, 1, 5};
        System.out.println(maxSum(nums1));
    }

    public int maxSum(int[] nums) {
        int max = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = max(max, sum, nums[i]);
            if (sum < 0) {
                if (nums[i] > 0) {
                    sum = nums[i];
                } else {
                    sum = 0;
                }
            }
        }

        return max;
    }

    private int max(int a1, int a2, int a3) {
        return a1 > a2 ? (a1 > a3 ? a1 : a3) : (a2 > a3 ? a2 : a3);
    }

}
