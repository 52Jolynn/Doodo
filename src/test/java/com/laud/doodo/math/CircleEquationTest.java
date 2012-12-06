package com.laud.doodo.math;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-11 下午3:05:15
 * @copyright: www.armisi.com.cn
 */
public class CircleEquationTest extends TestCase {
	public void testCircleEquation() {
		CircleEquation equation = CircleEquation.newInstance(0, 0, 5);
		assertEquals(Math.acos(3.0f / 5.0f), equation.getT(3), 0.01f);
	}
}
