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
	public static int getStringWidthQuickly(Font font, String value) {
		return getStringWidthQuickly(getFontMetrics(font), value);
	}

	/**
	 * 快速计算字符串宽度
	 * 
	 * @param fm
	 * @param value
	 * @return
	 */
	public static int getStringWidthQuickly(FontMetrics fm, String value) {
		int[] result = getEachCharWidth(fm, value);
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
	 * 计算每个字符的宽度，支持中英混合
	 * 
	 * @param font
	 * @param value
	 * @return
	 */
	public static int[] getEachCharWidth(Font font, String value) {
		if (font == null || value == null) {
			return null;
		}
		FontMetrics fm = getFontMetrics(font);
		return getEachCharWidth(fm, value);
	}

	/**
	 * 计算每个字符的宽度，支持中英混合
	 * 
	 * @param fm
	 * @param value
	 * @return
	 */
	public static int[] getEachCharWidth(FontMetrics fm, String value) {
		if (fm == null || value == null) {
			return null;
		}
		if (isMonospaced(fm)) {
			return getEachMonoCharWidth(fm, value);
		} else {
			int length = value.length();
			int[] result = new int[length];
			for (int i = 0; i < length; i++) {
				char ch = value.charAt(i);
				result[i] = fm.charWidth(ch);
			}
			return result;
		}
	}

	/**
	 * 计算每个字符的宽度，支持中英混合，适用于等宽字体
	 * 
	 * @param fm
	 * @param value
	 * @return
	 */
	private static int[] getEachMonoCharWidth(FontMetrics fm, String value) {
		if (fm == null || value == null) {
			return null;
		}
		int cnCharWidth = fm.charWidth('米');
		int enCharWidth = fm.charWidth('M');
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
		FontMetrics fm = getFontMetrics(font);
		return isMonospaced(fm);
	}

	/**
	 * 判断是否等宽字体
	 * 
	 * @param fm
	 * @return
	 */
	public static boolean isMonospaced(FontMetrics fm) {
		Font font = fm.getFont();
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

		int en1 = fm.charWidth('l');
		int en2 = fm.charWidth('m');
		if (en1 == en2) {
			return true;
		}
		return false;
	}
}
