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
		return Base64.decodeBase64(data);
	}

	/**
	 * base64编码
	 * 
	 * @param data
	 * @return
	 */
	public static String base64Encoder(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	/**
	 * md5算法，生成消息摘要
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] md5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(MD5);
		digest.update(data);
		return digest.digest();
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
