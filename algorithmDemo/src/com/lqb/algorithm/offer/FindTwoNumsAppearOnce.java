package com.lqb.algorithm.offer;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 
 * @author:JackBauer
 * @date:2016年6月18日 下午8:24:32
 */
public class FindTwoNumsAppearOnce {

	public static void main(String[] args) {
		FindTwoNumsAppearOnce test = new FindTwoNumsAppearOnce();
		int[] array = {1,1,2,33,33,44,44,66,88,66,88,3};
//		int[] array = {2,4,3,6,3,2,5,5};
		int num1[] = {0};
		int num2[] = {0};
		
		test.FindNumsAppearOnce(array, num1, num2);
		System.out.println(num1[0]);
		System.out.println(num2[0]);
	}

	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
		if (array == null || array.length <= 1) {
			return;
		}

		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			temp ^= array[i];
		}

		int leastBitOf1 = findLeastBitOf1(temp);
		
		for(int i = 0; i < array.length; i++){
			if(KthBitIs1(array[i], leastBitOf1)){
				num1[0] ^= array[i];
			}else{
				num2[0] ^= array[i];
			}
		}
		
	}
	
	public int findLeastBitOf1(int num){
		
		int indexOf1 = 0;
		
		while( (num & 1) == 0 && indexOf1 < Integer.SIZE * 8 ){
			num >>= 1;
			indexOf1++;
		}
		return indexOf1;
	}
	
	public boolean KthBitIs1(int num,int k){
		if(((num >> k) & 1) == 1){
			return true;
		}else{
			return false;
		}
	}

}
