package com.laud.doodo.math;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-10-12
 * @copyright: www.dreamoriole.com
 */
public class NumberUtils {
	/**
	 * 判断是否是数字类型
	 * 
	 * @param clazz
	 */
	public static boolean isNumberType(Class<?> clazz) {
		return (clazz == Number.class || clazz.getSuperclass() == Number.class || isPrimitiveNumberType(clazz));
	}

	/**
	 * 判断是否是原始数据类型
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isPrimitiveNumberType(Class<?> clazz) {
		if (!clazz.isPrimitive()) {
			return false;
		} else if (clazz != boolean.class && clazz != void.class
				&& clazz != char.class) {
			return true;
		}
		return false;
	}
}
