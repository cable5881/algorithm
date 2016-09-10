package com.lqb.alibaba._2017.jvm;

public class JVM_Object {
	private int size;// 对象内存大小
	private boolean isReferred;// 是否被引用 
	private int age;// 对象在Young区的年龄，用于判断是否足够老
	
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
