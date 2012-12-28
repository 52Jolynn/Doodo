package com.laud.doodo.framework.synthesize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动生成get/set方法
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-8
 * @copyright: www.dreamoriole.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Synthesize {
}
