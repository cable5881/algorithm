package com.lqb.offer;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:如果当前字符流没有存在出现一次的字符，返回#字符。
 * @Author:JackBauer
 * @Date:2016年7月20日 下午4:47:39
 */
public class FirstAppearingOnce {

	private int[] indexArr = new int[127];
	private int index = 0;
	
	public FirstAppearingOnce() {
		for (int i = 0; i < indexArr.length; i++) {
			indexArr[i] = 0;
		}
	}
	
	public static void main(String[] args) {
//		Character a1 = 'a';
//		Character a2 = 'a';
		
//		Integer n1 = 1;
//		Integer n2 = 1;
//		
//		System.out.println(n1 == n2);
		
//		System.out.println(a1 == a2);
		
		FirstAppearingOnce test = new FirstAppearingOnce();
		char[] str = "google".toCharArray();
		
		for (char c : str) {
			test.insert(c);
		}
		
		System.out.println(test.firstAppearingOnce());
	}
	
	//Insert one char from stringstream
    public void insert(char ch)
    {
    	++index;
    	
        if(indexArr[ch] > 0){
        	indexArr[ch] = -1;
        }else{
        	indexArr[ch] = index; 
        }
    }
    
  //return the first appearence once char in current stringstream
    public char firstAppearingOnce()
    {
    	char ch = '#';
    	int chIndex = 128;
    	for(int i = 0; i < indexArr.length; i++){
    		if(indexArr[i] < chIndex && indexArr[i] > 0){
    			chIndex = indexArr[i];
    			ch = (char)i;
    		}
    	}
    	
    	
    	return ch;
    }

}
