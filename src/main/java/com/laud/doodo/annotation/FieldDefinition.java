package com.laud.doodo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类域标记
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-26 上午9:12:59
 * @copyright: www.dreamoriole.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDefinition {
	/**
	 * 属性名称
	 * 
	 * @return
	 */
	public String propertyName();

	/**
	 * 数据库列名，不需要持久化的属性字段使用默认值即可
	 * 
	 * @return
	 */
	public String columnName() default "";

	/**
	 * 映射名称，不需要映射的属性字段使用默认值即可
	 * 
	 * @return
	 */
	public String mappingName() default "";

	/**
	 * 是否需要转义
	 * 
	 * @return
	 */
	public boolean escape() default false;
}
