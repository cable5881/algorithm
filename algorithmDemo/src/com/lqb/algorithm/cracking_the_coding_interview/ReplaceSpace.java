package com.lqb.algorithm.cracking_the_coding_interview;

/**
 * ���дһ�����������ַ����еĿո�ȫ���滻Ϊ��%20����
 * �ٶ����ַ������㹻�Ŀռ����������ַ�������֪���ַ�������ʵ����(С�ڵ���1000)��ͬʱ��֤�ַ����ɴ�Сд��Ӣ����ĸ��ɡ�
 * ����һ��string iniString Ϊԭʼ�Ĵ����Լ����ĳ��� int len, �����滻���string��
 * ����������
 * 
 * "Mr John Smith��,13
 * ���أ�"Mr%20John%20Smith"
 * 
 * ��Hello  World��,12
 * ���أ���Hello%20%20World��
 * 
 * @Author:JackBauer
 * @Date:2016��7��22�� ����4:32:55
 */
public class ReplaceSpace {
	
	public static void main(String[] args) {
		ReplaceSpace test = new ReplaceSpace();
		String a = "Mr John Smith";
		String b = test.replaceSpace(a, 13);
		System.out.println(b);
	}
	
	public String replaceSpace(String iniString, int length) {
		if(iniString == null || length <= 0){
			return iniString;
		}
		
		int spaces = 0;
		char[] a = iniString.toCharArray();
		
		for(char c : a){
			if(c == ' '){
				spaces++;
			}
		}
		
		char[] b = new char[length + 2 * spaces];
		
		int i = length - 1;
		int j = b.length - 1;
		
		while(i >= 0){
			if(a[i] != ' '){
				b[j--] = a[i--];
			}else{
				b[j--] = '0';
				b[j--] = '2';
				b[j--] = '%';
				
				i--;
			}
		}
		
		return new String(b);
    }
}
