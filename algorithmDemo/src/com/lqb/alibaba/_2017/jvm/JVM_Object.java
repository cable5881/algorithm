package com.lqb.alibaba._2017.jvm;

public class JVM_Object {
	private int size;// �����ڴ��С
	private boolean isReferred;// �Ƿ����� 
	private int age;// ������Young�������䣬�����ж��Ƿ��㹻��
	
	public JVM_Object(int size) {
		this.size = size;
		this.isReferred = true;
		this.age = 0;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isReferred() {
		return isReferred;
	}
	public void setReferred(boolean isReferred) {
		this.isReferred = isReferred;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
