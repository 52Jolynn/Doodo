package com.laud.doodo.exception;

/**
 * 异常包装器
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-28
 * @copyright: www.armisi.com.cn
 */
public class ExceptionFactory {
	/**
	 * 将Exception包装成RuntimeException
	 * 
	 * @param exception
	 * @return
	 */
	public static RuntimeException wrapException(String message,
			Exception exception) {
		return new RuntimeException(message, exception);
	}
}
