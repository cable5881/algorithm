package com.lqb.algorithm;

public class Demo {
	/*
	 * 小B最近迷上了字符串处理技术，他设计了各种处理方式，并计算字符串的属性。
	 * 这次也不例外，他定义了一种新的字符置换方式。小B研究的字符串由ASCII码字母和“.”构成，
	 * 这次的研究对象是“.”。他关心的对象是字符串中出现的连续两个“.”。
	 * 若每次操作把其中最开始的连续两个“.”以一个“.”替代，
	 * 则可以将函数f(s) 定义为使得串中不出现连续两个“.”的最小置换次数。
		现考虑m个字符替换操作，每次将指定位置的字符替换为给定的字符，求替换后函数f(s)的值。
	 * 
	 * 输入有若干组，每组的第一行为两个整数n和m，1<= n,m<=300000，
	 * 表示字符串的长度和字符替换操作的次数。第二行为所给的字符串，
	 * 随后紧跟m行操作，每行由一个正整数x和一个字母c构成，表示将字符串中位置m处的字符置换为字母c。
		表示将字符串中位置x处的字符置换为字母c
		
		对每组输入的每个置换操作，在单独的行中输出函数f(s)的结果
		
		10 3
		.b..bz....  .h.cbz...f
		1 h
		3 c
		9 f
		4 4
		.cc.
		2 .
		3 .
		2 a
		1 a
		
		4
		3
		1
		1
		3
		1
		1
	 * 
	 * */
	
	
	/*
	 * 为考验各自的数学能力，小B和小A经常在一起玩各种数值游戏，这一次他们又有了一种新玩法。
	 * 每人从指定的数值范围中各自选择一个整数，记小A选择的数值为n，小B选择的数值为b。
	 * 他们用一个均匀分布的随机数发生器在该数值范围中随机生成一个整数c，
	 * 定义制胜的游戏规则为谁选的数离c近则谁取得胜利。
	 * 由于小B是女生，特别定义当两人的数与c之间的差值相等时，小B获胜。
		由于先前的游戏中，小A为表现绅士风度总是输多赢少，
		因此他特别渴望这次能够给小B比较深刻的映像，所以向你求助。
		你事先已经知道了小B所选的数值和指定的数值范围，小A希望你帮他选择一个数值使得他获胜的概率最大。
		
		输入有若干行，每行为一组数据，包含两个正整数n和b，
		分别表示数值范围和小B所需的数，其中1<=b<=n<=10^9
		
		对每组输入，在单独的行中输出一个数，为小A所选的数，
		使得小A获胜的概率最大。若存在多个这样的数，则输出最小的那个
		
		样例输入
		3 1
		4 3
		样例输出
		2
		2
		
	 * 
	 * */
	public static void main(String[] args) {
		
	}
}