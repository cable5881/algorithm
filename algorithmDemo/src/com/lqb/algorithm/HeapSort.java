package com.lqb.algorithm;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] a = { -1, 3, 4, 1, 2, 8, 7, 6, 0 };

		heapSort(a, 8);
		
		System.out.println(Arrays.toString(a));
	}

	/**
	 * @Description: 堆排序
	 * @param a
	 * @param n 数组长度,默认a[0]为无效元素，为了看起来直观
	 */
	public static void heapSort(int[] a, int n) {
		int i;
		
		//先选出最大的元素放在堆顶
		//从i为每个父节点
		for (i = n / 2; i > 0; i--) {
			heapAdjust(a, i, n);
		}

		//每一轮结束后顶部都是最大的元素
		for (i = n; i > 1; i--) {
			//将顶部最大的元素调换到最后
			swap(a, 1, i);
			//最后的元素被换到堆顶后再一次进行构造堆
			//注意这时是从堆顶(即下标为1)开始往下构造
			//因为交换最大的元素到末尾后,从堆顶开始的第二层中包含有第二大的元素,这是上一轮比较后残留的结果
			//利用上一轮的结果,可以直接把第二大元素放到堆顶
			//同理下一次第二层又有第三大的元素
			//如果又从最底下开始,因为元素都已经是比较好了的,每一层的元素都比它对应下一层的子元素要大,此时是多余的操作
			heapAdjust(a, 1, i - 1);
		}
	}

	
	/**
	 * @Description: 构造堆
	 * @param a 
	 * @param p 父节点下标
	 * @param n 最后一个元素下标
	 */
	private static void heapAdjust(int a[], int p, int n) {
		int temp = a[p];//父节点

		for (int i = 2 * p; i <= n; i *= 2) {
			//i为左孩子下标
			//如果右孩子大于左孩子,则i+1指向右孩子
			//i < n是因为父节点可能只有一个孩子,此时访问a[i + 1]的话就越界了
			if (i < n && a[i] < a[i + 1])
				i++;
			
			//如果父节点比最大的孩子还要大直接跳出循环
			//注意每次都是和temp比较，因为temp不变，每一次都是父节店
			//写成a[p] >= a[i]就错了
			if (temp >= a[i])
				break;
			
			//最大的孩子比父节点还要大,则该孩子升级为父节点,即该孩子的值覆盖父节点的值
			a[p] = a[i];
			//如果产生了新父节点，则新孩子（原父节点，值仍为temp）将作为下一轮的父节点继续构造堆
			p = i;
		}
		
		//新父节点(p = i)还没有被赋值就退出了循环,即该父节点已经没有孩子了
		//于是给它的值即为最原始的父节点的值temp
		a[p] = temp;
	}

	/**
	 * @Description: 堆排序，从下标0开始
	 * @param a
	 */
	public static void heapSort2(int[] a) {
		if (a == null || a.length < 1) {
			return;
		}

		int len = a.length;

		//注意是i = len / 2 - 1
		for (int i = len / 2 - 1; i >= 0; i--) {
			heapAdjust2(a, i, len - 1);
		}

		for (int i = len - 1; i > 0; i--) {
			swap(a, 0, i);
			heapAdjust2(a, 0, i - 1);
		}

	}

	/**
	 * @Description: 构造顶堆，从下标0开始
	 * @param a
	 * @param parent
	 * @param end
	 */
	private static void heapAdjust2(int[] a, int parent, int end) {
		int temp = a[parent];
		
		//注意是i = parent * 2 + 1.
		for (int i = parent * 2 + 1; i <= end; i = i * 2 + 1) {
			if(i < end && a[i] < a[i + 1]){
				i++;
			}
			
			if(a[i] > temp){
				a[parent] = a[i];
				parent = i;
			}else{
				break;
			}
		}

		a[parent] = temp;
	}
	
	
	/**
	 * @Description: 交换元素
	 * @param a
	 * @param i
	 * @param j
	 */
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
