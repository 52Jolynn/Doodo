package com.laud.doodo.math;

/**
 * 椭园方程
 * 
 * @author Laud
 * 
 */
public class EllipseEquation {
	private float a;
	private float b;
	private float h;
	private float k;

	public EllipseEquation() {
	}

	/**
	 * 椭园方程
	 * 
	 * @param a
	 *            长/短轴
	 * @param b
	 *            长/短轴
	 * @param h
	 *            x轴偏移量
	 * @param k
	 *            y轴偏移量
	 */
	public EllipseEquation(float a, float b, float h, float k) {
		this.a = a;
		this.b = b;
		this.h = h;
		this.k = k;
	}

	/**
	 * 椭园方程，获取新实例
	 * 
	 * @param a
	 *            长/短轴
	 * @param b
	 *            长/短轴
	 * @param h
	 *            x轴偏移量
	 * @param k
	 *            y轴偏移量
	 */
	public static EllipseEquation newInstance(float a, float b, float h, float k) {
		return new EllipseEquation(a, b, h, k);
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

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getK() {
		return k;
	}

	public void setK(float k) {
		this.k = k;
	}

	/**
	 * 设置值
	 * 
	 * @param a
	 * @param b
	 * @param h
	 * @param k
	 */
	public void set(float a, float b, float h, float k) {
		this.a = a;
		this.b = b;
		this.h = h;
		this.k = k;
	}

	/**
	 * 计算离心率
	 * 
	 * @return
	 */
	public double getEccentricity() {
		return Math.sqrt(1 - b * b / a * a);
	}

	/**
	 * 椭圆参数方程
	 * <p>
	 * <code>
	 * x = h + acost
	 * y = k + bsint
	 * </code> 给定角度(-pi<t<pi)，获得x,y
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param t
	 *            弧度
	 * @return 结果
	 */
	public double[] getXY(double[] result, double t) {
		double x = (h + a * Math.cos(t));
		double y = (k + b * Math.sin(t));
		if (result == null) {
			result = new double[2];
		}
		result[0] = x;
		result[1] = y;
		return result;
	}

	/**
	 * 根据x得到y
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param x
	 * @return 结果
	 */
	public double[] getY(double[] result, double x) {
		double m = (x - h) / a;
		double q = (1 - m * m) * b * b;
		if (result == null) {
			result = new double[2];
		}
		result[0] = Math.sqrt(q) + k;
		result[1] = k - Math.sqrt(q);
		return result;
	}

	@Override
	public String toString() {
		String h = String.format("%+f", this.h);
		String k = String.format("%+f", this.k);
		return "[(x" + h + ")^2/" + this.a + "+(y" + k + ")^2/" + this.b + "=1"
				+ "]";
	}
}
