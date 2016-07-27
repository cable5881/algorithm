package com.lqb.algorithm.offer;

/**
 * ��ʵ��һ�����������ҳ��ַ����е�һ��ֻ����һ�ε��ַ���
 * ���磬�����ַ�����ֻ����ǰ�����ַ�"go"ʱ����һ��ֻ����һ�ε��ַ���"g"��
 * ���Ӹ��ַ����ж���ǰ�����ַ���google"ʱ����һ��ֻ����һ�ε��ַ���"l"��
 * �������:�����ǰ�ַ���û�д��ڳ���һ�ε��ַ�������#�ַ���
 * @Author:JackBauer
 * @Date:2016��7��20�� ����4:47:39
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
