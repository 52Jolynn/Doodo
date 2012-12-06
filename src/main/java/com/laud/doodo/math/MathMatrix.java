/**  
 * @title BallMathMatrix.java  
 * @package com.badlogicgames.common  
 * @description   
 * @author chenzf             
 * @Date 2011-12-2
 */
package com.laud.doodo.math;

/**
 * 
 * @author Laud
 * 
 */
public class MathMatrix {
	public static float[] GetEquationResult(float[][] array) {
		return Elimination(array);
	}

	private static void SwapRowData(int scrRowId, int targetRowId,
			float[][] array) {
		int aryLength = array[scrRowId].length;
		float temp;
		for (int i = 0; i < aryLength; i++) {
			temp = array[scrRowId][i];
			array[scrRowId][i] = array[targetRowId][i];
			array[targetRowId][i] = temp;
		}
	}

	private static float[] Elimination(float[][] array) {
		if (array == null || array.length < 1) {
			return null;
		}
		int row = array.length;
		int col = array[0].length;
		for (int i = 0; i < row - 1; i++) {
			if (array[0][0] != 0)
				break;
			SwapRowData(0, i + 1, array);
		}

		for (int i = 0; i < row; i++) {
			boolean hasResult = JudeHasResult(i, row, col, array);
			if (!hasResult) {
				break;
			}

			float temp1 = array[i][i];
			for (int j = 0; j < col; j++) {
				array[i][j] = array[i][j] / temp1;
			}

			for (int k = i + 1; k < row; k++) {
				float temp2 = array[k][i];
				for (int j = 0; j < col; j++) {
					array[k][j] = array[k][j] - array[i][j] * temp2;
				}
			}
			for (int k = 0; k < i; k++) {
				float temp2 = array[k][i];
				for (int j = 0; j < col; j++) {
					array[k][j] = array[k][j] - array[i][j] * temp2;
				}
			}
		}

		float[] result = new float[row];
		for (int i = 0; i < row; i++) {
			result[i] = array[i][col - 1];
		}
		return result;
	}

	private static boolean JudeHasResult(int i, int row, int col,
			float[][] array) {
		float temp1 = array[i][i];
		if (i == 0 || temp1 != 0) {
			return true;
		}

		boolean hasResult = false;
		SwapRowData(i, i + 1, array);
		temp1 = array[i][i];
		if (temp1 != 0) {
			hasResult = true;
		}
		return hasResult;
	}
}
