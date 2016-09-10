package com.lqb.alibaba._2017.jvm;

public class JVM {
	public boolean newObject(JVM_Object obj) {
		if(obj.getSize() < YoungSpace.getEdenLeftSize()) {
			YoungSpace.add(obj);
			YoungSpace.setEdenLeftSize(obj.getSize());
		}else if (obj.getSize() > YoungSpace.getSurvivorLeftSize()) {
			minorGC();
			OldSpace.add(obj);
		}
			
		return true;
	}
	
	public void minorGC() {
		YoungSpace.moveToAnother();
	}
	
	public void fullGC() {
		OldSpace.fullGC();
	}
}
