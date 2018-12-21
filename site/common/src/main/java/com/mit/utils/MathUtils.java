package com.mit.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	
	public static double userSpeed = 1; //m/s

	public static BigDecimal roundCurrencyAmount(BigDecimal amount) {
		return round(amount, 2);
	}

	public static BigDecimal round(BigDecimal amount, int decimals) {
		return amount.setScale(decimals, RoundingMode.HALF_EVEN);
	}
	
	public static double round(double amount, int decimals) {
		return round(new BigDecimal(amount), decimals).doubleValue();
	}
	
	public static double distance(double lon1, double lat1, double lon2, double lat2) {
		double R = 6371000;
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		double dtLat = lat1 - lat2;
		double dtLon = lon1 - lon2;
		double a = Math.pow(Math.sin(dtLat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dtLon/2), 2);
		double c = 2 * Math.asin(Math.pow(a, 0.5));
		double dis = R * c;
		
		return dis;		
	}

	public static long toPrimitive(Long val) {
		return val != null ? val : 0L;
	}

	public static int toPrimitive(Integer val) {
		return val != null ? val : 0;
	}

	public static String ordinalSuffixOf(int i) {
		int j = i % 10,
				k = i % 100;
		if (j == 1 && k != 11) {
			return i + "st";
		}
		if (j == 2 && k != 12) {
			return i + "nd";
		}
		if (j == 3 && k != 13) {
			return i + "rd";
		}
		return i + "th";
	}
	
	public static double[] transform2D(double radian, double scale, double[] point, double[] vector) {
		//translate -> O: T1
		//scale, rotate: S, R
		//translate -> point: T2
		//P' = T2 * S * R * T1 * P
		return matMultiVec(getMatrix2D(radian, scale, point), vector);
	}
	
	public static double[][] getMatrix2D(double radian, double scale, double[] point) {
		double cos = Math.cos(radian), sin = Math.sin(radian);
		double x = point[0], y = point[1];
		double[][] M = {{cos*scale, -sin*scale, x - x*cos*scale + y*sin*scale}, {sin*scale, cos*scale, y - x*sin*scale - y*cos*scale}, {0, 0, 1}};
		
//		double[] test = {2,2};
//		double[][] T2 = {{1, 0, x}, {0, 1, y}, {0, 0, 1}};
//		double[][] SR = {{cos*scale, -sin*scale, 0}, {sin*scale, cos*scale, 0}, {0, 0, 1}};
//		double[][] T1 = {{1, 0, -x}, {0, 1, -y}, {0, 0, 1}};
//		System.out.println(JsonUtils.Instance.toJson(matMultiVec(matMultiMat(T2, matMultiMat(SR, T1)), test)));
//		System.out.println(JsonUtils.Instance.toJson(matMultiVec(M, test)));
		return M;
	}
	
	public static void main(String[] args) {
		getMatrix2D(Math.PI/3, 1, new double[]{1, 1});
	}
	
	public static double[] rotate2D(double radian, double[] point) {
		double[][] matrix = {{Math.cos(radian), -Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};
		double[] pointNor = {point[0], point[1], 1};
		double[] rotateResult = matMultiVec(matrix, pointNor);
		return new double[]{rotateResult[0], rotateResult[1]};
	}
	
	public static double[] translate2D(double[] vector, double[] point) {
		double[][] matrix = {{1, 0, vector[0]}, {0, 1, vector[1]}, {0, 0, 1}};
		double[] pointNor = {point[0], point[1], 1};
		double[] translateResult = matMultiVec(matrix, pointNor);
		return new double[]{translateResult[0], translateResult[1]};
	}
	
	public static double[] scale2D(double scale, double[] point) {
		double[][] matrix = {{scale, 0, 0}, {0, scale, 0}, {0, 0, 1}};
		double[] pointNor = {point[0], point[1], 1};
		double[] scaleResult = matMultiVec(matrix, pointNor);
		return new double[]{scaleResult[0], scaleResult[1]};
	}
	
	public static double[] matMultiVec(double[][] matrix, double[] vector) {
		double[] rs = new double[matrix.length];
		for (int i = 0; i < matrix.length; i ++) {
			rs[i] = 0;
			for (int j = 0; j < vector.length; j++) {
				rs[i] += matrix[i][j] * vector[j];
			}
		}
		return rs;
	}
	
	public static double[][] matMultiMat(double[][] matrix1, double[][] matrix2) {
		double[][] matrix = new double[matrix1.length][matrix2[0].length];
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {
				matrix[i][j] = 0;
				for (int k = 0; k < matrix1[0].length; k++) {
					matrix[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		return matrix;
	}
}
