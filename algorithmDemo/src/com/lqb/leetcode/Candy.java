package com.lqb.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length < 1) {
            return 0;
        } else if (ratings.length < 2) {
            return 1;
        }

        int[] candies = new int[ratings.length];
        candies[0] = 1;
        int min = 1;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                candies[i] = candies[i - 1] - 1;
                if (candies[i] < min) {
                    min = candies[i];
                }
            } else {
                candies[i] = 1;
            }
        }

        int candiesNum = 0;
        if (min < 1) {
            int offset = 1 - min;
            for (int i = 0; i < candies.length; i++) {
                candiesNum += candies[i] + offset;
            }
        } else {
            for (int i = 0; i < candies.length; i++) {
                candiesNum += candies[i];
            }
        }

        return candiesNum;
    }

    @Test
    public void test1() {
        int[] ratings = {1, 2, 3};
        Assert.assertEquals(6, candy(ratings));
    }

    @Test
    public void test2() {
        int[] ratings = {3, 2, 1};
        Assert.assertEquals(6, candy(ratings));
    }

    @Test
    public void test3() {
        int[] ratings = {2, 3, 1};
        Assert.assertEquals(4, candy(ratings));
    }

    @Test
    public void test4() {
        int[] ratings = {2, 2};
        Assert.assertEquals(2, candy(ratings));
    }

    @Test
    public void test5() {
        int[] ratings = {1, 2, 2};
        Assert.assertEquals(4, candy(ratings));
    }

    @Test
    public void test6() {
        int[] ratings = {2, 2, 1};
        Assert.assertEquals(4, candy(ratings));
    }
}
