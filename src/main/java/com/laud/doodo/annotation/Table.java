package com.laud.doodo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-10-15 下午2:37:09
 * @copyright: www.armisi.com.cn
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	/**
	 * 表名
	 * 
	 * @return
	 */
	String tableName();

	/**
	 * 类型别名
	 * 
	 * @return
	 */
	String typeAlias();

	/**
	 * 用于国际化，资源文件名称
	 * 
	 * @return
	 */
	String resourceName() default "";
}
