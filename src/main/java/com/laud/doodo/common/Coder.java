package com.laud.doodo.common;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-12-8 上午10:20:46
 * @copyright: www.dreamoriole.com
 */
public interface Coder {
	/**
	 * 编码
	 * 
	 * @param object
	 * @return
	 */
	public Object encode(Object object);

	/**
	 * 解码
	 * 
	 * @param object
	 * @return
	 */
	public Object decode(Object object);
}
