package com.laud.doodo.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES-Data Encryption Standard,即数据加密算法。是IBM公司于1975年研究成功并公开发表的。
 * DES算法的入口参数有三个:Key、 Data、Mode。其中Key为8个字节共64位,
 * 是DES算法的工作密钥;Data也为8个字节64位,是要被加密或被解密的数据 ;Mode为DES的工作方式,有两种:加密或解密。
 * DES算法把64位的明文输入块变为64位的密文输出块,它所使用的密钥也是64位。
 * 
 * <pre>
 * 
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR) 
 * DES                  key size must be equal to 56 
 * DESede(TripleDES)    key size must be equal to 112 or 168 
 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
 * RC2                  key size must be between 40 and 1024 bits 
 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-09-23
 */
public class DESCoder {
	/**
	 * ALGORITHM 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * 
	 * <pre>
	 * 
	 * DES                  key size must be equal to 56 
	 * DESede(TripleDES)    key size must be equal to 112 or 168 
	 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
	 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
	 * RC2                  key size must be between 40 and 1024 bits 
	 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
	 * </pre>
	 * 
	 * 在Key toKey(byte[] key)方法中使用下述代码
	 * 
	 * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
	 * <code> 
	 * DESKeySpec dks = new DESKeySpec(key); 
	 * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
	 * SecretKey secretKey = keyFactory.generateSecret(dks); 
	 * </code>
	 */
	public static final String DESEDE = "DESede";
	public static final String AES = "AES";
	public static final String BLOWFISH = "Blowfish";
	public static final String RC2 = "RC2";
	public static final String RC4 = "RC4";
	public final static String DES = "DES";

	/**
	 * 生成密钥
	 * 
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private static Key toKey(byte[] key, String algorithm)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		DESKeySpec spec = new DESKeySpec(key);
		SecretKey secretKey = new SecretKeySpec(key, algorithm);

		if (algorithm.equals(DES)) {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(DES);
			secretKey = factory.generateSecret(spec);
		}
		return secretKey;
	}

	/**
	 * * DES加密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @param algorithm
	 *            算法
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] encrypt(byte[] data, String key, String algorithm)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			BadPaddingException, IllegalBlockSizeException {
		Key k = toKey(SecurityCoder.base64Decoder(key), algorithm);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * DES解密
	 * 
	 * @param data
	 * @param key
	 *            密钥
	 * @param algorithm
	 *            算法
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(byte[] data, String key, String algorithm)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			BadPaddingException, IllegalBlockSizeException {
		Key k = toKey(SecurityCoder.base64Decoder(key), algorithm);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String initKey() throws NoSuchAlgorithmException {
		return initKey(null);
	}

	/**
	 * 根据种子生成密钥字符串
	 * 
	 * @param seed
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String initKey(String seed) throws NoSuchAlgorithmException {
		String rValue = null;
		SecureRandom random = null;
		if (seed != null) {
			random = new SecureRandom(seed.getBytes());
		} else {
			random = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(DES);
		kg.init(random);
		SecretKey secretKey = kg.generateKey();
		rValue = SecurityCoder.base64Encoder(secretKey.getEncoded());

		return rValue;
	}
}
