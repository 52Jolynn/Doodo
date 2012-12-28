package com.laud.doodo.math;

/**
 * 双曲线方程
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-12-12 上午10:21:22
 * @copyright: www.dreamoriole.com
 */
public class HyperbolicEquation {
	private float a;
	private float b;
	private float h;
	private float k;

	/**
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
	public HyperbolicEquation(float a, float b, float h, float k) {
		this.a = a;
		this.b = b;
		this.h = h;
		this.k = k;
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
	 * 计算离心率
	 * 
	 * @return
	 */
	public double getEccentricity() {
		return Math.sqrt(1 + b * b / a * a);
	}

	/**
	 * 双曲线参数方程
	 * <p>
	 * <code>
	 * x = h + asec t
	 * y = k + btan t 
	 * </code> 给定角度(-pi<t<pi)，获得x,y
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param t
	 *            弧度
	 * @return 结果
	 */
	public double[] getXY(double[] result, double t) {
		double tant = Math.tan(t);
		double sect = tant / Math.sin(t);
		double x = (h + a * sect);
		double y = (k + b * tant);
		if (result == null) {
			result = new double[2];
		}
		result[0] = x;
		result[1] = y;
		return result;
	}
}
