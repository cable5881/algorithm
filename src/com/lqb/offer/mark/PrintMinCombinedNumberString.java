
package com.lqb.offer.mark;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * @author:JackBauer
 * @date:2016年6月16日 下午8:00:03
 */
public class PrintMinCombinedNumberString {

    @Test
    public void test() {
        System.out.println(PrintMinNumber(new int[]{3334, 3, 3333332}));
        System.out.println(PrintMinNumber(new int[]{4, 32, 321}));
    }

    /**
     * 利用每两个元素拼接后比较的形式来排序
     * 如3334和3，拼接后是33343和33334，前者比较大，那么排序上3排在3334前面。
     * 又如3332和3，拼接后是33323和33332，后者比较大，那么排序上3332排在3前面。
     * 也正是因为可能出现上面两种情况，所以需要通过拼接的形式来比较。
     */
    public String PrintMinNumber(int[] numbers) {

        if (numbers == null || numbers.length == 0) {
            return "";
        }

        ArrayList<String> stringList = new ArrayList<>(numbers.length);

        for (int number : numbers) {
            stringList.add(String.valueOf(number));
        }

        Collections.sort(stringList, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;

            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return s1.charAt(i) - s2.charAt(i);
                }
            }

            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for (String str : stringList) {
            sb.append(str);
        }

        return sb.toString();
    }
}
