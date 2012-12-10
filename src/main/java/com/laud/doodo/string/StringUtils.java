package com.laud.doodo.string;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-11 下午3:27:31
 * @copyright: www.dreamoriole.com
 */
public class StringUtils {
	/**
	 * 字节数组转为String
	 * 
	 * @param data
	 * @return
	 */
	public static String byteToString(byte[] data) {
		return new String(data);
	}

	/**
	 * 改变首字母大小写
	 * 
	 * @param value
	 * @param capitalized
	 *            是否大写
	 * @return
	 */
	public static String changeFirstCharacterCapitalized(String value,
			boolean capitalized) {
		String firstCharacter = value.substring(0, 1);
		if (capitalized) {
			return value.replaceFirst(firstCharacter,
					firstCharacter.toUpperCase());
		} else {
			return value.replaceFirst(firstCharacter,
					firstCharacter.toLowerCase());
		}
	}
}
