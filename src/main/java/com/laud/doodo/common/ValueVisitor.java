package com.laud.doodo.common;

/**
 * 数值访问器
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-23 上午10:11:25
 * @copyright: www.dreamoriole.com
 */
public interface ValueVisitor<T> {
	/**
	 * 将指定值转成指定类型的值
	 * 
	 * @param value
	 *            值
	 * @return 返回值
	 */
	public T accept(Object value);

	/**
	 * 将值转成目标对象类型的值
	 * 
	 * @param targetClass
	 *            目标对象类型
	 * @param value
	 *            值
	 * @return
	 */
	public Object target(Class<?> targetClass, Object value);
}
