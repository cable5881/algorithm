package com.lqb.offer.mark;

import org.junit.Test;

/**
 * 字符流中第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * @Author:JackBauer
 * @Date:2016年7月20日 下午4:47:39
 */
public class FirstAppearingOnce {

    @Test
    public void test() {
        char[] str = "google".toCharArray();

        for (char c : str) {
            insert(c);
        }

        System.out.println(firstAppearingOnce());
    }

    //记录每个字符第一次出现的时机，如果出现了第二次，则记为-1
    private int[] indexArr = new int[128];

    //记录插入的时机
    private int index = 0;

    //Insert one char from stringstream
    public void insert(char ch) {
        ++index;

        if (indexArr[ch] > 0) {
            indexArr[ch] = -1;
        } else {
            indexArr[ch] = index;
        }
    }

    //return the first appearence once char in current stringstream
    public char firstAppearingOnce() {
        char ch = '#';

        //最近的出现时机
        int chIndex = 128;
        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i] < chIndex && indexArr[i] > 0) {
                chIndex = indexArr[i];
                ch = (char) i;
            }
        }

        return ch;
    }

}
