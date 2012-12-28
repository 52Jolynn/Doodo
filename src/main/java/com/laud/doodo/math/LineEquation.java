package com.laud.doodo.math;

import com.laud.doodo.exception.InvalidValueException;

/**
 * 直线方程
 * <p>
 * <code>
 * 两点式：
 * x/a-y/b=1
 * 点斜式：
 * y = kx+b
 * 一般式：
 * Ax+By+C=0
 * 参数式：
 * x = x0+tcosa
 * y = y0+tsina
 * </code>
 * 
 * @author Laud
 * @date 2012-4-11
 */
public class LineEquation {
	// 点斜式
	private float k;
	private float b;

	// 参数式
	private float x0;
	private float y0;
	private double radian;

	// 是否是平行于y轴的直线方程
	private boolean isVertical = false;
	// 是否平行于x轴的直线方程
	private boolean isHorizontal = false;
	private float value;

	public LineEquation() {
	}

	/**
	 * 两点式，获得新实例
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static LineEquation newInstance(float x1, float y1, float x2,
			float y2) throws InvalidValueException {
		return new LineEquation(x1, y1, x2, y2);
	}

	/**
	 * 两点式
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public LineEquation(float x1, float y1, float x2, float y2)
			throws InvalidValueException {
		compute(x1, y1, x2, y2);
	}

	/**
	 * 两点式
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void compute(float x1, float y1, float x2, float y2)
			throws InvalidValueException {
		if (x2 == x1 && y2 == y1) {
			// throw new InvalidValueException("必须是两个不同的点。");
			// 没必要抛出异常
			return;
		}
		if (x2 == x1) {
			this.radian = Math.PI / 2;
			this.isVertical = true;
			this.isHorizontal = false;
			this.value = x1;
			this.k = Float.POSITIVE_INFINITY;
			this.radian = Math.PI / 2;
		} else if (y2 == y1) {
			this.radian = 0;
			this.isHorizontal = true;
			this.isVertical = false;
			this.value = y1;
			this.k = 0;
			this.radian = 0;
		} else {
			this.k = (y2 - y1) / (x2 - x1);
			this.b = y1 - this.k * x1;
			this.x0 = x1;
			this.y0 = y1;
			this.radian = Math.atan(this.k);
			this.isHorizontal = false;
			this.isVertical = false;
		}
	}

	/**
	 * 点斜式
	 * 
	 * @param k
	 *            斜率
	 * @param b
	 * @return
	 */
	public LineEquation(float k, float b) {
		compute(k, b);
	}

	/**
	 * 点斜式，获得新实例
	 * 
	 * @param k
	 * @param b
	 * @return
	 */
	public static LineEquation newInstance(float k, float b) {
		return new LineEquation(k, b);
	}

	/**
	 * 点斜式
	 * 
	 * @param k
	 *            斜率
	 * @param b
	 * @return
	 */
	private void compute(float k, float b) {
		this.k = k;
		this.b = b;
		if (k == 0) {
			this.radian = 0;
			this.isHorizontal = true;
			this.isVertical = false;
			this.value = b;
			this.radian = 0;
		} else {
			this.radian = Math.atan(this.k);
			this.x0 = 0;
			this.y0 = b;
			this.isHorizontal = false;
			this.isVertical = false;
		}
	}

	/**
	 * 一般式
	 * 
	 * @param A
	 * @param B
	 * @param C
	 */
	public LineEquation(float A, float B, float C) throws InvalidValueException {
		compute(A, B, C);
	}

	/**
	 * 一般式，获得新实例
	 * 
	 * @param A
	 * @param B
	 * @param C
	 * @return
	 * @throws InvalidValueException
	 */
	public static LineEquation newInstance(float A, float B, float C)
			throws InvalidValueException {
		return new LineEquation(A, B, C);
	}

