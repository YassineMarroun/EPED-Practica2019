package es.uned.lsi.eped.pract2018_2019;

import java.util.ArrayList;

public class Par {

	private ArrayList<Integer> p1 = new ArrayList<Integer>();
	private ArrayList<Integer> p2 = new ArrayList<Integer>();
	
	public Par(ArrayList<Integer> p1, ArrayList<Integer> p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public ArrayList<Integer> getP1() {
		return p1;
	}

	public void setP1(ArrayList<Integer> p1) {
		this.p1 = p1;
	}

	public ArrayList<Integer> getP2() {
		return p2;
	}

	public void setP2(ArrayList<Integer> p2) {
		this.p2 = p2;
	}
}
