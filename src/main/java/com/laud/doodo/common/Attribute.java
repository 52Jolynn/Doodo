package com.laud.doodo.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.laud.doodo.exception.ExceptionFactory;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-23 上午10:55:34
 * @copyright: www.armisi.com.cn
 */
public abstract class Attribute implements Mappable<Attribute, String, String>,
		ValueVisitor<String> {
	public Attribute() {
	}

	public Map<String, String> map(ValueVisitor<String> visitor) {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String value = "";
			try {
				Object obj = field.get(this);
				value = visitor.accept(obj);
			} catch (IllegalArgumentException e) {
				throw ExceptionFactory.wrapException("argument error!", e);
			} catch (IllegalAccessException e) {
				throw ExceptionFactory.wrapException("permission error!", e);
			}
			map.put(field.getName(), value);
		}
		return map;
	}

	public Attribute set(Map<String, String> map, ValueVisitor<String> visitor) {
		Set<String> keys = map.keySet();
		Class<?> clazz = getClass();
		for (String key : keys) {
			String value = map.get(key);
			try {
				Field field = clazz.getDeclaredField(key);
				field.setAccessible(true);
				field.set(this, visitor.target(field.getType(), value));
			} catch (NoSuchFieldException e) {
				throw ExceptionFactory.wrapException("no such field!", e);
			} catch (IllegalArgumentException e) {
				throw ExceptionFactory.wrapException("argument error!", e);
			} catch (IllegalAccessException e) {
				throw ExceptionFactory.wrapException("permission error!", e);
			}
		}
		return this;
	}
}