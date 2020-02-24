package com.lqb.alibaba;

// 对于一个长度为N的整型数组A，数组里所有的数都是正整数，对于两个满足0<=X<Y<N的整数，
// A[X],A[X+1]…A[Y]构成A的一个切片，记作(X,Y)。
// 用三个下标m1,m2,m3下标满足条件0<m1,m1+1<m2,m2+1<m3<N–1。
// 可以把这个整型数组分成(0,m1-1),(m1+1,m2-1),(m2+1,m3-1),(m3+1,N-1)四个切片。
// 如果这四个切片中的整数求和相等，称作“四等分”。
// 编写一个函数，求一个给定的整型数组是否可以四等分，如果可以，返回一个布尔类型的true，
// 如果不可以返回一个布尔类型的false。
// 要求：函数的计算复杂度为O(N)，使用的额外存储空间（除了输入的数组之外）最多为O(N)。
// 例子：对于数组A=[2,5,1,1,1,1,4,1,7,3,7]存在下标2,7,9使得数组分成
// 四个分片[2,5],[1,1,1,4],[7],[7]，这三个分片内整数之和相等，所以对于这个数组，函数应该返回true。
// 对于数组A=[10,2,11,13,1,1,1,1,1]，找不到能把数组四等分的下标，所以函数应该返回false。
public class Quartering {

    public static boolean accept(int[] items) {
        int l = 0;
        int r = items.length - 1;
        int lSum = 0;
        int rSum = 0;
        int flag = 0;
        while (l < r) {
            if (flag == 0) {
                lSum = lSum + items[l];
                rSum = rSum + items[r];
            } else if (flag == 1) {
                lSum = lSum + items[l];
            } else {
                rSum = rSum + items[r];
            }

            if (lSum == rSum) {
                if (acceptMid(items, l, r, lSum)) {
                    return true;
                }
                l++;
                r--;
            } else if (lSum < rSum) {
                l++;
                flag = 1;
            } else {
                r--;
                flag = 2;
            }
        }

        return false;
    }

    private static boolean acceptMid(int[] items, int start, int end, int sum) {
        int l = start;
        int r = end;
        int lSum = 0;
        int rSum = 0;
        int flag = 0;
        while (l < r) {
            if (flag == 0) {
                lSum = lSum + items[l];
                rSum = rSum + items[r];
            } else if (flag == 1) {
                lSum = lSum + items[l];
            } else {
                rSum = rSum + items[r];
            }

            if (lSum > sum || rSum > sum) {
                return false;
            }

            if (lSum == rSum) {
                if (lSum == sum && start + 1 < l && l + 1 < r) {
                    return true;
                }
                l++;
                r--;
            } else if (lSum < rSum) {
                l++;
                flag = 1;
            } else {
                r--;
                flag = 2;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] items1 = new int[]{2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7};
        System.out.println(accept(items1));

        int[] items2 = new int[]{10, 2, 11, 13, 1, 1, 1, 1, 1};
        System.out.println(accept(items2));

    }
}


