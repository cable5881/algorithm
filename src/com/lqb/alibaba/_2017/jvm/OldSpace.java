package com.lqb.alibaba._2017.jvm;

import java.util.ArrayList;
import java.util.Iterator;

public class OldSpace {
	private static ArrayList<JVM_Object> old;
	
	private static final int oldSize;
	
	static {
		oldSize = JVM_Constant.OLD_SIZE;
		old = new ArrayList<>();
	}

	public static int getOldsize() {
		return oldSize;
	}
	
	public static void add(JVM_Object obj) {
		old.add(obj);
	}

	public static void fullGC() {
		Iterator<JVM_Object> it = old.iterator();
		while(it.hasNext()) {
			JVM_Object obj = it.next();
			if(!obj.isReferred()) {
				it.remove();
			}
		}
	}
}
