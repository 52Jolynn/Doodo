package com.laud.doodo.web;

import java.io.File;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class WebUtils {
	/**
	 * 取得
	 * 
	 * @return
	 */
	public static String getWebClassesPath() {
		String path = WebUtils.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;
	}

	/**
	 * 取得WEB-INF路径
	 * 
	 * @return
	 */
	public static String getWebINFPath() {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(1, path.indexOf("WEB-INF") + 8);
			path = path.replace("/", File.separator);
			return path;
		}
		return null;
	}
}
