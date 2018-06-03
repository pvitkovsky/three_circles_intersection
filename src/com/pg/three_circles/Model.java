package com.pg.three_circles;

import java.util.ArrayList;

public class Model {
	private final ArrayList<Circle> circles;
	private final ArrayList<PairCircle> pairs;
	private final boolean commonPointExists;
	private final Point pointP;
	
	public boolean getCommonPointExists() {
		return commonPointExists;
	}
	
	public Point getPointP() {
		return pointP;
	}

	public ArrayList<PairCircle> getPairs() {
		return pairs;
	}
	
	public Model(ArrayList<Circle> circles) {
		super();
		this.circles = simplify(circles);
		this.pairs = createPairs();
		this.commonPointExists = testCommonPoint();
		this.pointP = findP();
	}
	
	private ArrayList<Circle> simplify(ArrayList<Circle> circList) {
		if (circList.size() < 2) return circList;
		ArrayList<Circle> res = new ArrayList<Circle>();
		for(int i = 0; i < circList.size(); i++) {
			Circle cTest = circList.get(i);
			boolean keepCircle = true;
			for(int j = i + 1; j < circList.size(); j++) {
				Circle cTestAgainst = circList.get(j);
				if(cTest.sameCircle(cTestAgainst) || cTest.isContainedIn(cTestAgainst)) {
					keepCircle = false;
					break;
				}
			}
			if (keepCircle) res.add(cTest);
		}
		return res;
	}

	private ArrayList<PairCircle> createPairs() {
		ArrayList<PairCircle> set = new ArrayList<PairCircle>();
		set.add(new PairCircle(circles.get(0), circles.get(circles.size()-1)));
		for (int i = 1; i < circles.size(); i++){
			set.add(new PairCircle(circles.get(i-1), circles.get(i)));
		}
		return set;
	}
	
	private boolean testCommonPoint() {
		if (circles.size() == 1) return true;
		if (testNoContact()) return false;  
		if (testContactExists()) return testContactPoints();
		return true;
	
	}
	
	private boolean testNoContact() {
		for (PairCircle pair : pairs){
			if (pair.intersectionType() == IntersectionType.none) return true;
		}
		return false;
	}
	
	private boolean testContactExists() {
		for (PairCircle pair : pairs){
			if (pair.intersectionType() == IntersectionType.contact) return true;
		}
		return false;
	}
	
	private boolean testContactPoints() {
		for (PairCircle pair : pairs){
			if (pair.intersectionType() == IntersectionType.contact) {
				Point potentialContact = pair.contactPoint();
				for(Circle c : circles) if (!c.contains(potentialContact)) {
					//System.out.println("not contained?");
					return false;
				}
			}
		}
		return true;
	}

	private Point findP() {
		// TODO Auto-generated method stub
		return null;
	}

}