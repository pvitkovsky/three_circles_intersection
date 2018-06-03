package com.pg.three_circles;

import java.util.ArrayList;

public class Exec {

	public static void main() {
		Circle a = new Circle(0., 0., 1.);
		Circle b = new Circle(1., 0., 1.);
		Circle c = new Circle(2., 0., 1.);
		Circle d = new Circle(3., 0., 1.);
		
		test2(a, b);
		test2(a, c); //problematic test case - see PairCircle.getPointsOfIntersection for (0, 0) ; (2. 0);
		test2(a, d);
		test2(b, c);
		test2(b, d);
		test2(c, d);
		test2(d, d);
			
	}
	
	public static void test2(Circle first, Circle second) {
		ArrayList<Circle> circles = new ArrayList<Circle>();
		circles.add(first);
		circles.add(second);
		Model mod = new Model(circles);
		System.out.println(mod.getCommonPointExists());
	}

}
