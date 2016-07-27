package com.lqb.algorithm.offer;

/**
 * ��ʵ��һ����������ƥ�����'.'��'*'��������ʽ�� ģʽ�е��ַ�'.'��ʾ����һ���ַ�����'*'��ʾ��ǰ����ַ����Գ�������Σ�����0�Σ���
 * �ڱ����У�ƥ����ָ�ַ����������ַ�ƥ������ģʽ�� ���磬�ַ���"aaa"��ģʽ"a.a"��"ab*ac*a"ƥ�䣬������"aa.a"��"ab*a"����ƥ��
 */
public class RegexMatch {

	public static void main(String[] args) {
		RegexMatch test = new RegexMatch();
//		 char[] str = "aaa".toCharArray();
		char[] str = "".toCharArray();
//		char[] str = "".toCharArray();
		 char[] pattern = ".*".toCharArray();
//		char[] pattern = ".".toCharArray();
//		 char[] pattern = "ab*ac*a".toCharArray();
//		 char[] pattern = "ab*a".toCharArray();
//		 char[] pattern = "a.a".toCharArray();
		System.out.println(test.myMatch(str, pattern));
	}

	public boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null) {
			return false;
		} else if (str.length < 1 && (pattern.length < 1 || containsMoreOnly(pattern))) {
			return true;
		} else if (str.length < 1 || pattern.length < 1) {
			return false;
		}

		int i = 0;
		int j = 0;
		int SLength = str.length;
		int PLength = pattern.length;

		while (i < SLength && j < PLength) {
			if (str[i] == pattern[j] || pattern[j] == '.') {
				i++;
				j++;
			} else if (pattern[j] == '*' && j - 1 >= 0) {
				char c = pattern[j - 1];

				if (c == '.' || c == '*') {
					return false;
				}

				while (str[i] == c && i < SLength) {
					i++;
				}

				j++;
			} else if (j + 1 < PLength && pattern[j + 1] == '*') {
				j++;
			} else {
				return false;
			}
		}

		if (i < SLength) {
			return false;
		} else if (j < PLength && pattern[j] != '*') {
			return false;
		} else {
			return true;
		}

	}

	private boolean containsMoreOnly(char[] regex) {

		if (regex[0] != '.' || regex.length < 2) {
			return false;
		}

		for (int i = 1; i < regex.length; i++) {
			if (regex[i] != '*') {
				return false;
			}
		}

		return true;
	}

	public boolean myMatch(char[] s, char[] p) {
		if(s == null || p == null){
			return false;
		}else if(s.length == 0 && p.length == 0){
			return true;
		}else if(s.length != 0 && p.length == 0){
			return false;
		}
		
		return matchCore(s, p, 0, 0);
	}

	private boolean matchCore(char[] s, char[] p, int i, int j) {
		int sLength = s.length;
		int pLength = p.length;

		if (i >= sLength && j >= pLength) {
			return true;
		} else if (i < sLength && j >= pLength) {
			return false;
		}else if(i >= sLength && j < pLength){
			//str��β��patternδ��β(��һ��ƥ��ʧ�ܣ���Ϊa*����ƥ��0���ַ�)
			//ֻ��patternʣ�µĲ�������a*b*c*����ʽ����ƥ��ɹ�
			if(j + 1 < pLength && p[j + 1] == '*'){
				return matchCore(s, p, i, j + 2);
			}else return false;
		}
		
		if (j + 1 < pLength && p[j + 1] == '*') {
			if ((s[i] == p[j]) || (p[j] == '.' && i < sLength)) {
				return matchCore( s, p, i + 1, j + 2) 	//match one
					|| matchCore( s, p, i + 1, j)		//match more than one
					|| matchCore( s, p, i, j + 2);		//match none,ignore the regex	
			} else {
				return matchCore(s, p, i, j + 2);
			}
		} else if (s[i] == p[j] || (p[j] == '.')) {
			return matchCore(s, p, i + 1, j + 1);
		}
		
		return false;
	}

}
