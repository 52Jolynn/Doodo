package com.laud.doodo.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 加/解密类
 * 
 * @author Laud
 * @date 2012-2-29
 */
public final class SecurityCoder {
	private final static String MD5 = "MD5";
	private final static String SHA = "SHA";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * 
	 * <pre>
	 * 
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * base64解码
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] base64Decoder(String data) {
		return base64Decoder(data, false);
	}

	/**
	 * base64解码
	 * 
	 * @param data
	 * @param urlSafe
	 * @return
	 */
	public static byte[] base64Decoder(String data, boolean urlSafe) {
		Base64 base64 = new Base64(urlSafe);
		return base64.decode(data);
	}

	/**
	 * base64编码
	 * 
	 * @param data
	 * @return
	 */
	public static String base64Encoder(byte[] data) {
		return base64Encoder(data, false);
	}

	/**
	 * base64编码
	 * 
	 * @param data
	 * @param urlSafe
	 * @return
	 */
	public static String base64Encoder(byte[] data, boolean urlSafe) {
		Base64 base64 = new Base64(urlSafe);
		return base64.encodeToString(data);
	}

	/**
	 * md5算法，生成消息摘要
	 * 
	 * @param data
	 * @return
	 */
	public static String md5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(MD5);
		digest.update(data);
		byte[] bytes = digest.digest();
		int length = bytes.length;
		StringBuffer buffer = new StringBuffer(length * 2);

		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < length; j++) {
			buffer.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buffer.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buffer.toString();
	}

	/**
	 * SHA-1算法，生成消息摘要
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] sha(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(SHA);
		digest.update(data);
		return digest.digest();
	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return base64Encoder(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encryptHMAC(byte[] data, String key)
			throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(base64Decoder(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);
	}
}
