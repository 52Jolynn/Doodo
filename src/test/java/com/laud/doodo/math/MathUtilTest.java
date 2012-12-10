package com.laud.doodo.math;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-11 下午2:11:02
 * @copyright: www.dreamoriole.com
 */
public class MathUtilTest extends TestCase {
	public void testDistanceBetweenTwoPoints() {
		double distance = MathUtils.distanceBetweenTwoPoints(0, 0, 3, 3);
		assertEquals(Math.sqrt(18), distance, 0);
		
		distance = MathUtils.distanceBetweenTwoPoints(0, 0, 0, 0);
		assertEquals(0, distance, 0);
		
		distance = MathUtils.distanceBetweenTwoPoints(1, 1, 3, 3);
		assertEquals(Math.sqrt(8), distance, 0);
		
		distance = MathUtils.distanceBetweenTwoPoints(-1, -1, 3, 3);
		assertEquals(Math.sqrt(32), distance, 0);
	}

	public void testRandomNumber() {
		int number = MathUtils.getRandomNumber(0, 10);
		assertTrue(number >= 0 && number < 10);
		
		number = MathUtils.getRandomNumber(0, 1);
		assertEquals(0, number);
		
		number = MathUtils.getRandomNumber(10, 11);
		assertEquals(10, number);
	}
}
