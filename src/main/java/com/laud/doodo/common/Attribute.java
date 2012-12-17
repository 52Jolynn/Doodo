package com.laud.doodo.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.laud.doodo.exception.ExceptionFactory;
import com.laud.doodo.spring.util.SpringReflectionUtils;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-23 上午10:55:34
 * @copyright: www.dreamoriole.com
 */
public abstract class Attribute implements Mappable<Attribute, String, String>,
		ValueVisitor<String> {
	public Attribute() {
	}

	public Map<String, String> map(final ValueVisitor<String> visitor) {
		final Map<String, String> map = new HashMap<String, String>();
		final Attribute thiz = this;
		Class<?> clazz = getClass();
		try {
			SpringReflectionUtils.doWithFields(clazz,
					new SpringReflectionUtils.FieldCallback() {
						public void doWith(Field field)
								throws IllegalArgumentException,
								IllegalAccessException {
							field.setAccessible(true);
							String value = "";
							Object obj = field.get(thiz);
							value = visitor.accept(obj);
							map.put(field.getName(), value);
						}
					}, SpringReflectionUtils.COPYABLE_FIELDS);
		} catch (IllegalArgumentException e) {
			throw ExceptionFactory.wrapException("argument exception!", e);
		}

		return map;
	}

	public Attribute set(Map<String, String> map, ValueVisitor<String> visitor) {
		Set<String> keys = map.keySet();
		Class<?> clazz = getClass();
		for (String key : keys) {
			String value = map.get(key);
			try {
				Field field = SpringReflectionUtils.findField(clazz, key);
				field.setAccessible(true);
				field.set(this, visitor.target(field.getType(), value));
			} catch (IllegalArgumentException e) {
				throw ExceptionFactory.wrapException("argument exception!", e);
			} catch (IllegalAccessException e) {
				throw ExceptionFactory
						.wrapException("permission exception!", e);
			}
		}
		return this;
	}
}