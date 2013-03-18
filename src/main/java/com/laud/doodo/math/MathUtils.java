package com.laud.doodo.math;

import java.util.Random;

/**
 * 数学工具类
 * 
 * @author Laud
 * @date 2012-4-25
 */
public class MathUtils {
	private final static Random random = new Random();

	/**
	 * 计算两点距离
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double distanceBetweenTwoPoints(float x1, float y1, float x2,
			float y2) {
		return Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2)
				+ Math.abs(y1 - y2) * Math.abs(y1 - y2));
	}

	/**
	 * 返回包含min，不包含max的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNumber(int min, int max) {
		int num = random.nextInt(max);
		int mod = num % (max - min);
		return mod + min;
	}
}
