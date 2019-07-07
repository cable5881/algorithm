package com.lqb.algorithm;

/**
 * @Description:汉诺塔
 * @Author:JackBauer
 * @Date:2016年8月10日
 */
public class Hanoi {

	public static void main(String[] args) {
		Hanoi test = new Hanoi();
		test.move(3, 'a', 'b', 'c');
	}

	private void move(int n, char origin, char buffer, char destination) {
		if (n == 1) {
			System.out.println(origin + "-->" + destination);
		} else {
			//将n - 1 个盘先移到buffer
			//即以buffer作为目的地destination
			//可以认为现在是n - 1个盘子的移动问题,只不过目的地不同
			move(n - 1, origin, destination, buffer);
			
			//n - 1 个盘子移动完之后把最后一个盘子移动到目的地
			System.out.println(origin + "-->" + destination);
			
			//剩下的n - 1个盘子从buffer移动到destination
			//可以认为现在是n - 1个盘子的移动问题,只不过起点不同
			move(n - 1, buffer, origin, destination);
		}
	}
}
