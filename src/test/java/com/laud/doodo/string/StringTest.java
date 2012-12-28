package com.laud.doodo.string;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-24
 * @copyright: www.dreamoriole.com
 */
public class StringTest extends TestCase {
	public void testInitial2LowerCase() {
		String value = "abcde";
		assertEquals(value,
				StringUtils.changeFirstCharacterCapitalized(value, false));

		value = "Abcde";
		assertEquals("abcde",
				StringUtils.changeFirstCharacterCapitalized(value, false));

		value = "中国";
		assertEquals(value,
				StringUtils.changeFirstCharacterCapitalized(value, false));
	}

	public void testInitial2UpperCase() {
		String value = "Abcde";
		assertEquals(value,
				StringUtils.changeFirstCharacterCapitalized(value, true));

		value = "abcde";
		assertEquals("Abcde",
				StringUtils.changeFirstCharacterCapitalized(value, true));

		value = "中国";
		assertEquals(value,
				StringUtils.changeFirstCharacterCapitalized(value, true));
	}

	public void testCheckMobile() {
		String mobile = "13828452853";
		assertTrue(StringUtils.checkMobile(mobile));
		assertTrue(StringUtils.checkMobile("15845656658"));
		assertTrue(StringUtils.checkMobile("18845656658"));
		assertFalse(StringUtils.checkMobile("123"));
		assertFalse(StringUtils.checkMobile("123333"));
		assertFalse(StringUtils.checkMobile("123333333331"));
		assertFalse(StringUtils.checkMobile("1233333333312"));
		assertFalse(StringUtils.checkMobile(""));
		assertFalse(StringUtils.checkMobile("1382845285322"));
		assertFalse(StringUtils.checkMobile("1582845285322"));
		assertFalse(StringUtils.checkMobile("1982845285322"));
		assertFalse(StringUtils.checkMobile("11828452853"));
		assertFalse(StringUtils.checkMobile("12828452853"));
		assertFalse(StringUtils.checkMobile("16828452853"));
		assertFalse(StringUtils.checkMobile("17828452853"));
		assertFalse(StringUtils.checkMobile("19828452853"));
	}
}
