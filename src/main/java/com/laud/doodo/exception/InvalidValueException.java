package com.laud.doodo.exception;

/**
 * 错误的值异常
 * 
 * @author Laud
 * @date 2012-4-12
 */
public class InvalidValueException extends Exception {
	private static final long serialVersionUID = -6688821818728356670L;

	public InvalidValueException(String message) {
		super(message);
	}
}
