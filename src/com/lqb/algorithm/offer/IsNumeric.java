package com.lqb.algorithm.offer;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * 
 * @Author:JackBauer
 * @Date:2016年7月20日 下午5:47:16
 */
public class IsNumeric {

	public static void main(String[] args) {
//		double d = 3.14156e+0;
//		double f = 400.;
//		double g = .123;
//		System.out.println(f);
		
		IsNumeric test = new IsNumeric();
		String num1 = String.valueOf(1);
		String num2 = String.valueOf(2.3);
		String num3 = String.valueOf('+');
		String num4 = String.valueOf("+1");
		String num5 = String.valueOf("+2.3");
		String num6 = String.valueOf("@");
		String num7 = String.valueOf("1#23");
		String num8 = String.valueOf("1.2#");
		String num9 = String.valueOf("3.13e+2");
		String num10 = String.valueOf("3.");
		String num11 = String.valueOf("12e");
		String num12 = String.valueOf("1.2.3");
		String num13 = null;
		String num14 = String.valueOf("+-1");
		String num15 = String.valueOf("12e4.3");
		String num16 = String.valueOf("5e4");
		String num17 = String.valueOf("-.123");//true
		String num18 = String.valueOf("..123");//true
		
		System.out.println(num1 + " : " + test.isNumeric(num1.toCharArray()));
		System.out.println(num2 + " : " + test.isNumeric(num2.toCharArray()));
		System.out.println(num3 + " : " + test.isNumeric(num3.toCharArray()));
		System.out.println(num4 + " : " + test.isNumeric(num4.toCharArray()));
		System.out.println(num5 + " : " + test.isNumeric(num5.toCharArray()));
		System.out.println(num6 + " : " + test.isNumeric(num6.toCharArray()));
		System.out.println(num7 + " : " + test.isNumeric(num7.toCharArray()));
		System.out.println(num8 + " : " + test.isNumeric(num8.toCharArray()));
		System.out.println(num9 + " : " + test.isNumeric(num9.toCharArray()));
		System.out.println(num10 + " : " + test.isNumeric(num10.toCharArray()));
		System.out.println(num11 + " : " + test.isNumeric(num11.toCharArray()));
		System.out.println(num12 + " : " + test.isNumeric(num12.toCharArray()));
		System.out.println(num13 + " : " + test.isNumeric(null));
		System.out.println(num14 + " : " + test.isNumeric(num14.toCharArray()));
		System.out.println(num15 + " : " + test.isNumeric(num15.toCharArray()));
		System.out.println(num16 + " : " + test.isNumeric(num16.toCharArray()));
		System.out.println(num17 + " : " + test.isNumeric(num17.toCharArray()));
		System.out.println(num18 + " : " + test.isNumeric(num18.toCharArray()));
	}

	public boolean isNumeric(char[] str) {
		if(str == null || str.length < 1){
			return false;
		}
		
		int len = str.length;
		int i = 0;
		
		//处理开头，如果开头有+-，则i + 1否则不变
		if(isSignAtHead(str[i])){
			i++;
		}
		//排除掉'+'、'-'
		if(i >= len){
			return false;
		}
		
		//没有必要，否则无法排除掉..123类似的情况
//		if(!isInteger(str[i]) && str[i] != '.'){
//			return false;
//		}else i++;
		
		i = indexOfAfterNumber(str, i); 
		
		if(i < len){
			if(str[i] == '.'){
				 i = indexOfAfterNumber(str, ++i);
				 if(i >= len){
					 return true;
				 }
			}
			
			if(str[i] == 'e' || str[i] == 'E'){
				return isRearOfE(str, ++i);
			}
			
			return false;
		}
		
		return true;
	}
	
	private int indexOfAfterNumber(char[] ch, int start){
		int i;
		for(i = start; i < ch.length; i++){
			if(!isInteger(ch[i])){
				return i;
			}
		}
		
		return i;
	}

	private boolean isSignAtHead(char c) {
		if (c == '+' || c == '-') {
			return true;
		} else
			return false;
	}
	
	private boolean isInteger(char c){
		if(c <= '9' && c >= '0'){
			return true;
		}else return false;
	}
	
	private boolean isRearOfE(char[] ch, int start){
		if(start >= ch.length){
			return false;
		}
		
		int i = isSignAtHead(ch[start]) ? start + 1 : start;
		
		if(i >= ch.length){
			return false;
		}
		
		//indexOfAfterNumbe()
		while(i < ch.length){
			if(!isInteger(ch[i++])){
				return false;
			}
		}
		
		return true;
	}
	
}
