package com.laud.doodo.common;

import java.util.Map;

/**
 * <pre>
 * T 对象类型
 * K 键类型
 * V 值类型
 * </pre>
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-23 上午9:43:42
 * @copyright: www.dreamoriole.com
 */
public interface Mappable<T, K, V> {
	/**
	 * 键值映射集合
	 * 
	 * @param visitor
	 *            值处理器
	 * @return
	 */
	public Map<K, V> map(ValueVisitor visitor);

	/**
	 * 键值映射转实体对象
	 * 
	 * @param map
	 *            键值集合
	 * @param visitor
	 *            值处理器
	 * @return
	 */
	public T set(Map<K, V> map, ValueVisitor visitor);
}
