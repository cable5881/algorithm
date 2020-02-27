package com.lqb.offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
 * 输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2020/2/26 16:20
 **/
public class Dice {

    @Test
    public void test() {
        System.out.println(Arrays.toString(twoSum2(1)));
        System.out.println(Arrays.toString(twoSum2(2)));
        System.out.println(Arrays.toString(twoSum2(3)));
    }

    /**
     * @author liqibo
     * @date 2020/2/26 16:40
     * @description 方法一，统计出每个点数出现的次数，然后分别除以总次数即可得到概率
     */
    public double[] twoSum(int n) {
        if (n <= 0) {
            return new double[0];
        }

        //注意长度是5 * n + 1，因为2个骰子时，1的点数是取不到的，同理3个骰子取不到2的点数。
        //1~6 = 6
        //2~12 = 11
        //3~18 = 16
        double[] res = new double[5 * n + 1];

        twoSum(n, res, 0, 0);

        //统计次数
        //int sum = 0;
        //for (double re : res) {
        //    sum += Double.valueOf(re).intValue();
        //}

        //实际上可以用数学公式，1个骰子有6面，6种点数，2个骰子即6*6...以此类推
        int sum = Double.valueOf(Math.pow(6, n)).intValue();

        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] / sum;
        }

        return res;
    }

    private void twoSum(int n, double[] res, int start, int sum) {
        if (start == n) {
            //注意数组下标的取值。因为数组是从0开始的，所以需要减1.
            //又因为当2个骰子时，数组长度是11，点数12实际下标是11-1=10
            //当3个骰子时，数组长度是16，点数18实际下标是16-2=14
            //当4个骰子时，数组长度是21，点数24实际下标是21-3=18
            res[sum - (n - 1) - 1] = res[sum - (n - 1) - 1] + 1;
            return;
        }

        for (int i = 1; i <= 6; i++) {
            twoSum(n, res, start + 1, sum + i);
        }
    }

    /**
     * 方法二：考虑用两个数组来存储骰子点数的每一个总数出现的次数。
     * 在一次循环中，第一个数组中的第 n 个数字表示骰子和为s出现的次数。
     * 在下一循环中，我们加上一个新的骰子，此时和为 s 的骰子出现的次数应该
     * 等于上一次循环中骰子点数和为 s-1、s-2、s-3、s-4、s-5与 s-6的次数的总和，
     * 所以我们把另一个数组的第 n个数字设为前一个数组对应的第 s-1、s-2、s-3、s-4、s-5与 s-6之和
     *
     * 可以参数：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/di-gui-huo-zhe-die-dai-du-ke-yi-python-and-javascr/
     */
    public double[] twoSum2(int n) {
        if (n <= 0) {
            return new double[0];
        }

        double[][] dp = new double[n + 1][n * 6 + 1];

        //第一个的骰子每个点数出现次数都为1
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        //从第二个骰子开始到第n个骰子
        for (int i = 2; i <= n; i++) {
            //第i个骰子的最低点数为i，最高点数为i * 6. 如果第2个骰子，那么两个骰子的最低点数为2，最高点数为12
            for (int j = i; j <= i * 6; j++) {
                //当前点数 = 上一个骰子的连续6个骰子的点数之和
                for (int k = 1; k <= 6; k++) {
                    //注意数据越界问题
                    if ((j - k) <= 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k];

                    //那第2个骰子举例
                    //点数2的次数=1
                    //dp[2][2] += dp[1][2-1=1]=1
                    //         += dp[1][2-2=0] 不允许

                    //点数3的次数=2
                    //dp[2][3] += dp[1][3-1=2]=1
                    //dp[2][3] += dp[1][3-2=1]=1
                    //dp[2][3] += dp[1][3-3=0] 不允许
                    //.....

                    //点数8的次数=5
                    //dp[2][8] += dp[1][8-1=7]=0
                    //dp[2][8] += dp[1][8-2=6]=1
                    //dp[2][8] += dp[1][8-3=5]=1
                    //...
                    //dp[2][8] += dp[1][8-3=2]=1

                    //点数12的次数=1
                    //dp[2][12] += dp[1][12-1=11]=0
                    //dp[2][12] += dp[1][12-2=10]=0
                    //dp[2][12] += dp[1][12-3=9]=0
                    //...
                    //dp[2][12] += dp[1][12-6=6]=1
                }
            }
        }

        double sum = Math.pow(6, n);
        double[] res = new double[5 * n + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = dp[n][n + i] / sum;
        }

        return res;
    }

}
