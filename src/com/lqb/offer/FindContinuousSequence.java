package com.lqb.offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。
 * 例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续序列1～5、4～6和7～8
 */
public class FindContinuousSequence {

    @Test
    public void test() {
        System.out.println(findContinuousSequence2(15));
    }

    public ArrayList<ArrayList<Integer>> findContinuousSequence(int num) {
        ArrayList<ArrayList<Integer>> numbersSequence = new ArrayList<>();

        //注意结束条件
        if (num < 3) {
            return numbersSequence;
        }

        int small = 1;
        int big = small + 1;

//		while (big < num) {
//			int sum = getSum(small, big);
//			if(sum < num){
//				big++;
//			}else if(sum > num){
//				small++;
//			}else{
//				addNumbers(numbersSequence, small, big);
//				small = big - 1;
//				big++;
//			}
//		}

        int middle = num / 2;
        int sum = big + small;

        //注意是
        while (small <= middle) {
            if (num == sum) {
                addNumbers(numbersSequence, small, big);
            }

            while (sum > num && small <= middle) {
                sum -= small;
                small++;

                if (num == sum) {
                    addNumbers(numbersSequence, small, big);
                }
            }

            big++;
            sum += big;
        }

        return numbersSequence;
    }

    private int getSum(int start, int end) {
        int sum = 0;

        while (start <= end) {
            sum += start++;
        }

        return sum;
    }

    private void addNumbers(ArrayList<ArrayList<Integer>> numbersSequence, int start, int end) {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (start <= end) {
            numbers.add(start++);
        }
        numbersSequence.add(numbers);
    }

    /**
     * 20200221
     * 需要注意的是，不用每一个循环都去调getSum做累加
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence2(int num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num < 3) {
            return res;
        }
        int i = 1;
        int j = 1;
        int k = num / 2 + 1;
        int sum = 1;

        while (j <= k) {
            if (sum == num) {
                addNumbers(res, i, j);
                j++;
                sum += j;
            } else if (sum < num) {
                j++;
                sum += j;
            } else {
                sum -= i;
                i++;
            }
        }

        return res;
    }
}
