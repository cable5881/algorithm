package com.lqb.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 *
 * 提示：
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KClosestPointsToOrigin {

    @Test
    public void test() {
        KClosestPointsToOrigin demo = new KClosestPointsToOrigin();
        //print(demo.kClosest2(new int[][] {{3,3},{5,-1},{-2,4}}, 2));
        //print(demo.kClosest2(new int[][] {{1,3},{-2,-2}}, 1));
        //print(demo.kClosest3(new int[][] {{1,3},{-2,-2}}, 1));
        //print(demo.kClosest3(new int[][] {{3,3},{5,-1},{-2,4}}, 1));
        print(demo.kClosest3(new int[][] {{0,1},{1,0}}, 2));
    }

    private void print(int[][] nums) {
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
        System.out.println("###############################");
    }

    /**
     * 采用最小堆, 时间复杂度O(nlogn), 空间复杂度O(n)
     */
    public int[][] kClosest(int[][] points, int K) {
        if (points == null) {
            return null;
        }

        PriorityQueue<int[]> dump = new PriorityQueue<>(points.length, (Comparator.comparingInt(o -> dist(o[0], o[1]))));

        for (int[] point : points) {
            dump.add(point);
        }

        int[][] ans = new int[K][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = dump.remove();
        }

        return ans;
    }

    private int dist(int x, int y) {
        return x * x + y * y;
    }

    /**
     * 还是使用大小堆, 但是封装了到原点的距离, 避免每次计算
     */
    public int[][] kClosest2(int[][] points, int K) {
        if (points == null) {
            return null;
        }

        PriorityQueue<Point> dump = new PriorityQueue<>(points.length, (Comparator.comparingInt(o -> o.des)));

        for (int[] point : points) {
            dump.add(new Point(point[0], point[1]));
        }

        int[][] ans = new int[K][2];
        for (int i = 0; i < ans.length; i++) {
            Point p = dump.remove();
            ans[i][0] = p.x;
            ans[i][1] = p.y;
        }

        return ans;
    }

    /**
     * 官方解法: 分治法
     * 时间复杂度：O(N) ，这是在平均情况下 的时间复杂度， 其中 N 是给定点的数量。
     * 空间复杂度：O(N)
     *
     *
     * 思路
     *
     * 我们想要一个复杂度比 N \log NNlogN 更低的算法。 显然，做到这件事情的唯一办法就是利用题目中可以按照任何顺序返回 K 个点的条件，否则的话，必要的排序将会话费我们 NlogN 的时间。
     * 我们随机地选择一个元素 x = A[i] 然后将数组分为两部分： 一部分是到原点距离小于 x 的，另一部分是到原点距离大于等于 x 的。 这个快速选择的过程与快速排序中选择一个关键元素将数组分为两部分的过程类似。
     * 如果我们快速选择一些关键元素，那么每次就可以将问题规模缩减为原来的一半，平均下来时间复杂度就是线性的。
     *
     * 算法
     *
     * 我们定义一个函数 work(i, j, K)，它的功能是部分排序 (points[i], points[i+1], ..., points[j]) 使得最小的 K 个元素出现在数组的首部，也就是 (i, i+1, ..., i+K-1)。
     * 首先，我们从数组中选择一个随机的元素作为关键元素，然后使用这个元素将数组分为上述的两部分。为了能使用线性时间的完成这件事，我们需要两个指针 i 与 j，然后将它们移动到放错了位置元素的地方，然后交换这些元素。
     * 然后，我们就有了两个部分 [oi, i] 与 [i+1, oj]，其中 (oi, oj) 是原来调用 work(i, j, K) 时候 (i, j) 的值。假设第一部分有 10 个元，第二部分有15 个元素。如果 K = 5 的话，我们只需要对第一部分调用 work(oi, i, 5)。否则的话，假如说 K = 17，那么第一部分的 10 个元素应该都需要被选择，我们只需要对第二部分调用 work(i+1, oj, 7) 就行了。
     *
     */
    public int[][] kClosest3(int[][] points, int K) {
        work(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void work(int[][] points, int i, int j, int K) {
        if (i >= j) {
            return;
        }

        int oi = i, oj = j;
        int pivot = dist(points, ThreadLocalRandom.current().nextInt(i, j));

        while (i < j) {
            while (i < j && dist(points, i) <= pivot) {
                i++;
            }
            while (i < j && dist(points, j) >= pivot) {
                j--;
            }
            swap(points, i, j);
        }

        if (K <= i - oi + 1) {
            work(points, oi, i, K);
        } else {
            work(points, i+1, oj, K - (i - oi + 1));
        }
    }

    public int dist(int[][] points, int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int[][] points, int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }

}

class Point{
    int x;
    int y;
    int des;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.des = x * x + y * y;
    }
}