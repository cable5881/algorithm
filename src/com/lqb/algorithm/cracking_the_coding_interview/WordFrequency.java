package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * 
 * 
 * 请设计一个高效的方法，找出任意指定单词在一篇文章中的出现频数。
 * 给定一个string数组article和数组大小n及一个待统计单词word，请返回该单词在文章中的出现频数。
 * 保证文章的词数小于等于1000。
 * 
 * @author:JackBauer
 * @date:2016年10月14日
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
