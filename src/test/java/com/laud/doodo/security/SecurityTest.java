package com.laud.doodo.security;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-11 下午3:21:28
 * @copyright: www.dreamoriole.com
 */
public class SecurityTest extends TestCase {
	public void testDES() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		try {
			String key = DESCoder.initKey(source);
			byte[] data = DESCoder
					.encrypt(source.getBytes(), key, DESCoder.DES);
			byte[] result = DESCoder.decrypt(data, key, DESCoder.DES);
			assertEquals(source, new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testBase64() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		String result = SecurityCoder.base64Encoder(source.getBytes());
		byte[] data = SecurityCoder.base64Decoder(result);
		assertEquals(source, new String(data));
	}
}
