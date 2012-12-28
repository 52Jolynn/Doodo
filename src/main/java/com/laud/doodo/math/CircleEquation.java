package com.laud.doodo.math;

/**
 * 圆方程
 * 
 * @author Laud
 * @date 2012-4-14
 */
public class CircleEquation {
	private float a;
	private float b;
	private float r;

	public CircleEquation() {
	}

	/**
	 * 返回新实例
	 * 
	 * @param a
	 *            圆心横坐标
	 * @param b
	 *            圆心纵坐标
	 * @param r
	 *            半径
	 */
	public static CircleEquation newInstance(float a, float b, float r) {
		return new CircleEquation(a, b, r);
	}

	/**
	 * 
	 * @param a
	 *            圆心横坐标
	 * @param b
	 *            圆心纵坐标
	 * @param r
	 *            半径
	 */
	public CircleEquation(float a, float b, float r) {
		this.a = a;
		this.b = b;
		this.r = r;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public void set(float a, float b, float r) {
		this.a = a;
		this.b = b;
		this.r = r;
	}

	/**
	 * 圆的参数方程
	 * <p>
	 * <code>
	 * x = rcost+a 
	 * <p>
	 * y = rsint+b
	 * </code>
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param t
	 *            弧度
	 * @return 结果
	 */
	public double[] getXY(double[] result, double t) {
		double x = (r * Math.cos(t) + a);
		double y = (r * Math.sin(t) + b);
		if (result == null) {
			result = new double[2];
		}
		result[0] = x;
		result[1] = y;
		return result;
	}

	/**
	 * 根据x获得弧度
	 * 
	 * @param x
	 * @return
	 */
	public double getT(double x) {
		return Math.acos((x - a) / r);
	}

	/**
	 * 根据x值获得y
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param x
	 * @return 结果
	 */
	public double[] getY(double[] result, double x) {
		double v = Math.sqrt((r + x - a) * (r - x + a));
		if (result == null) {
			result = new double[2];
		}
		result[0] = v + b;
		result[1] = v - b;
		return result;
	}

	@Override
	public String toString() {
		String a = String.format("%+f", this.a);
		String b = String.format("%+f", this.b);
		return "[(x" + a + ")^2" + "(y" + b + ")^2=" + r * r + "]";
	}
}
