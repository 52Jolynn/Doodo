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
}
