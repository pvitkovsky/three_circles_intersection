package com.test.jtest_circles;

import org.junit.Test;

import com.pg.three_circles.Circle;

import static org.junit.Assert.assertEquals;

public class TestJunit {
   @Test
	
   public  void testCirclesI() {
		Circle a = new Circle(5., 5., 3.);
		Circle b = new Circle(5., 5., 3.);
		Circle c = new Circle(2., 6., 2.);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		assertEquals(true, Circle.common(a,b,c));
		assertEquals("CommonPoint [g=[Point (4,196; 10,088), Point (2,054; 3,662)]]", Circle.commonp(a,b,c).toString());
		//TEST COMMIT - DOT VS COMMA TEST CASE
   }
}