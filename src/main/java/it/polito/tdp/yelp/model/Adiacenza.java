package it.polito.tdp.yelp.model;

import java.time.LocalDate;

public class Adiacenza {
	private Review r1;
	private Review r2;
	private LocalDate data1;
	private LocalDate data2;
	public Adiacenza(Review r1, Review r2, LocalDate data1, LocalDate data2) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.data1 = data1;
		this.data2 = data2;
	}
	public Review getR1() {
		return r1;
	}
	public void setR1(Review r1) {
		this.r1 = r1;
	}
	public Review getR2() {
		return r2;
	}
	public void setR2(Review r2) {
		this.r2 = r2;
	}
	public LocalDate getData1() {
		return data1;
	}
	public void setData1(LocalDate data1) {
		this.data1 = data1;
	}
	public LocalDate getData2() {
		return data2;
	}
	public void setData2(LocalDate data2) {
		this.data2 = data2;
	}
	@Override
	public String toString() {
		return "Adiacenza r1=" + r1 + ", r2=" + r2 + ", data1=" + data1 + ", data2=" + data2 ;
	}
	
	
}
