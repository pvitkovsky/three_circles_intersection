package com.pg.three_circles;

import java.util.ArrayList;

public class CommonPoint{
	private ArrayList<Point> g;
	public CommonPoint(ArrayList<Point> g){
		 this.g = g;
	}
	public CommonPoint(){
		 this.g = null;
	}
	public ArrayList<Point> getG() {
		return g;
	}
	@Override
	public String toString() {
		return "CommonPoint [g=" + g + "]";
	}
	
}