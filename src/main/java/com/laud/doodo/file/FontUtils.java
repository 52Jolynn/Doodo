package com.laud.doodo.file;

import java.awt.Font;

import sun.font.FontDesignMetrics;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-3-14 上午9:03:34
 * @copyright: www.armisi.com.cn
 */
public class FontUtils {
	/**
	 * 取得指定字体的高度
	 * 
	 * @param font
	 *            字体
	 * @return
	 */
	public static int getFontHeight(Font font) {
		FontDesignMetrics fdm = FontDesignMetrics.getMetrics(font);
		return fdm.getHeight();
	}

	/**
	 * 取得指定字体下字符串的宽度
	 * 
	 * @param font
	 *            字体
	 * @param value
	 *            字符串
	 * @return
	 */
	public static int getStringWidthWithFont(Font font, String value) {
		FontDesignMetrics fdm = FontDesignMetrics.getMetrics(font);
		return fdm.stringWidth(value);
	}
}
