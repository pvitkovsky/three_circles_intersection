package com.pg.three_circles;

import java.util.ArrayList;
import java.util.Random;

enum Type {intersection, inclusion, equals, none};

public class Circle {
	private double x,y,r;

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
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public Circle(double x, double y, double r) {
		super();
		this.x = x;
		this.y = y;
		setR(r);
	}
	
	public void setR(double r) {
		if(r>0) {
			this.r = r;
		}else {
			throw new Error("Radius most by positive!");
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	@Override
	public String toString() {
		return "[(" + String.format("%.3f", x) + "; " + String.format("%.3f", y) + ") r=" + String.format("%.3f", r) + "]";
	}
	
	public static Circle getRandomCircle(){
		Random gen = new Random();
		return new Circle((gen.nextDouble()-.5)*10.,(gen.nextDouble()-.5)*10.,(gen.nextDouble())*5.);
		
	}
	
	//entry point for true/false;
	public static boolean common(Circle c1, Circle c2, Circle c3){
		ArrayList<Point> g;
		Circle c;
		switch (common(c1,c2)) {
		case intersection:
			try {
				g = getPointsOfIntersection(c1, c2);
				System.out.println("intersection c1 and c2 "+g);
				return pointIntoCircle(g,c3);
			} catch (Error e) {
				return false;
			}
		case inclusion:
			// the smaller of the circles c1 and c2
			c = c1.getR()>c2.getR()?c2:c1;
			switch (common(c, c3)) {
			case intersection:
				try {
					g = getPointsOfIntersection(c, c3);
					System.out.println("intersection c3 "+g);
					return g.size()>0 ? true : false;
				} catch (Error e) {
					return false;
				}
			case inclusion:
				return true;
				
			default:
				return false;
			}
		case equals:
			switch (common(c1, c3)) {
			case intersection:
				try {
					g = getPointsOfIntersection(c1, c3);
					System.out.println("intersection c3 "+g);
					return g.size()>0 ? true : false;
				} catch (Error e) {
					return false;
				}
			case inclusion:
				return true; 
				
			default:
				return false;
			}
		case none:
			return false;
			
		default:
			return false;
		}
	}
	
	// entry point for common point;
	public static CommonPoint commonp(Circle c1, Circle c2, Circle c3){
		ArrayList<Point> g;
		Circle c;
		switch (common(c1,c2)) {
		case intersection:
			try {
				g = getPointsOfIntersection(c1, c2);
				System.out.println("intersection c1 and c2 "+g);
				return pointIntoCircle(g,c3) ? new CommonPoint(g) :  new CommonPoint();
			} catch (Error e) {
				return new CommonPoint();
			}
		case inclusion:
			// the smaller of the circles c1 and c2 //why the smaller?
			c = c1.getR()>c2.getR()?c2:c1;
			switch (common(c, c3)) {
			case intersection:
				try {
					g = getPointsOfIntersection(c, c3);
					System.out.println("intersection c3 "+g);
					return g.size()>0 ? new CommonPoint(g) :  new CommonPoint();
				} catch (Error e) {
					return new CommonPoint();
				}
			case inclusion:

				return new CommonPoint(); //some points in c3 are in c1; 
				// test ab = 2; r1 = 2.5; r2 = 1
				
			default:
				return new CommonPoint();
			}
		case equals:
			switch (common(c1, c3)) {
			case intersection:
				try {
					g = getPointsOfIntersection(c1, c3);
					System.out.println("intersection c3 "+g);
					return g.size()>0 ? new CommonPoint(g) : new CommonPoint();
				} catch (Error e) {
					return new CommonPoint();
				}
			case inclusion:
				return new CommonPoint(); //some points in c3 are in c1; 
				// test ab = 2; r1 = 2.5; r2 = 1
			default:
				return new CommonPoint();
			}
		case none:
			return new CommonPoint();
			
		default:
			return new CommonPoint();
		}
	}
	
	private static boolean pointIntoCircle(ArrayList<Point> g, Circle c3) {
		double distance;
		double r = c3.getR();
		for (Point point : g) {
			distance = Math.sqrt(Math.pow((point.getX()-c3.getX()), 2)+Math.pow((point.getY()-c3.getY()), 2));
			if (distance<r) {
				return true;
			}
		}
		return false;
	}

	private static Type common(Circle c1, Circle c2) {
		//return Type: intersection or inclusion
		double r1 = c1.getR();
		double r2 = c2.getR();
		double ab = Math.sqrt(Math.pow((c1.getX()-c2.getX()),2)+Math.pow((c1.getY()-c2.getY()),2)); //ab is a distance between centres
		if (c1.equals(c2)) {
			return Type.equals;
		}
		if ((r1+r2)>=ab) {
			return Type.intersection;
		}
		if (ab<Math.abs(r1-r2)) {
			return Type.inclusion;
		}
		
		return Type.none;
	}
	
	private static ArrayList<Point> getPointsOfIntersection(Circle c1, Circle c2) throws Error {
		ArrayList<Point> res = new ArrayList<>();
		double x, y;
		//�?аходим уравнени�? окружно�?тей и приравниваем их друг другу, решаем уравнение, получаем точку (или две) �? координатами.
		// Уравнение первой окружно�?ти: (x-c1.getX())pow2+(y-c1.getY())pow2=c1.getR()*c1.getR()
		// второе - по аналогии
		double r1pow2 = c1.getR()*c1.getR();
		double r2pow2 = c2.getR()*c2.getR();
		// (X - c1.getX())pow2 + (Y - c1.getY())pow2 - r1pow2 = (X - c2.getX())pow2 + (Y - c2.getY())pow2 -r2pow2
		
		double x1pow2 = c1.getX()*c1.getX();
		double x2pow2 = c2.getX()*c2.getX();
		double y1pow2 = c1.getY()*c1.getY();
		double y2pow2 = c2.getY()*c2.getY();
		// квадраты ик�?ов (x) и игреков (y) при приравнивании уравнений �?окраща�?т�?�?, по�?тому �? их не учитыва�?.
		// -2*X*c1.getX() + x1pow2 - 2*Y*c1.getY() + y1pow2 - r1pow2 + 2*X*c2.getX() - x2pow2 + 2*Y*c2.getY() - y2pow2 + r2pow2 = 0  
		double constant = x1pow2+y1pow2-r1pow2-x2pow2-y2pow2+r2pow2;
		// 2*X*(c2.getX() - c1.getX()) + 2*Y*(c2.getY() - c1.getY()) + constant = 0
		double dX = c2.getX() - c1.getX();
		double dY = c2.getY() - c1.getY();
		//	итоговое: 2*X*dX + 2*Y*dY + constant = 0
		//	выражаем:
		//	2*X*dX = -(2*Y*dY + constant)
		//	X = -(2*Y*dY + constant)/2*dX;
		// 	под�?тавл�?ем в уравнение окружно�?ти и решаем:
		//	Xpow2 + Ypow2 - 2*X*c1.getX() + x1pow2 - 2*Y*c1.getY() + y1pow2 - r1pow2 = 0
		double constant1 = x1pow2+y1pow2-r1pow2;
		double a = 1-Math.pow(dY,2)/Math.pow(dX,2);
		double b = 2*c1.getX()*dY/dX-2*dY*constant/(3*dX)-2*c1.getY();
		double c = Math.pow(constant/(4*dX), 2)+c1.getX()*constant/dX+constant1;
		double d = Math.pow(b, 2)-4*a*c;
		if(a==0) {
			//е�?ли ко�?ффициент при Y = 0, то решаем линейное уравнение.
			y = -c/b;
			x = -(2*y*dY + constant)/(2*dX);
			res.add(new Point(x,y));
		} else {
			if (d<0) {
				// �?ет решений
				throw new Error("No intersection point");
			}
			if (d==0) {
				y = -b/2*a;
				x = -(2*y*dY + constant)/(2*dX);
				res.add(new Point(x,y));
			}
			if (d>0) {
				y = (-b+Math.sqrt(d))/(2*a);
				x = -(2*y*dY + constant)/(2*dX);
				res.add(new Point(x,y));
				y = (-b-Math.sqrt(d))/(2*a);
				x = -(2*y*dY + constant)/(2*dX);
				res.add(new Point(x,y));
			}
		}
		return res;
	}
}
