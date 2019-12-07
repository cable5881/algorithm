package com.lqb.leetcode;

import org.junit.Test;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallestElementInASortedMatrix {

    @Test
    public void test() {
        System.out.println(kthSmallest2(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15},
        }, 8));

        System.out.println(kthSmallest2(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5));

    }

    /**
     * 错误的解法：第一行和第二行交叉遍历。其实还有可能纵向遍历
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {

        if (matrix.length == 1) {
            return matrix[0][k - 1];
        }

        int R1 = 0;
        int R2 = R1 + 1;
        int C1 = 0;
        int C2 = 0;
        int i = 0;
        int res;

        while (R1 < matrix.length && R2 < matrix.length) {
            while (C1 < matrix[0].length && C2 < matrix[0].length) {
                if (matrix[R1][C1] < matrix[R2][C2]) {
                    res = matrix[R1][C1];
                    C1++;
                } else {
                    res = matrix[R2][C2];
                    C2++;
                }

                if (++i == k) {
                    return res;
                }
            }
            if (C1 >= matrix[0].length) {
                C1 = 0;
                R1 = R2 + 1;
            } else {
                C2 = 0;
                R2 = R1 + 1;
            }
        }

        if (R1 < matrix.length) {
            res = matrix[R1][C1 + k - i - 1];
        } else {
            res = matrix[R2][C2 + k - i - 1];
        }

        return res;
    }

    /**
     * 1.找出二维矩阵中最小的数left，最大的数right，那么第k小的数必定在left~right之间
     * 2.mid=(left+right) / 2；在二维矩阵中寻找小于等于mid的元素个数count
     * 3.若这个count小于k，表明第k小的数在右半部分且不包含mid，即left=mid+1, right=right，又保证了第k小的数在left~right之间
     * 4.若这个count大于k，表明第k小的数在左半部分且可能包含mid，即left=left, right=mid，又保证了第k小的数在left~right之间
     * 5.因为每次循环中都保证了第k小的数在left~right之间，当left==right时，第k小的数即被找出，等于right
     * 注意：这里的left mid right是数值，不是索引位置。
     *
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/er-fen-chao-ji-jian-dan-by-jacksu1024/
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];
        while (left < right) {
            // 每次循环都保证第K小的数在 left~right 之间，当 left==right，left
            int mid = (left + right) / 2;
            // 找二维矩阵中<=mid的元素总个数
            int count = findNotBiggerThanMid(matrix, mid, row, col);
            if (count < k) {
                // 第k小的数在右半部分，且不包含mid
                left = mid + 1;
            } else {
                // 第k小的数在左半部分，可能包含mid
                // 因为我们不知道第k小的数是不是等于mid，因此我们每次都要把mid包含进来，当left==right=mid的时候，就是第k小的数了
                right = mid;
            }
        }
        return right;
    }

    /**
     * 从左下角开始向右上角找，返回有多少个数小于mid
     */
    private int findNotBiggerThanMid(int[][] matrix, int mid, int row, int col) {
        int i = row - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < col) {
            if (matrix[i][j] <= mid) {
                // 第j列有i+1个元素<=mid
                count += i + 1;
                j++;
            } else {
                // 第j列目前的数大于mid，需要继续在当前列往上找
                i--;
            }
        }
        return count;
    }

}
