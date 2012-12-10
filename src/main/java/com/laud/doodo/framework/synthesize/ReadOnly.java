package com.laud.doodo.framework.synthesize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只读属性
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-11-8
 * @copyright: www.dreamoriole.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {
}
