package com.laud.doodo.math;

import com.laud.doodo.exception.UnmatchDimensionException;

/**
 * 一元二次抛物线方程
 * 
 * @author Laud
 * @2012-3-12
 */
public class QuadraticParabolaEquation {
	private float a;
	private float b;
	private float c;

	public QuadraticParabolaEquation() {
	}

	/**
	 * 二次抛物线方程
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public QuadraticParabolaEquation(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * 二次抛物线方程，获取新实例
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public static QuadraticParabolaEquation newInstance(float a, float b,
			float c) {
		return new QuadraticParabolaEquation(a, b, c);
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

	public float getC() {
		return c;
	}

	public void setC(float c) {
		this.c = c;
	}

	public void set(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void set(Point[] points) {
		float[][] matrix = new float[3][4];
		if (points.length != 3) {
			throw new UnmatchDimensionException("参数数组大小必须为3");
		}
		for (int i = 0; i < 3; i++) {
			Point p = points[i];
			float x = p.x;
			float y = p.y;
			matrix[i][0] = x * x;
			matrix[i][1] = x;
			matrix[i][2] = 1;
			matrix[i][3] = y;
		}
		float[] result = MathMatrix.GetEquationResult(matrix);
		if (result != null) {
			set(result[0], result[1], result[2]);
		}
	}

	/**
	 * 获取抛物线顶点坐标
	 * 
	 * @return
	 */
	public Point getVertice() {
		float h = -0.5f * b / a;
		float k = c - 0.25f * b * b / a;
		return new Point(h, k);
	}

	/**
	 * 根据x计算y值
	 * 
	 * @param x
	 * @return
	 */
	public float getY(float x) {
		return a * x * x + b * x + c;
	}

	/**
	 * 根据y获得x
	 * 
	 * @param result
	 *            用于存储结果的数组
	 * @param y
	 * @return 结果
	 */
	public float[] getX(float[] result, float y) {
		double delta = b * b - 4 * a * c;
		if (delta < 0) {
			return null;
		} else {
			float x1 = (-b + (float) Math.sqrt(delta)) * 0.5f / a;
			float x2 = (-b - (float) Math.sqrt(delta)) * 0.5f / a;
			if (result == null) {
				result = new float[2];
			}
			result[0] = x1;
			result[1] = x2;
			return result;
		}
	}

	/**
	 * 根据在抛物线上的点坐标得到抛物线方程
	 * 
	 * @param points
	 * @return
	 */
	public static QuadraticParabolaEquation newInstance(Point[] points) {
		float[][] matrix = new float[3][4];
		if (points.length != 3) {
			throw new UnmatchDimensionException("参数数组大小必须为3");
		}
		for (int i = 0; i < 3; i++) {
			Point p = points[i];
			float x = p.x;
			float y = p.y;
			matrix[i][0] = x * x;
			matrix[i][1] = x;
			matrix[i][2] = 1;
			matrix[i][3] = y;
		}
		float[] result = MathMatrix.GetEquationResult(matrix);
		if (result != null) {
			return new QuadraticParabolaEquation(result[0], result[1],
					result[2]);
		}
		return null;
	}

	@Override
	public String toString() {
		String b = String.format("%+f", this.b);
		String c = String.format("%+f", this.c);
		return "[y=" + a + "*x^2" + b + "*x" + c + "]";
	}
}
