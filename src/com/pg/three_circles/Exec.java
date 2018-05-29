package com.pg.three_circles;

public class Exec {

	public static void main() {
		Circle c1 = Circle.getRandomCircle();
		Circle c2 = Circle.getRandomCircle();
		Circle c3 = Circle.getRandomCircle();
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(Circle.common(c1, c2, c3));

		System.out.println("-----------------------");
    
		Circle a = new Circle(5., 5., 3.);
		Circle b = new Circle(5., 5., 3.);
		Circle c = new Circle(2., 6., 2.);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(Circle.common(a,b,c));
	}

}