	/**
	 * 一般式
	 * 
	 * @param A
	 * @param B
	 * @param C
	 */
	private void compute(float A, float B, float C)
			throws InvalidValueException {
		if (A == 0 && B == 0) {
			throw new InvalidValueException("A、B不能同时为0");
		}
		if (B == 0) {
			this.radian = Math.PI / 2;
			this.isVertical = true;
			this.isHorizontal = false;
			this.value = -C / A;
			this.k = Float.POSITIVE_INFINITY;
			this.radian = Math.PI / 2;
		} else if (A == 0) {
			this.radian = 0;
			this.isHorizontal = true;
			this.isVertical = false;
			this.value = -B / A;
			this.k = 0;
			this.radian = 0;
		} else {
			this.k = -A / B;
			this.b = -C / B;
			this.x0 = 0;
			this.y0 = this.b;
			this.radian = Math.atan(this.k);
			this.isHorizontal = false;
			this.isVertical = false;
		}
	}

	/**
	 * 参数式
	 * 
	 * @param x0
	 * @param y0
	 * @param radian
	 *            弧度
	 */
	public LineEquation(float x0, float y0, double radian) {
		compute(x0, y0, radian);
	}

	/**
	 * 参数式，获得新实例
	 * 
	 * @param x0
	 * @param y0
	 * @param radian
	 * @return
	 */
	public static LineEquation newInstance(float x0, float y0, double radian) {
		return new LineEquation(x0, y0, radian);
	}

	/**
	 * 参数式
	 * 
	 * @param x0
	 * @param y0
	 * @param radian
	 *            弧度
	 */
	private void compute(float x0, float y0, double radian) {
		this.x0 = x0;
		this.y0 = y0;
		this.radian = radian;
		if (Math.sin(radian) == 0) {
			this.isHorizontal = true;
			this.isVertical = false;
			this.value = this.y0;
			this.k = 0;
		} else if (Math.sin(this.radian) == 1) {
			this.isVertical = true;
			this.isHorizontal = false;
			this.value = this.x0;
			this.k = Float.POSITIVE_INFINITY;
		} else {
			this.k = (float) Math.tan(this.radian);
			this.b = y0 - this.k * x0;
			this.isHorizontal = false;
			this.isVertical = false;
		}
	}

	/**
	 * 两点式，重新设置值，
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void set(float x1, float y1, float x2, float y2)
			throws InvalidValueException {
		compute(x1, y1, x2, y2);
	}

	/**
	 * 点斜式，重新设置值
	 * 
	 * @param k
	 * @param b
	 */
	public void set(float k, float b) {
		compute(k, b);
	}

	/**
	 * 一般式，重新设置值
	 * 
	 * @param A
	 * @param B
	 * @param C
	 */
	public void set(float A, float B, float C) throws InvalidValueException {
		compute(A, B, C);
	}

	/**
	 * 参数式，重新设置值
	 * 
	 * @param x0
	 * @param y0
	 * @param radian
	 */
	public void set(float x0, float y0, double radian) {
		compute(x0, y0, radian);
	}

	/**
	 * 斜率
	 * 
	 * @return
	 */
	public float getK() {
		return this.k;
	}

	/**
	 * 根据x获取y值
	 * 
	 * @param x
	 * @return
	 */
	public float getY(float x) {
		if (isVertical) {
			return Float.NaN;
		} else if (isHorizontal) {
			return value;
		} else {
			return this.k * x + b;
		}
	}

	/**
	 * 根据y获得x值
	 * 
	 * @param y
	 * @return
	 */
	public float getX(float y) {
		if (isVertical) {
			return value;
		} else if (isHorizontal) {
			return Float.NaN;
		} else {
			return (y - this.b) / this.k;
		}
	}

	/**
	 * 根据参数方程获取x,y值
	 * 
	 * @param t
	 * @return
	 */
	public float[] getXY(float t) {
		return new float[] { (float) (this.x0 + Math.cos(this.radian) * t),
				(float) (this.y0 + Math.sin(this.radian) * t) };
	}

	/**
	 * 是否是垂直的直线
	 * 
	 * @return
	 */
	public boolean isVertical() {
		return isVertical;
	}

	/**
	 * 是否是水平的直线
	 * 
	 * @return
	 */
	public boolean isHorizontal() {
		return isHorizontal;
	}

	@Override
	public String toString() {
		if (isVertical) {
			return "[x=" + value + "]";
		} else if (isHorizontal) {
			return "[y=" + value + "]";
		} else {
			String b = String.format("%+f", this.b);
			return "[y=" + this.k + "*x" + b + "]";
		}
	}
}
