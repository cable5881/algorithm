package com.lqb.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqibo
 * @date 2020/3/3 16:29
 *
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
 * 我们将这些回答放在 answers 数组里。
 * 返回森林中兔子的最少数量。
 *
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 *
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 *
 * 输入: answers = []
 * 输出: 0
 *
 * 说明:
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 **/
public class RabbitsInForest {

    @Test
    public void test() {
        System.out.println(numRabbits(new int[]{1, 1, 2}));
        System.out.println(numRabbits(new int[]{10, 10, 10}));
        System.out.println(numRabbits(new int[]{1, 0, 1, 0, 0}));
    }

    public int numRabbits(int[] answers) {
        if (answers == null || answers.length <= 0) {
            return 0;
        }

        int res = 0;

        Map<Integer, Integer> mappings = new HashMap<>();

        for (int answer : answers) {
            //第一次写漏了0的情况
            if (answer == 0) {
                res++;
            } else if (mappings.containsKey(answer)) {
                Integer num = mappings.get(answer) + 1;
                if (num == answer + 1) {
                    res += (answer + 1);
                    mappings.remove(answer);
                } else {
                    mappings.put(answer, num);
                }
            } else {
                mappings.put(answer, 1);
            }
        }

        if (!mappings.isEmpty()) {
            for (Integer key : mappings.keySet()) {
                res += (key + 1);
            }
        }

        return res;
    }
}
