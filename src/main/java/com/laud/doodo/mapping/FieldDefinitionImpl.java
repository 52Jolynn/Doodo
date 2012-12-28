package com.laud.doodo.mapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.laud.doodo.annotation.FieldDefinition;
import com.laud.doodo.common.Coder;
import com.laud.doodo.common.ValueVisitor;
import com.laud.doodo.exception.ExceptionFactory;
import com.laud.doodo.spring.utils.SpringReflectionUtils;

/**
 * 标记{@link FieldDefinition}实现类
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-22 下午1:42:11
 * @copyright: www.dreamoriole.com
 */
public abstract class FieldDefinitionImpl implements Coder {
	private static Map<Class<?>, ValueVisitor> visitors = null;

	/**
	 * 注册值处理器
	 * 
	 * @param targetClass
	 *            目标类型
	 * @param visitor
	 *            值处理器
	 */
	public void registerValueVisitor(Class<?> targetClass, ValueVisitor visitor) {
		if (visitors == null) {
			visitors = new HashMap<Class<?>, ValueVisitor>(4);
		}
		visitors.put(targetClass, visitor);
	}

	/**
	 * 移除值处理器
	 * 
	 * @param targetClass
	 */
	public void removeValueVisitor(Class<?> targetClass) {
		if (visitors != null) {
			visitors.remove(targetClass);
		}
	}

	/**
	 * 映射-值的集合
	 * 
	 * @param defaultVisitor
	 *            值处理器
	 * @return
	 */
	public Map<String, Object> valuesWithMappingNames(
			ValueVisitor defaultVisitor) {
		return values(0, defaultVisitor);
	}

	/**
	 * 属性-值的集合
	 * 
	 * @param defaultVisitor
	 *            值处理器
	 * @return
	 */
	public Map<String, Object> valuesWithPropertyNames(
			ValueVisitor defaultVisitor) {
		return values(1, defaultVisitor);
	}

	/**
	 * 列名-值的集合
	 * 
	 * @param defaultVisitor
	 *            值处理器
	 * @return
	 */
	public Map<String, Object> valuesWithColumnNames(ValueVisitor defaultVisitor) {
		return values(2, defaultVisitor);
	}

	/**
	 * 根据映射名-值集合设置对象值
	 * 
	 * @param map
	 * @param defaultVisitor
	 *            默认值处理器
	 */
	public void setValuesWithMappingNameMap(final Map<String, Object> map,
			final ValueVisitor defaultVisitor) {
		Class<?> clazz = getClass();
		final FieldDefinitionImpl thiz = this;
		try {
			SpringReflectionUtils.doWithFields(clazz,
					new SpringReflectionUtils.FieldCallback() {

						public void doWith(Field field)
								throws IllegalArgumentException,
								IllegalAccessException {
							if (!field
									.isAnnotationPresent(FieldDefinition.class)) {
								return;
							}
							FieldDefinition def = field
									.getAnnotation(FieldDefinition.class);
							String key = def.mappingName();
							if (map.containsKey(key)) {
								field.setAccessible(true);
								Object value = map.get(key);
								Class<?> targetClass = field.getClass();
								Map<String, Object> params = new HashMap<String, Object>(
										1);
								params.put("escape", def.escape());
								if (visitors.containsKey(targetClass)) {
									ValueVisitor visitor = visitors
											.get(targetClass);
									value = visitor.target(targetClass, value,
											params);
								} else if (defaultVisitor != null) {
									value = defaultVisitor.target(
											field.getClass(), value, params);
								}
								field.set(thiz, value);
							}
						}
					}, SpringReflectionUtils.COPYABLE_FIELDS);
		} catch (IllegalArgumentException e) {
			throw ExceptionFactory.wrapException("argument exception!", e);
		}
	}

	/**
	 * 
	 * @param type
	 *            0-映射名，1-属性名，2-列名
	 * @param defaultVisitor
	 *            默认值处理器
	 * @return
	 */
	private Map<String, Object> values(final int type,
			final ValueVisitor defaultVisitor) {
		Class<?> clazz = getClass();
		final FieldDefinitionImpl thiz = this;
		final Map<String, Object> map = new HashMap<String, Object>();
		SpringReflectionUtils.doWithFields(clazz,
				new SpringReflectionUtils.FieldCallback() {

					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {
						if (!field.isAnnotationPresent(FieldDefinition.class)) {
							return;
						}
						FieldDefinition def = field
								.getAnnotation(FieldDefinition.class);
						// 映射名
						String key = def.mappingName();
						if (type == 1) {// 属性名
							key = def.propertyName();
						} else if (type == 2) {// 列名
							key = def.columnName();
						}
						// 忽略未声明的值
						if (key.equals("")) {
							return;
						}
						Object value = null;
						field.setAccessible(true);
						value = field.get(thiz);
						Class<?> targetClass = field.getType();
						Map<String, Object> params = new HashMap<String, Object>(
								1);
						params.put("escape", def.escape());
						if (visitors.containsKey(targetClass)) {
							ValueVisitor visitor = visitors.get(targetClass);
							value = visitor.target(targetClass, value, params);
						} else if (defaultVisitor != null) {
							value = defaultVisitor.target(targetClass, value,
									params);
						}
						map.put(key, value);
					}
				}, SpringReflectionUtils.COPYABLE_FIELDS);

		return map;
	}
}
