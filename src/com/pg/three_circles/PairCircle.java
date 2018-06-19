package com.pg.three_circles;

import java.util.ArrayList;

public class PairCircle extends Line {	
	final private static double TOLERANCE = 0.01;
	private final Circle firstC;
	private final Circle secondC;
	
	public void printPair() {
		System.out.println("  Pair of circles");
		System.out.println("	"+firstC.getX()+" "+firstC.getY()+" "+firstC.getR());
		System.out.println("	"+secondC.getX()+" "+secondC.getY()+" "+secondC.getR());
	}
	
	public PairCircle(Circle firstC, Circle secondC) {
		super(firstC.getCentre(), secondC.getCentre());
		this.firstC = firstC;
		this.secondC = secondC;
	}
	
	public Point contactPoint() throws Error {
		final Circle c1 = firstC;
		final Circle c2 = secondC;
		double r1 = c1.getR();
		double r2 = c2.getR();
		double ab = Math.sqrt(Math.pow((c1.getX()-c2.getX()),2)+Math.pow((c1.getY()-c2.getY()),2));
		if ((r1+r2) - ab > TOLERANCE) throw new Error("Failure getting contactPoint inapplicable Pair ");
		return this.getPointsOfIntersectionII().get(0); // stub
	}
	
	public ArrayList<Point> intersectionPoints() {
		return this.getPointsOfIntersectionII(); // stub
	}
	
	public IntersectionType intersectionType() {
		final Circle c1 = firstC;
		final Circle c2 = secondC;
		return Circle.intersectionType(c1, c2);
	}
	
	private ArrayList<Point> getPointsOfIntersectionII(){//https://stackoverflow.com/a/42803692
		final Circle c0 = firstC;
		final Circle c1 = secondC;
		ArrayList<Point> res = new ArrayList<>();
		double x0, x1, y0, y1;
		double x2, y2, x3, y3;
		double r0, r1;
		double d, a, h;
		x0 = c0.getX();
		x1 = c1.getX();
		y0 = c0.getY();
		y1 = c1.getY();
		r0 = c0.getR();
		r1 = c1.getR();
		d = Math.sqrt((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0));
		a = (r0*r0 - r1*r1 + d*d)/(d*2);
		h = Math.sqrt(r0*r0-a*a);
		x2=x0+a*(x1-x0)/d;  
		y2=y0+a*(y1-y0)/d; 
		x3=x2+h*(y1-y0)/d;    
		y3=y2-h*(x1-x0)/d;
		res.add(new Point(x2,y2));
		res.add(new Point(x3,y3));
		return res;
	}
	
	private ArrayList<Point> getPointsOfIntersection() throws Error {
		final Circle c1 = firstC;
		final Circle c2 = secondC;
		ArrayList<Point> res = new ArrayList<>();
		double x, y;
		//находим уравнение окружностей и приравниваем их друг другу, решаем уравнение, получаем точку (или две) с координатами.
		// Уравнение первой окружности: (x-c1.getX())pow2+(y-c1.getY())pow2=c1.getR()*c1.getR()
		// второе - по аналогии
		double r1pow2 = c1.getR()*c1.getR();
		double r2pow2 = c2.getR()*c2.getR();
		// (X - c1.getX())pow2 + (Y - c1.getY())pow2 - r1pow2 = (X - c2.getX())pow2 + (Y - c2.getY())pow2 -r2pow2
		double x1pow2 = c1.getX()*c1.getX();
		double x2pow2 = c2.getX()*c2.getX();
		double y1pow2 = c1.getY()*c1.getY();
		double y2pow2 = c2.getY()*c2.getY();
		// квадраты иксов (x) и игреков (y) при приравнивании уравнений сокращаются, поэтому их не учитывают.
		// -2*X*c1.getX() + x1pow2 - 2*Y*c1.getY() + y1pow2 - r1pow2 + 2*X*c2.getX() - x2pow2 + 2*Y*c2.getY() - y2pow2 + r2pow2 = 0  
		double constant = x1pow2+y1pow2-r1pow2-x2pow2-y2pow2+r2pow2;
		// 2*X*(c2.getX() - c1.getX()) + 2*Y*(c2.getY() - c1.getY()) + constant = 0
		double dX = c2.getX() - c1.getX();
		double dY = c2.getY() - c1.getY();
		System.out.println(dX + " " + dY);
		//	итоговое: 2*X*dX + 2*Y*dY + constant = 0
		//	выражаем:
		//	2*X*dX = -(2*Y*dY + constant)
		//	X = -(2*Y*dY + constant)/2*dX;
		// 	подставляем в уравнение окружности и решаем:
		//	Xpow2 + Ypow2 - 2*X*c1.getX() + x1pow2 - 2*Y*c1.getY() + y1pow2 - r1pow2 = 0
		double constant1 = x1pow2+y1pow2-r1pow2;
		double a = 1-Math.pow(dY,2)/Math.pow(dX,2);
		double b = 2*c1.getX()*dY/dX-2*dY*constant/(3*dX)-2*c1.getY();
		double c = Math.pow(constant/(4*dX), 2)+c1.getX()*constant/dX+constant1;
		double d = Math.pow(b, 2)-4*a*c;
		System.out.println(a + " " + b + " " + c + " " + d);
		if(a==0) {
			//если коэффициент при Y = 0, то решаем линейное уравнение.
			y = -c/b;
			x = -(2*y*dY + constant)/(2*dX);
			res.add(new Point(x,y));
		} else {
			if (d<0) {
				// сет решений
				throw new Error("No intersection point");
			}
			if (d==0) {
				y = -b/2*a;
				x = -(2*y*dY + constant)/(2*dX);
				res.add(new Point(x,y));
			}
			if (d>0) {
				System.out.println("here");
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
