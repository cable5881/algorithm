package com.lqb.algorithm.cvteTest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:��HashMap�е����ݷ�װ��TargetVo��
 * @author:JackBauer
 * @date:2016��3��18�� ����3:01:58
 */
public class BeanTest {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "lqb");
		map.put("age", 12);
		map.put("isMarried", true);
		TargetVo targetVo = null;
		try {
			targetVo = getTargetVo(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(targetVo);
	}

	public static TargetVo getTargetVo(Map<String, Object> map) throws Exception {
		//�쳣��Ҫ����Ϊ�˼�ֱ���׳�ȥ��
		TargetVo targetVo = new TargetVo();

		Class<? extends TargetVo> targetVoClass = targetVo.getClass();

		Field[] fields = targetVoClass.getDeclaredFields();

		for (String key : map.keySet()) {
			for (Field field : fields) {
				if (field.getName().equals(key)) {
					field.setAccessible(true);
					field.set(targetVo, map.get(key));
				}
			}
		}

		return targetVo;
	}

}

class TargetVo {

	private String name;
	private int age;
	private boolean isMarried;

	@Override
	public String toString() {
		return "TargetVo [name=" + name + ", age=" + age + ", isMarried=" + isMarried + "]";
	}

}
