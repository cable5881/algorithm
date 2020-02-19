package com.lqb.offer;

import org.junit.Test;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {

    @Test
    public void test() {
        System.out.println(GetNumberOfK(new int[]{1, 2, 3, 3, 3, 3, 3, 5, 6}, 3));
        System.out.println(GetNumberOfK(new int[]{1, 2, 3, 3, 3, 3, 3, 5, 6}, 5));
        System.out.println(GetNumberOfK(new int[]{1, 2, 3, 3, 3, 3, 3, 5, 6}, 7));
        System.out.println(GetNumberOfK(new int[]{1, 3, 3, 3, 3, 4, 5}, 2));
        System.out.println(GetNumberOfK(new int[]{1, 2}, 1));
        System.out.println(GetNumberOfK(new int[]{1, 2}, 2));
        System.out.println(GetNumberOfK(new int[]{2, 2}, 2));
        System.out.println(GetNumberOfK(new int[]{2, 2}, 3));
    }

    public int GetNumberOfK(int[] a, int k) {
        if (a == null || a.length <= 0) {
            return 0;
        }

        int t1 = getFirstK(a, k);
        int t2 = getLastK(a, k);

        return t1 < 0 ? 0 : t2 - t1 + 1;
    }

    public int getFirstK(int[] a, int k) {
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] == k) {
                //如果已经到达数组最左，或者当前已经是第一个K了
                if ((mid - 1) < 0 || a[mid - 1] != k) {
                    return mid;
                } else {
                    r = mid - 1;
                }
            } else if (a[mid] > k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        //最后的l或者r是相等的，且是数组中的某个位置，但是a[l]不一定是k
        return a[l] == k ? l : -1;
    }

    private int getLastK(int[] a, int k) {
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] == k) {
                //太复杂
                // if (mid + 1 <= r && a[mid + 1] == k) { // 考虑k=2,[2,2], a[l=0]=2,a[r=1]=2,a[mid=0]=2
                //     l = mid + 1;
                // } else if (l == mid) {// 考虑k=1,[1, 2], a[l=0]=1,a[r=1]=2,a[mid=0]=1, 如果没有这个条件走下面的l=mid，那么会死循环
                //     break;
                // } else {//其他大多数情况，L都是要向右靠近
                //     l = mid;
                // }

                //如果已经到达数组最右，或者当前已经是最后一个K了
                if ((mid + 1) > r || a[mid + 1] != k) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else if (a[mid] < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return a[l] == k ? l : -1;
    }
}
