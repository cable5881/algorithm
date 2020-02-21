package com.lqb.offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
 * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。 
 * @author:JackBauer
 * @date:2016年6月23日 下午5:14:12
 */
public class IsContinuous {

	@Test
	public void test() {
		System.out.println(isContinuous2(new int[]{1,3,5,0,4}));//true
		System.out.println(isContinuous2(new int[]{1,3,5,0,0}));//true
		System.out.println(isContinuous2(new int[]{0,3,2,6,4}));//true
		System.out.println(isContinuous2(new int[]{1,0,0,0,5}));//true
		System.out.println(isContinuous2(new int[]{1,3,5,6,0}));//false

	}

	public boolean isContinuous(int[] numbers) {
		if (numbers == null || numbers.length != 5) {
			return false;
		}

		Arrays.sort(numbers);

		int numbersOfGap = 0;
		int numbersOfZero = 0;

		for (int num : numbers) {
			if (num == 0) {
				numbersOfZero++;
			}
		}

		int small = numbersOfZero;
		int big = small + 1;

		while (big < 5) {
			if(numbers[big] - numbers[small] == 0){
				return false;
			}
			
			numbersOfGap += numbers[big] - numbers[small] - 1;
			small++;
			big++;
		}
		
		return numbersOfGap > numbersOfZero ? false : true;
	}

	public boolean isContinuous2(int[] a) {
        if (a == null || a.length <= 5) {
            return false;
        }
		Arrays.sort(a);

        //计算大小鬼的数量
		int jokerNum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                break;
            }
            jokerNum++;
        }

		int last = a[a.length - 1];
        int jokerLeft = jokerNum;
		int i = a.length - 2;
        while (i >= jokerNum) {
            if (last - a[i] == 1) {
                last = last - 1;
                i--;
            } else if (jokerLeft > 0) {
                //注意这里不用i--，因为用了大小鬼顶上，a[i]需要继续下一轮的比较
                //如[0,1,2,3,5]，0只是代替了4，目前a[i]=3还要接着下一轮的比较
                jokerLeft--;
                last = last - 1;
            } else {
                return false;
            }
        }

		return true;
	}

}
