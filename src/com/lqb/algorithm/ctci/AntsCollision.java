package com.lqb.algorithm.ctci;

/**
 * 在n个顶点的多边形上有n只蚂蚁，这些蚂蚁同时开始沿着多边形的边爬行，请求出这些蚂蚁相撞的概率。
 * (这里的相撞是指存在任意两只蚂蚁会相撞)
 * 给定一个int n(3<=n<=10000)，代表n边形和n只蚂蚁，请返回一个double，为相撞的概率。
 * 
 * @author:JackBauer
 * @date:2016年10月13日
 */
public class AntsCollision {

	public static void main(String[] args) {
		AntsCollision test = new AntsCollision();
		System.out.println(test.antsCollision(3));
		System.out.println(test.antsCollision(4));
		System.out.println(test.antsCollision(5));
		System.out.println(test.antsCollision(10));
		System.out.println(test.antsCollision(10000));
	}
	
	public double antsCollision(int n) {
		return 1 - Math.pow(0.5, n) * 2;
    }

}
