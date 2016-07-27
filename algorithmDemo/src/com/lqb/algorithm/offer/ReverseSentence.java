package com.lqb.algorithm.offer;

/**
 * ���磬��student. a am I���� ��������ʶ������һ�ԭ���Ѿ��ӵ��ʵ�˳��ת�ˣ���ȷ�ľ���Ӧ���ǡ�I am a student.����
 * 
 * @Description:TODO
 * @author:JackBauer
 * @date:2016��6��22�� ����4:16:56
 */
public class ReverseSentence {

	public static void main(String[] args) {
		ReverseSentence test = new ReverseSentence();
//		String str = test.reverseSentence(new String("student. a am I"));
		String str = test.reverseSentence(new String("I am a student."));
		System.out.println(str);
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
				reverseSentenceCore(ch, start, end);
				start = i + 1;
				end = i;//ע�����ﲻ��i + 1�������ת����Iam a student.
			}
			
			if(end == ch.length - 1){
				reverseSentenceCore(ch, start, end);
			}
		}
		
//		int i = 0;
//		
//		while(start < ch.length){
//			if(ch[i] != ' '){
//				end++;
//			}else if(ch[i] == ' ' || i == ch.length - 1){
//				reverseSentenceCore(ch, start, end);
//				start = i + 1;
//				end = i;//ע�����ﲻ��i + 1�������ת����Iam a student.
//			}
//			i++;
//		}
		
		reverseSentenceCore(ch, 0, ch.length - 1);
		
		return new String(ch);
	}

	private void reverseSentenceCore(char[] ch, int start, int end) {
		while(start < end){
			swap(ch, start++, end--);
		}
	}
	
	private void swap(char[] ch, int index1, int index2){
		char temp = ch[index1];
		ch[index1] = ch[index2];
		ch[index2] = temp;
	}

}
