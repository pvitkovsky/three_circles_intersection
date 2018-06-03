package com.pg.three_circles;

import java.util.Random;

public class Circle extends Point {

	final private static double TOLERANCE = 0.01;
	private double r;

	public Point getCentre(){
		return new Point(this.getX(), this.getY());
	}
	
	private void setR(double r) {
		if(r>0) {
			this.r = r;
		}else {
			throw new Error("Radius most by positive!");
		}
	}

	public double getR() {
		return r;
	}

	@Override
	public String toString() {
		return "[(" + String.format("%.3f", getX()) + "; " + String.format("%.3f", getY()) + ") r=" + String.format("%.3f", r) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		if (Double.doubleToLongBits(this.getX()) != Double.doubleToLongBits(other.getX()))
			return false;
		if (Double.doubleToLongBits(this.getY()) != Double.doubleToLongBits(other.getY()))
			return false;
		return true;
	}
	
	public Circle(double x, double y, double r) {
		super(x, y);
		setR(r);
	}
	
	public boolean containsPoint(Point point) {
		double distance = Math.sqrt(Math.pow((point.getX()- getX()), 2)+Math.pow((point.getY()-getY()), 2));
		if (distance  < r + TOLERANCE) {
			return true;
		}
		return false;
	}
	
	public boolean sameCircle(Circle other) {
		return (intersectionType(this, other) == IntersectionType.equals);
	}
	

	public boolean containsCircle(Circle other) {
		return (intersectionType(other, this) == IntersectionType.inclusion);
	}
	
	public boolean isContainedIn(Circle other) {
		return (intersectionType(this, other) == IntersectionType.inclusion);
	}
	
	public static IntersectionType intersectionType(Circle c1, Circle c2) {
		double r1 = c1.getR();
		double r2 = c2.getR();
		double ab = Math.sqrt(Math.pow((c1.getX()-c2.getX()),2)+Math.pow((c1.getY()-c2.getY()),2)); //ab is a distance between centres
		if (c1.equals(c2)) {
			return IntersectionType.equals;
		}
		if ((r1+r2)>ab) {
			return IntersectionType.intersection;
		}
		if ((r1+r2)==ab) {
			return IntersectionType.contact;
		}
		if (ab<Math.abs(r1-r2)) {
			return IntersectionType.inclusion;
		}
		
		return IntersectionType.none;
			
	}
	
	public static Circle getRandomCircle(){
		Random gen = new Random();
		return new Circle((gen.nextDouble()-.5)*10.,(gen.nextDouble()-.5)*10.,(gen.nextDouble())*5.);
	}

}
