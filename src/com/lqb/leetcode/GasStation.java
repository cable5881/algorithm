package com.lqb.leetcode;

import org.junit.Test;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * <p>
 * <p>
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * <p>
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class GasStation {

    @Test
    public void test() {
        GasStation demo = new GasStation();
        System.out.println(demo.canCompleteCircuit2(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(demo.canCompleteCircuit2(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(demo.canCompleteCircuit2(new int[]{2}, new int[]{2}));
        System.out.println(demo.canCompleteCircuit2(new int[]{2}, new int[]{3}));
    }

    /**
     * @description 自己的解法：从某一个汽油净流入大于0起点开始出发，不行就换下一个出发点。复杂度是O(n²)
     * @author liqibo
     * @date 2019/7/1 15:33
     **/
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] delta = new int[gas.length];

        //优化：如果到最后一步汽油净流入为正则表示可以行驶一周
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            delta[i] = gas[i] - cost[i];
            sum += delta[i];
        }

        if (sum < 0) {
            return -1;
        }

        int i = 0;
        while (i < delta.length) {
            if (delta[i] >= 0) {
                int res = travel(delta, i);
                if (res != -1) {
                    return res;
                }
            }
            i++;
        }

        return -1;
    }

    private int travel(int[] delta, int end) {
        int start = (end + 1) % delta.length;
        int gasLeft = delta[end];
        while (start != end) {
            gasLeft += delta[start];
            if (gasLeft < 0) {
                return -1;
            }

            start = (start + 1) % delta.length;
        }

        return start;
    }

    /**
     * @description 官方解法：一次遍历，复杂度O(n)
     * 定义三个变量：一个用来记录从0开始到某一点的汽油净流入量total_tank ；
     * 第二个记录从某个汽油净流入为正的起点开始计算累计汽油净流入量curr_tank；
     * 第三个记录有效的出发点start
     * 核心思想：如果到达数组最后一个元素时，curr_tank >= 0 则start就是一个有效的出发点（能绕一圈），根据题目是唯一的
     *
     *
     * 为什么curr_tank >= 0 则start就是一个有效的出发点呢？
     * curr_tank >= 0，说明start到数组最后一个元素时肯定没问题的，关键是从0到start这段路能顺利跑完吗？
     * 反证法 -> 假设从0到start之间存在一个k点使得从start出发绕一圈不能到达k点。
     * 则total_tank简称(TT）在0-k这段路是小于0的，即T_0~k < 0。
     * 而最终的TT又是>=0的，也就是说三段路：(TT_0~k)  + (TT_k+1~start-1) + (TT_start~N) >= 0
     * 根据算法流程，第二项为负，因为每一个出发点前面一段路途的 curr_tank 一定为负。否则，出发点应该是比 start更靠前的一个加油站
     * ∴ (TT_0~k) + (TT_start~N) >= 0 (1)
     * 又∵start出发无法到达k，∴(TT_0~k) + (TT_start~N) < 0 （2）
     * （1）和（2）矛盾
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/jia-you-zhan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @author liqibo
     * @date 2019/7/1 15:33
     **/
    public int canCompleteCircuit2(int[] gas, int[] cost) {

        int total_tank = 0;
        int curr_tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];

            if (curr_tank < 0) {
                curr_tank = 0;

                //注意这里不是i++
                start = i + 1;
            }
        }

        return total_tank >= 0 ? start : -1;
    }

}
