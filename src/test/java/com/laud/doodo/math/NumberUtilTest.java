package com.laud.doodo.math;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-10-12
 * @copyright: www.armisi.com.cn
 */
public class NumberUtilTest extends TestCase {
	public void testIsNumberType() {
		assertTrue(NumberUtils.isNumberType(int.class));
		assertTrue(NumberUtils.isNumberType(Integer.class));
		assertFalse(NumberUtils.isNumberType(String.class));
		assertFalse(NumberUtils.isNumberType(boolean.class));
		assertFalse(NumberUtils.isNumberType(char.class));
		assertFalse(NumberUtils.isNumberType(Boolean.class));
		assertFalse(NumberUtils.isNumberType(Character.class));
	}

	public void testIsPrimitiveNumberType() {
		assertTrue(NumberUtils.isNumberType(int.class));
		assertFalse(NumberUtils.isNumberType(Integer.class));
		assertFalse(NumberUtils.isNumberType(String.class));
		assertFalse(NumberUtils.isNumberType(boolean.class));
		assertFalse(NumberUtils.isNumberType(char.class));
		assertFalse(NumberUtils.isNumberType(Boolean.class));
		assertFalse(NumberUtils.isNumberType(Character.class));
	}
}
