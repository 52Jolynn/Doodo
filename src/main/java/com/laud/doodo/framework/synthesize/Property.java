package com.laud.doodo.framework.synthesize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要生成get/set方法的域
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-8
 * @copyright: www.dreamoriole.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
	/**
	 * 要生成get/set方法的名称
	 * 
	 * @return
	 */
	String name();

	/**
	 * 是否只读
	 * 
	 * @return
	 */
	boolean readOnly() default false;
}
