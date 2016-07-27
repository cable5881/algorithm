package com.lqb.algorithm.offer;

public class RectCover {

	public static void main(String[] args) {
		System.out.println(rectCover(-1));
		System.out.println(rectCover(0));
		System.out.println(rectCover(1));
		System.out.println(rectCover(2));
		System.out.println(rectCover(3));
		System.out.println(rectCover(4));
		System.out.println(rectCover(5));
		System.out.println(rectCover(6));
		System.out.println(rectCover(7));

	}
	
	public static int rectCover(int target) {
		
		if( target <= 0 ){
			return 0;
		}else if( target == 1 ){
			return 1;
		}else if( target == 2 ){
			return 2;
		}
		
		return rectCover(target - 1) + rectCover(target - 2);
		
    }

}
