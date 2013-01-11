package com.laud.doodo.common;

/**
 * 数值访问器
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-23 上午10:11:25
 * @copyright: www.dreamoriole.com
 */
public interface ValueVisitor {

	/**
	 * 将传入值转成targetClass指定的对象类型值
	 * 
	 * @param targetClass
	 *            目标对象类型
	 * @param value
	 *            值
	 * @param param
	 *            参数
	 * @return
	 */
	public Object target(Class<?> targetClass, Object value, Object param);
}
