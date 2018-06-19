package com.pg.three_circles;

public class Line {
	private final Point pFirst;
	private final Point pSecond;
	public Line(Point pFirst, Point pSecond) {
		this.pFirst = pFirst;
		this.pSecond = pSecond;
	}
	public Point getpFirst() {
		return pFirst;
	}
	public Point getpSecond() {
		return pSecond;
	}
	public Point getpMid() {
		return pSecond;
	}
	 
}
