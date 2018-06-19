package com.test.jtest_circles;

import org.junit.Test;

import com.pg.three_circles.Circle;
import com.pg.three_circles.IntersectionType;

import static org.junit.Assert.assertEquals;

public class TestJunit {
   
	@Test
   public  void testCirclesAI() {
		assertEquals(IntersectionType.equals, Circle.intersectionType(
				new Circle(5., 5., 3.),
				new Circle(5., 5., 3.)));
		   }
   @Test
   public  void testCirclesAII() {
	   // expected this would be an inclusion, but it is intersections
		assertEquals(IntersectionType.inclusion, Circle.intersectionType(
				new Circle(0., 0., 3.),
				new Circle(1., 0., 1.)));
		   }
   @Test
   public  void testCirclesAIII() {//
		assertEquals(IntersectionType.intersection, Circle.intersectionType(
				new Circle(5., 5., 3.),
				new Circle(10., 5., 3.)));
		   }
   @Test
   public  void testCirclesAIV() {//border case
		assertEquals(IntersectionType.contact, Circle.intersectionType(
				new Circle(4., 5., 3.),
				new Circle(10., 5., 3.)));
		   }
   @Test
   public  void testCirclesAV() {
		assertEquals(IntersectionType.none, Circle.intersectionType(
				new Circle(0., 5., 3.),
				new Circle(10., 5., 3.)));
   			}
}