package com.lqb.offer;

import org.junit.Test;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student."，则输出"student.a am I"
 * 
 * @Description:TODO
 * @author:JackBauer
 * @date:2016年6月22日 下午4:16:56
 */
@SuppressWarnings("Duplicates")
public class ReverseSentence {

    @Test
	public void test() {
		System.out.println(reverseSentence(new String("I am a student.")));
		System.out.println(ReverseSentence2(new String("I am a student.")));
		System.out.println(ReverseSentence2(new String("Wonderful")));
	}

	public String reverseSentence(String str) {
		if(str == null || str.length() <= 0){
			return str;
		}
		
		char[] ch = str.toCharArray();
		
		int start = 0;
		int end = 0;
		
		for(int i = 1; i < ch.length; i++){
			if(ch[i] != ' '){
				end++;
			}else if(ch[i] == ' '){
				swap(ch, start, end);
				start = i + 1;
				end = i;//注意这里不是i + 1，否则会转换成Iam a student.
			}
			
			if(end == ch.length - 1){
				swap(ch, start, end);
			}
		}
		
        swap(ch, 0, ch.length - 1);
		
		return new String(ch);
	}

	private void swap(char[] ch, int start, int end) {
		while(start < end){
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;

            start++;
            end--;
		}
	}

    /**
     * 20200221
     */
    public String ReverseSentence2(String str) {
        if(str == null || str.length() <= 0){
            return str;
        }

        char[] ch = str.toCharArray();

        //先整体翻转一遍
        swap(ch, 0, ch.length - 1);

        int i = 0;
        int j = 0;
        while (j < ch.length) {
            if (ch[j] != ' ') {
                j++;
            } else {
                //然后再翻转每一个单词
                swap(ch, i, j - 1);
                i = ++j;
            }
        }

        //注意最后单词没有翻转，因为上面的循环中到达结尾就退出了
        swap(ch, i, j - 1);
        return new String(ch);
    }
}
