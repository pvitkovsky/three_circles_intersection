package com.pg.three_circles;

public class Point {
	private double x;
	private double y;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Point (" + String.format("%.3f", x) + "; " + String.format("%.3f", y) + ")";
	}
	
}
