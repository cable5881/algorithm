package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LargestNumber {

    @Test
    public void test() {
        System.out.println(largestNumber(new int[]{10, 2}));

        //128比12大
        System.out.println(largestNumber(new int[]{128, 12}));

        //3比30大
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));

        //12比121大，
        System.out.println(largestNumber(new int[]{121, 12}));

        //输出0
        System.out.println(largestNumber(new int[]{0, 0}));

        //如果是83和8则应该是883
        //如果是883和8则应该是8883
        System.out.println(largestNumber(new int[]{883, 8}));
    }

    /**
     * @author liqibo
     * @date 2019/11/25 19:41
     * @description 自己的解法，错了很多次
     * 将nums数组转为字符串数组后，主要是字符串数据的排序
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        String[] ns = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(ns, (o1, o2) -> compare(o1, o2));

        StringBuilder sb = new StringBuilder();
        for (int i = ns.length - 1; i >= 0; i--) {
            sb.append(ns[i]);
        }

        String res = sb.toString();
        return res.startsWith("0") ? "0" : res;
    }

    /**
     * @author liqibo
     * @date 2019/11/25 19:43
     * @description 排序规则
     */
    private int compare(String o1, String o2) {
        String longer;
        String shorter;
        //如果o1是longer则reverse为false
        //如果o2是longer则reverse为true
        boolean reverse = false;
        if (o1.length() > o2.length()) {
            longer = o1;
            shorter = o2;
        } else {
            longer = o2;
            shorter = o1;
            reverse = true;
        }

        //这里和String的compare是相同的
        int i = 0;
        while (i < shorter.length()) {
            if (o1.charAt(i) > o2.charAt(i)) {
                return 1;
            } else if (o1.charAt(i) < o2.charAt(i)) {
                return -1;
            } else if (i == o1.length() - 1 && i == o2.length() - 1) {
                return 0;
            }
            i++;
        }

        //到这里说明两个字符串长度不等，且短的是长的前缀，即longer.startWith(shorter)==true，如121和12
        //此时需要将121的12割去，剩下1和12比，重复此步骤，是递归
        //因为121和12拼接后，无论是12112还是12121，他们拥有共同前缀121，因此需要将他们去除共同前缀来接着相比
        int res = compare(longer.substring(i, longer.length()), shorter);
        return !reverse ? res : -res;
    }

    /**
     * @author liqibo
     * @date 2019/11/25 19:39
     * @description 官方解法
     * 更加简单，直接拼接a和b
     */
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            //注意这里反过来
            return order2.compareTo(order1);
        }
    }

    public String largestNumber2(int[] nums) {
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(asStrs, new LargerNumberComparator());

        if (asStrs[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String numAsStr : asStrs) {
            sb.append(numAsStr);
        }

        return sb.toString();
    }

}
