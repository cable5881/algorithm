package com.lqb.leetcode;

import org.junit.Test;

/**
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * 示例:
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-bad-version
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstBadVersion extends VersionControl{

    @Test
    public void test() {
        System.out.println(firstBadVersion(5));
    }

    @Test
    public void test2() {
        System.out.println(firstBadVersion(10));
    }

    @Test
    public void test3() {
        System.out.println(firstBadVersion2(21_2675_3390));
    }

    /**
     * 自己的解法: 会有溢出的情况, 还需要另外一个变量来保存第一个错误版本
     */
    public int firstBadVersion(int n) {
        long left = 1;
        long right = n;
        int mid;
        long first = 0;

        while (left <= right) {
            //会有溢出的情况
            mid = Long.valueOf((right + left) / 2).intValue();

            if (isBadVersion(mid)) {
                right = mid - 1;
                first = mid;
            } else {
                left = mid + 1;
            }

        }

        return Long.valueOf(first).intValue();
    }

    /**
     * 官方解法: 避免了溢出, 也不需要第二个变量来存储第一个错误版本
     */
    public int firstBadVersion2(int n) {
        int left = 1;
        int right = n;
        int mid;

        while (left < right) {
            //采用这种可以避免溢出的情况
            mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                //right不再另外+1
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        //返回的是left
        return left;
    }

}


class VersionControl{
    boolean isBadVersion(int version) {
        return version >= 17_0276_6719;
    }
}