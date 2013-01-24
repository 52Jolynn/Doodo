package com.laud.doodo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-1-11 下午1:21:33
 * @copyright: www.armisi.com.cn
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * 字段长度
	 * 
	 * @return
	 */
	public int length() default 0;

	/**
	 * 正则表达式
	 * 
	 * @return
	 */
	public String regex() default "";

	/**
	 * 名称
	 * 
	 * @return
	 */
	public String displayName() default "";

	/**
	 * <pre>
	 * 资源键名，若配置此值，则displayName失效
	 *  用于国际化，需结合{@link Table}中的resourceName使用。
	 * </pre>
	 * 
	 * @return
	 */
	public String resourceKey() default "";
}
