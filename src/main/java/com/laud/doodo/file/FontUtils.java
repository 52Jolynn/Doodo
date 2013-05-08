package com.laud.doodo.file;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;

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
		if (font == null) {
			return 0;
		}
		FontMetrics fm = getFontMetrics(font);
		return fm.getHeight();
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
		if (font == null || value == null) {
			return 0;
		}

		FontMetrics fm = getFontMetrics(font);
		return fm.stringWidth(value);
	}

	/**
	 * 通过JComponent取得FontMetrics对象
	 * 
	 * @param font
	 * @return
	 */
	public static FontMetrics getFontMetrics(Font font) {
		JComponent jc = new JLabel();
		return jc.getFontMetrics(font);
	}

	/**
	 * 通过Graphics2D取得FontMetrics对象
	 * 
	 * @param font
	 * @return
	 */
	public static FontMetrics getFontMetrics2(Font font) {
		Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_ARGB).getGraphics();
		g2.setFont(font);
		return g2.getFontMetrics();
	}

	/**
	 * 计算字符宽度
	 * 
	 * @param font
	 * @param ch
	 * @return
	 */
	public static int getCharWidthWithFont(Font font, char ch) {
		if (font == null) {
			return 0;
		}
		FontMetrics fm = getFontMetrics(font);
		return fm.charWidth(ch);
	}

	/**
	 * 快速计算字符串宽度
	 * 
	 * @param font
	 * @param value
	 * @return
	 */
	public static int getStringWidthQuicklyWithFont(Font font, String value) {
		int[] result = getEachCharWidth(font, value);
		if (result == null) {
			return 0;
		}
		int sumWidth = 0;
		for (int i : result) {
			sumWidth += i;
		}
		return sumWidth;
	}

	/**
	 * 计算每个字符串的宽度，支持中英混合
	 * 
	 * @param font
	 * @param value
	 * @return
	 */
	public static int[] getEachCharWidth(Font font, String value) {
		if (font == null || value == null) {
			return null;
		}
		int length = value.length();
		int[] result = new int[length];
		Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_ARGB).getGraphics();
		g2.setFont(font);
		FontMetrics fm = g2.getFontMetrics();
		for (int i = 0; i < length; i++) {
			char ch = value.charAt(i);
			result[i] = fm.charWidth(ch);
		}
		return result;
	}

	/**
	 * 计算每个字符串的宽度，支持中英混合，适用于等宽字体
	 * 
	 * @param font
	 * @param value
	 * @return
	 */
	public static int[] getEachCharWidth2(Font font, String value) {
		if (font == null || value == null) {
			return null;
		}
		int cnCharWidth = getCharWidthWithFont(font, '米');
		int enCharWidth = getCharWidthWithFont(font, 'M');
		int length = value.length();
		int[] result = new int[length];
		int oneByteCharCodePoint = 0x7F;
		for (int i = 0; i < length; i++) {
			int cp = Character.codePointAt(value, i);
			if (cp > oneByteCharCodePoint) {
				result[i] = cnCharWidth;
			} else {
				result[i] = enCharWidth;
			}
		}
		return result;
	}

	/**
	 * 判断是否等宽字体
	 * 
	 * @param font
	 * @return
	 */
	public static boolean isMonospacedFont(Font font) {
		String mono = "Monospaced";
		String family = "family";
		Map<TextAttribute, ?> attributes = font.getAttributes();
		if (attributes.containsValue(mono)) {
			return true;
		}
		Set<TextAttribute> keySet = attributes.keySet();
		for (TextAttribute key : keySet) {
			Object value = attributes.get(key);
			if (value != null && family.equalsIgnoreCase(key.toString())
					&& mono.equalsIgnoreCase(value.toString())) {
				return true;
			}
		}

		FontMetrics fm = getFontMetrics(font);
		int en1 = fm.charWidth('l');
		int en2 = fm.charWidth('m');
		if (en1 == en2) {
			return true;
		}
		return false;
	}
}
