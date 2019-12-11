package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 * 示例 1：
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 *
 * 示例 2：
 * 输入：K = 2, N = 6
 * 输出：3
 *
 * 示例 3：
 * 输入：K = 3, N = 14
 * 输出：4
 *  
 *
 * 提示：
 *
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SuperEggDrop {

    @Test
    public void test() {
        System.out.println(superEggDrop_w2(1, 2));//2
        System.out.println(superEggDrop_w2(1, 3));//3
        System.out.println(superEggDrop_w2(2, 6));//3
        System.out.println(superEggDrop_w2(2, 7));//4
        System.out.println(superEggDrop_w2(3, 14));//4
        System.out.println(superEggDrop_w2(3, 26));//6
        //System.out.println(superEggDrop_w2(4, 10000));//23
        //System.out.println(superEggDrop_w2(10, 10000));//14
    }

    //错误解法1
    public int superEggDrop_w1(int K, int N) {
        if (N == 2) {
            return 2;
        } else if (N == 1) {
            return 1;
        } else if (N == 0) {
            return 0;
        }

        return superEggDrop_w1(K - 1, (N + 1) / 2) + 1;
    }

    /**
     * @author liqibo
     * @date 2019/11/21 16:13
     * @description 错误解法2
     * 往上走或往下走，每次走剩下楼层的 1/2层
     */
    public int superEggDrop_w2(int K, int N) {
        return superEggDrop_w2(K, 1, N);
    }
    private int superEggDrop_w2(int k, int i, int j) {
        //i 是开始楼层，j是结束楼层
        if (i > j) {
            return 0;
        }

        if (k == 1) {
            return j - i + 1;
        }

        int mid = (i + j) / 2;

        return Math.max(superEggDrop_w2(k - 1, i, mid - 1) + 1, superEggDrop_w2(k, mid + 1, j) + 1);
    }

    /**
     * 暴力法
     */
    public int superEggDrop1(int K, int N) {
        if (N <= 1 || K == 1) {
            return N;
        }

        int min = N;
        //从1楼开始丢鸡蛋丢到N楼
        for (int i = 1; i <= N; i++) {
            //如果i楼鸡蛋破了则往下一层楼看看，如果没破则上一层看看
            //求出至少需要移动的次数
            int tmp = Math.max(superEggDrop1(K - 1, i - 1), superEggDrop1(K, N - i));
            //然后取最小
            min = Math.min(tmp + 1, min);
        }

        return min;
    }

    /**
     * 动态规划法0：使用一个二维数组来记录不同的K和N下至少需要移动的次数
     * （空间复杂度O(KN)），使得时间复杂度降低到了O(KN^2)
     */
    public int superEggDrop2(int K, int N) {
        int[][] middleResults = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            // 只有1个鸡蛋的情况
            middleResults[1][i] = i;
            // 没有鸡蛋的情况
            middleResults[0][i] = 0;
        }
        //for (int i = 1; i <= K; i++) {
        //    // 0层大楼
        //    middleResults[i][0] = 0;
        //}

        //可以看到这里没有用递归，因此要计算所有情况
        // 从2个鸡蛋开始
        for (int k = 2; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                int tMinDrop = Integer.MAX_VALUE;
                //n层高的大楼，鸡蛋从1~n开始扔
                for (int x = 1; x <= n; x++) {
                    tMinDrop = Math.min(tMinDrop, 1 + Math.max(middleResults[k - 1][x - 1], middleResults[k][n - x]));
                }

                //到这里我们就能得出n层楼下的至少移动次数
                middleResults[k][n] = tMinDrop;
            }
        }

        return middleResults[K][N];
    }

    Map<Integer, Integer> dp = new HashMap();
    /**
     * 动态规划法1：使用一个Map来记录不同的K和N下至少需要移动的次数
     * 在测试用例4, 10000下会栈溢出
     */
    public int superEggDrop3(int K, int N) {
        int key = getKey(K, N);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int ans;
        if (N <= 1 || K == 1) {
            ans = N;
        } else {
            ans = N;
            //从1楼开始丢鸡蛋丢到N楼
            for (int i = 1; i <= N; i++) {
                //如果i楼鸡蛋破了则往下一层楼看看，如果没破则上一层看看
                //求出至少需要移动的次数
                int tmp = Math.max(superEggDrop3(K - 1, i - 1), superEggDrop3(K, N - i));
                //然后取最小
                ans = Math.min(tmp + 1, ans);
            }

        }

        dp.put(key, ans);
        return dp.get(key);
    }
    private int getKey(int K, int N) {
        //N * 100 + K 是用来将N和K合并成一个KEY的
        return N * 100 + K;
    }

    /**
     * 动态规划法2：优化，不用每一层都去尝试丢鸡蛋，使用二分法减少递归次数
     */
    public int superEggDrop4(int K, int N) {
        int key = getKey(K, N);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int ans = 0;
        if (N <= 1 || K == 1) {
            ans = N;
        } else {

            int l = 1;
            int r = N;
            int m;
            ans = N;

            while (l < r) {
                m = (r - l) / 2 + l;
                int t1 = superEggDrop4(K - 1, m - 1);
                int t2 = superEggDrop4(K, N - m);

                if (t1 > t2) {
                    l = m;
                } else if (t1 < t2) {
                    r = m;
                } else {
                    l = r = m;
                }

            }

            //ans = Math.min(ans, superEggDrop2())

        }

        dp.put(key, ans);
        return dp.get(key);
    }

}
