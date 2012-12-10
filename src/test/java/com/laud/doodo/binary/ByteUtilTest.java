package com.laud.doodo.binary;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class ByteUtilTest extends TestCase {
	public void testByteIntConvert() {
		int expect = 8;
		byte[] bytes = ByteUtils.intToByteArray(expect);
		int i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = -8;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = -255;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = 255;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = 128;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = -128;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = -0xffffffff;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = 0xffffffff;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = -1;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);

		expect = 0;
		bytes = ByteUtils.intToByteArray(expect);
		i = ByteUtils.byteArrayToInt(bytes);
		assertEquals(expect, i);
	}
}
