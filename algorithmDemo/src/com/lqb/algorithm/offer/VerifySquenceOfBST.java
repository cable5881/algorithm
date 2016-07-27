package com.lqb.algorithm.offer;

public class VerifySquenceOfBST {

	public static void main(String[] args) {
		
		VerifySquenceOfBST v = new VerifySquenceOfBST();
		
		int[] sequence = {2,4,3,6,8,7,5};
//		int[] sequence = {2,4,3,8,6,7,5};//错误序列
//		int[] sequence = {4,3,6,8,7,5};//左子树的左叶子节点缺失
//		int[] sequence = {2,4,3,6,7,5};//右子树的右叶子节点缺失
//		int[] sequence = {3,5};//缺右子树
//		int[] sequence = {7,5};//缺左子树
		
		
		System.out.println(v.verifySquenceOfBST(sequence));

	}

	public boolean verifySquenceOfBST(int[] sequence) {

		if( sequence == null || sequence.length == 0 ){
			return false;
		}
		
		return verify(sequence,0,sequence.length - 1);
		
	}
	
	private boolean verify(int[] sequence, int start, int end){
		
		if( end <= start ){
			return true;
		}
		
		int rootVal = sequence[end];
		
		int rChildIndex = getChildIndex(sequence, start, end);
		
		for( int i = rChildIndex; i < end; i++ ){
			if( sequence[i] < rootVal ){
				return false;
			}
		}
		
		boolean isLChildBST = verify(sequence, start, rChildIndex - 1);
		boolean isRChildBST = verify(sequence, rChildIndex, end - 1);
		
		return isLChildBST && isRChildBST;
	}
	
	private int getChildIndex(int[] sequence, int start, int end){
		
		int root = sequence[end];
		
		int rTreeStart = start;
		
		while( rTreeStart < end ){
			if( sequence[rTreeStart] > root ){
				break;
			}
			rTreeStart++;
		}
		
		return rTreeStart;
	}

}
