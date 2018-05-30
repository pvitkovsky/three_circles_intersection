package com.test.jtest_circles;

import org.junit.Test;

import com.pg.three_circles.Circle;
import com.pg.three_circles.Type;

import static org.junit.Assert.assertEquals;

public class TestJunit {
   @Test
   public  void testCirclesAI() {
		assertEquals(Type.equals, Circle.common(
				new Circle(5., 5., 3.),
				new Circle(5., 5., 3.)));
		   }
   @Test
   public  void testCirclesAII() {
	   // expected this would be an inclusion, but it is intersections
		assertEquals(Type.inclusion, Circle.common(
				new Circle(0., 0., 3.),
				new Circle(1., 0., 1.)));
		   }
   @Test
   public  void testCirclesAIII() {//
		assertEquals(Type.intersection, Circle.common(
				new Circle(5., 5., 3.),
				new Circle(10., 5., 3.)));
		   }
   @Test
   public  void testCirclesAIV() {//border case
		assertEquals(Type.intersection, Circle.common(
				new Circle(4., 5., 3.),
				new Circle(10., 5., 3.)));
		   }
   @Test
   public  void testCirclesAV() {
		assertEquals(Type.none, Circle.common(
				new Circle(0., 5., 3.),
				new Circle(10., 5., 3.)));
		   }

}