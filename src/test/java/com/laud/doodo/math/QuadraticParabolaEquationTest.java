package com.laud.doodo.math;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-11 下午3:12:09
 * @copyright: www.armisi.com.cn
 */
public class QuadraticParabolaEquationTest extends TestCase {
	public void testQuardraticParabolaEquation() {
		Point[] points = new Point[] { new Point(3, 0), new Point(-3, 0),
				new Point(0, 6) };
		QuadraticParabolaEquation equation = QuadraticParabolaEquation
				.newInstance(points);
		assertEquals(6, equation.getY(0), 0);
	}
}
