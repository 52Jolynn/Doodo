package com.laud.doodo.string;

import java.util.regex.Pattern;

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

	/**
	 * 检查Email是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		String regex = "([a-zA-Z0-9 \\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
		if (Pattern.matches(regex, email)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查手机号码是否合法，中国大陆
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		String regex = "^1[358][0-9]\\d{8}";
		if (Pattern.matches(regex, mobile)) {
			return true;
		}
		return false;
	}
}
