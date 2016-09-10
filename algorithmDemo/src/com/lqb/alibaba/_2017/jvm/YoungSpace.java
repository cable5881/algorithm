package com.lqb.alibaba._2017.jvm;

import java.util.ArrayList;

public class YoungSpace {
	private static ArrayList<JVM_Object> eden;
	private static ArrayList<JVM_Object> from;
	private static ArrayList<JVM_Object> to;
	
	private static final int edenTotalSize;
	private static final int survivorTotalSize;
	private static int edenLeftSize;
	private static int survivorLeftSize;
	private static boolean currentUsingSpace;
	
	static {
		edenTotalSize = JVM_Constant.EDEN_SIZE;
		survivorTotalSize = JVM_Constant.SURVIVOR_SIZE;
		currentUsingSpace = true;
		edenLeftSize = edenTotalSize;
		survivorLeftSize = survivorTotalSize;
		
		eden = new ArrayList<>();
		from = new ArrayList<>();
		to = new ArrayList<>();
	}
	
	public static int getEdensize() {
		return edenTotalSize;
	}

	public static int getSurvivorsize() {
		return survivorTotalSize;
	}
	
	public static int getEdenLeftSize() {
		return edenLeftSize;
	}

	public static void setEdenLeftSize(int edenLeftSize) {
		YoungSpace.edenLeftSize = YoungSpace.edenLeftSize - edenLeftSize;
	}

	public static int getSurvivorLeftSize() {
		return survivorLeftSize;
	}

	public static void setSurvivorLeftSize(int survivorLeftSize) {
		YoungSpace.survivorLeftSize = YoungSpace.survivorLeftSize - survivorLeftSize;
	}
	
	public static void add(JVM_Object obj) {
		eden.add(obj);
	}
	
	public static void moveToAnother () {
		if(currentUsingSpace) {
			for(JVM_Object obj : eden) {
				obj.setAge(obj.getAge() + 1);
				to.add(obj);
			}
			
			for(JVM_Object obj : from) {
				if(!obj.isReferred()) {
					continue;
				}
				
				if(obj.getAge() > JVM_Constant.OLD_AGE) {
					OldSpace.add(obj);
				} else {
					to.add(obj);
				}
				obj.setAge(obj.getAge() + 1);
			}
			
			from.clear();
		} else {
			for(JVM_Object obj : eden) {
				obj.setAge(obj.getAge() + 1);
				from.add(obj);
			}
			
			for(JVM_Object obj : to) {
				if(!obj.isReferred()) {
					continue;
				}
				
				if(obj.getAge() > JVM_Constant.OLD_AGE) {
					OldSpace.add(obj);
				} else {
					from.add(obj);
				}
				obj.setAge(obj.getAge() + 1);
			}
			
			to.clear();
		}
		
		eden.clear();
		edenLeftSize = 0;
	}
	
}
