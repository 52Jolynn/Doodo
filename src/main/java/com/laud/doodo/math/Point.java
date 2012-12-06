package com.laud.doodo.math;

/**
 * 点
 * 
 * @author Laud
 * @date 2012-3-12
 */
public class Point {
	public float x;
	public float y;

	public Point() {
	}

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[x=" + x + ",y=" + y + "]";
	}

	/**
	 * 增加比较
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {// 非空性
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point p = (Point) obj;
		return this.x == p.x && this.y == p.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + Float.floatToIntBits(x);
		result = result * prime + Float.floatToIntBits(y);
		return result;
	}
}
