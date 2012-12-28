package com.laud.doodo.common;

/**
 * 单一键值对
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-26 上午11:08:20
 * @copyright: www.dreamoriole.com
 */
public class KeyValue<T> {
	private String key;
	private T value;

	public KeyValue() {
	}

	/**
	 * 构造函数
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public KeyValue(String key, T value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 取得键值对的键
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置键值对的键
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 取得键值对的值
	 * 
	 * @return
	 */
	public T getValue() {
		return value;
	}

	/**
	 * 设置键值对的值
	 * 
	 * @param value
	 */
	public void setValue(T value) {
		this.value = value;
	}
}
