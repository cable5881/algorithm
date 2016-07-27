package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * �����ַ��ظ����ֵĴ�������дһ��������ʵ�ֻ������ַ���ѹ�����ܡ�
 * ���磬�ַ�����aabcccccaaa����ѹ�����ɡ�a2b1c5a3������ѹ������ַ���û�б�̣��򷵻�ԭ�ȵ��ַ�����
 * ����һ��string iniStringΪ��ѹ���Ĵ�(����С�ڵ���3000)��
 * ��֤�����ַ����ɴ�СдӢ����ĸ��ɣ�����һ��string��Ϊ�����ѹ�����δ�仯�Ĵ���
 * ��������:
 * 
 * "aabcccccaaa"
 * ���أ�"a2b1c5a3"
 * 
 * "welcometonowcoderrrrr"
 * ���أ�"welcometonowcoderrrrr"
 * 
 * @Author:JackBauer
 * @Date:2016��7��22�� ����4:49:49
 */
public class ZipString {

	public static void main(String[] args) {
//		String iniString = "aabcccccaaa";
		String iniString = "welcometonowcoderrrrr";
		
		ZipString test = new ZipString();
		System.out.println(test.zipString(iniString));
	}

	public String zipString(String iniString) {
		if(iniString == null || iniString.length() < 1){
			return iniString;
		}
		
		char[] s = iniString.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length; i++){
			int times = 1;
			char c = s[i];
			sb.append(c);
			
			for(i += 1; i < s.length && s[i] == c; i++){
				times++;
			}
			
			sb.append(times);
			i--;
		}
		
		String newString = sb.toString();
		
		return newString.length() > iniString.length() ?  iniString : newString;
	}

}
