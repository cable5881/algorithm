package com.lqb.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:归并排序
 * @Author:JackBauer
 * @Date:2016年7月20日 上午11:28:57
 */
public class MergeSort {

    @Test
    public void test() {
        int[] a = {1, 9, 7, 5, 2, 8, 4, 3, 6};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        ForkJoinTask<int[]> future = forkJoinPool.submit(
                new SortTask(new int[]{1, 9, 7, 5, 2, 8, 4, 3, 6, 5, 6, 8, 3, 2, 5, 6, 7, 8, 3, 1, 5}));
        System.out.println(Arrays.toString(future.get()));
    }

    public void mergeSort(int[] a) {
        if (a == null || a.length < 1) {
            return;
        }

        mergeSortCore(a, new int[a.length], 0, a.length - 1);
    }

    private void mergeSortCore(int[] a, int t[], int start, int end) {
        if (end <= start) {
            return;
        }

        int leftStart = start;
        int leftEnd = (start + end) / 2;
        int rightStart = leftEnd + 1;
        int rightEnd = end;

        mergeSortCore(a, t, leftStart, leftEnd);
        mergeSortCore(a, t, rightStart, rightEnd);

        int i = t.length - 1;

        while (leftEnd >= leftStart && rightStart <= rightEnd) {
            if (a[leftEnd] > a[rightEnd]) {
                t[i--] = a[leftEnd--];
            } else {
                t[i--] = a[rightEnd--];
            }
        }

        while (leftEnd >= leftStart) {
            t[i--] = a[leftEnd--];
        }

        while (rightStart <= rightEnd) {
            t[i--] = a[rightEnd--];
        }

        while (start <= end) {
            a[start++] = t[++i];
        }

    }

}

class SortTask extends RecursiveTask<int[]> {

    int[] a;

    SortTask(int[] a) {
        this.a = a;
    }

    @Override
    protected int[] compute() {
        if (a.length > 2) {
            // 任务太大,一分为二:
            int middle = a.length / 2;
            SortTask subtask1 = new SortTask(Arrays.copyOfRange(a, 0, middle));
            SortTask subtask2 = new SortTask(Arrays.copyOfRange(a, middle, a.length));
            invokeAll(subtask1, subtask2);
            int[] a1 = subtask1.join();
            int[] a2 = subtask2.join();
            return joinArray(a1, a2);
        } else if (a.length == 2) {
            if (a[0] > a[1]) {
                int tmp = a[0];
                a[1] = a[0];
                a[0] = tmp;
            }
        }

        return a;
    }

    private int[] joinArray(int[] a1, int[] a2) {
        int[] b = new int[a1.length + a2.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j]) {
                b[k++] = a1[i++];
            } else {
                b[k++] = a2[j++];
            }
        }

        while (i < a1.length) {
            b[k++] = a1[i++];
        }
        while (j < a2.length) {
            b[k++] = a2[j++];
        }

        return b;
    }
}
