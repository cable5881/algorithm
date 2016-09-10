package com.lqb.alibaba._2017.jvm;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JVM jvm = new JVM();
		
		JVM_Object o1 = new JVM_Object(100);
		JVM_Object o2 = new JVM_Object(200);
		JVM_Object o3 = new JVM_Object(300);
	
		jvm.newObject(o1);
		jvm.newObject(o2);
		jvm.newObject(o3);
		
		Thread.sleep(2000);
		
		o1.setReferred(false);// Ê§È¥ÒýÓÃ
		o2.setReferred(false);
		
		JVM_Object o4 = new JVM_Object(800);
		jvm.newObject(o4);
	}

}
