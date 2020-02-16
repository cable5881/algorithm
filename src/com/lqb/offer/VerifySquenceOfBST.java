package com.lqb.offer;

import org.junit.Test;

/**
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST {

    @Test
    public void test() {
        System.out.println(verifySquenceOfBST2(new int[]{2, 4, 3, 6, 8, 7, 5}));
        System.out.println(verifySquenceOfBST2(new int[]{2, 4, 3, 8, 6, 7, 5}));//错误序列
        System.out.println(verifySquenceOfBST2(new int[]{4, 3, 6, 8, 7, 5}));//左子树的左叶子节点缺失
        System.out.println(verifySquenceOfBST2(new int[]{2, 4, 3, 6, 7, 5}));//右子树的右叶子节点缺失
        System.out.println(verifySquenceOfBST2(new int[]{3, 5}));//缺右子树
        System.out.println(verifySquenceOfBST2(new int[]{7, 5}));//缺左子树
    }

    public boolean verifySquenceOfBST(int[] sequence) {

        if (sequence == null || sequence.length == 0) {
            return false;
        }

        return verify(sequence, 0, sequence.length - 1);

    }

    private boolean verify(int[] sequence, int start, int end) {

        if (end <= start) {
            return true;
        }

        int rootVal = sequence[end];

        int rChildIndex = getChildIndex(sequence, start, end);

        for (int i = rChildIndex; i < end; i++) {
            if (sequence[i] < rootVal) {
                return false;
            }
        }

        boolean isLChildBST = verify(sequence, start, rChildIndex - 1);
        boolean isRChildBST = verify(sequence, rChildIndex, end - 1);

        return isLChildBST && isRChildBST;
    }

    private int getChildIndex(int[] sequence, int start, int end) {

        int root = sequence[end];

        int rTreeStart = start;

        while (rTreeStart < end) {
            if (sequence[rTreeStart] > root) {
                break;
            }
            rTreeStart++;
        }

        return rTreeStart;
    }

    /**
     * 多年后的尝试
     */
    public boolean verifySquenceOfBST2(int[] a) {
        if (a == null || a.length <= 0) {
            return false;
        }

        return verifySquenceOfBST2(a, 0, a.length - 1);
    }

    private boolean verifySquenceOfBST2(int[] a, int start, int end) {
        if (start >= end) {
            return true;
        }

        int mid = a[end];
        int i = start;

        while (i < end) {
            if (a[i] < mid) {
                i++;
            } else {
                break;
            }
        }

        //正常情况下，j是右子树的开始
        int j = i;
        while (j < end) {
            //注意等于，因为可能前面都是满足的，后面来了一个等于mid的，也是不满足的
            if (a[j++] <= mid) {
                return false;
            }
        }

        return verifySquenceOfBST2(a, start, i - 1) && verifySquenceOfBST2(a, i, end - 1);
    }
}
