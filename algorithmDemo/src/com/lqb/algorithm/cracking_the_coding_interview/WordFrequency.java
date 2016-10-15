package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * 
 * �����һ����Ч�ķ������ҳ�����ָ��������һƪ�����еĳ���Ƶ����
 * ����һ��string����article�������Сn��һ����ͳ�Ƶ���word���뷵�ظõ����������еĳ���Ƶ����
 * ��֤���µĴ���С�ڵ���1000��
 * 
 * @author:JackBauer
 * @date:2016��10��14��
 */
public class WordFrequency {

	public static void main(String[] args) {
		WordFrequency test = new WordFrequency();
		String[] article = {"hello","world","shit","hello","fuck"};
		System.out.println(test.getFrequency(article, 5, "hello"));
	}

	public int getFrequency(String[] article, int n, String word) {
		int count = 0;
		
		if(article == null || article.length < 1 || word == null || word.equals("")){
			return count;
		}
		
		for(String s : article) {
			if(s.equals(word)){
				count++;
			}
		}
		
		return count;
	}

}
