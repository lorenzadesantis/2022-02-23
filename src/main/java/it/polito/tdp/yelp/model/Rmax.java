package it.polito.tdp.yelp.model;

public class Rmax {
	private String r1;
	private int num;
	public Rmax(String r1, int num) {
		super();
		this.r1 = r1;
		this.num = num;
	}
	public String getR1() {
		return r1;
	}
	public void setR1(String r1) {
		this.r1 = r1;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return  r1 + "        #ARCHI USCENTI:" + num;
	}
	
	
	
	
}
