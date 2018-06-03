package com.pg.three_circles;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class Exec {

	public static void main() {
		Circle a = new Circle(0., 0., 1.);
		Circle b = new Circle(1., 0., 1.);
		Circle c = new Circle(2., 0., 1.);
		Circle d = new Circle(3., 0., 1.);
		
		assertTrue(test2(a,b));
		assertTrue(test2(a,c));
		assertFalse(test2(a,d));
		assertTrue(test2(d,d));
		
		Circle notA = new Circle(-10., -10., 10.);
		Circle notB = new Circle(-20., -20., 10.);
		Circle notC = new Circle(0., -40., 10.);

		assertTrue(test2(notA,notB));
		assertFalse(test2(notA,notC));
		
		Circle i = new Circle(0., 2., 1.);
		Circle j = new Circle(0., 0., 1.);
		Circle k = new Circle(2., 0., 2.25);
		assertTrue(test3(i, j, k));
		
		Circle l = new Circle(2., 0., 2.1);
		assertFalse(test3(i,j,l));
		
	}
	
	public static boolean test2(Circle first, Circle second) {
		ArrayList<Circle> circles = new ArrayList<Circle>();
		circles.add(first);
		circles.add(second);
		return testAll(circles);
	}
	
	public static boolean test3(Circle first, Circle second, Circle third) {
		ArrayList<Circle> circles = new ArrayList<Circle>();
		circles.add(first);
		circles.add(second);
		circles.add(third);
		return testAll(circles);
	}
	
	public static boolean testAll(final ArrayList<Circle> circles) {
		Model mod = new Model(circles);
		final boolean res = mod.getCommonPointExists();
		System.out.println(res);
		return res;
	}

}
