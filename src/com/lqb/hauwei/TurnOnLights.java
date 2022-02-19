package com.lqb.hauwei;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class TurnOnLights {

    @Test
    public void test() {
        System.out.println(Arrays.toString(func(7, 1)));
        System.out.println(Arrays.toString(func(7, 2)));
        System.out.println(Arrays.toString(func(7, 3)));
        System.out.println(Arrays.toString(func(7, 4)));
        System.out.println(Arrays.toString(func(7, 5)));
        System.out.println(Arrays.toString(func(7, 6)));
        System.out.println(Arrays.toString(func(7, 7)));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            int k = in.nextInt();
            int[] res = func(n, k);
            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i]);
                if (i < res.length - 1) {
                    System.out.print(" ");
                }
            }
        }
    }

    /**
     * 返回最终打开的灯
     * @param n 灯数量
     * @param k 人数量
     * @return 最终打开的灯
     */
    public static int[] func(int n, int k) {
        boolean[] lights = new boolean[n + 1];
        //开灯的数量
        int lightsOpen = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = i; j < lights.length; j += i) {
                if (lights[j]) {
                    lights[j] = false;
                    lightsOpen--;
                } else {
                    lights[j] = true;
                    lightsOpen++;
                }
            }
        }
        int[] res = new int[lightsOpen];
        int i = 0;
        for (int j = 0; j < lights.length; j++) {
            if (lights[j]) {
                res[i++] = j;
            }
        }
        return res;
    }


}
