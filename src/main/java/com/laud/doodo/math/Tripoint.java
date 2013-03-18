package com.laud.doodo.math;

/**
 * 三维
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-12-12 上午10:24:07
 * @copyright: www.dreamoriole.com
 */
public class Tripoint {
	public float x;
	public float y;
	public float z;

	public Tripoint() {
	}

	/**
	 * 构造函数
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param z
	 *            z坐标
	 */
	public Tripoint(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tripoint point = (Tripoint) obj;
		return x == point.x && y == point.y && z == point.z;
	}
}
