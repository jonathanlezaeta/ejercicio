package com.biciclyRental.calculate;

import java.util.ArrayList;

public class CompositePrice extends AbstractRentailPrice{

	public ArrayList<AbstractRentailPrice> rentailCosts;
	public double discount; 
	
	public CompositePrice(ArrayList<AbstractRentailPrice> rentailCosts, double discount, String descripcion){
		this.rentailCosts = rentailCosts;
		this.discount = discount;
		this.descripcion = descripcion;
	}
	
	public ArrayList<AbstractRentailPrice> getRentailCosts() {
		return rentailCosts;
	}
	
	public void setRentailCosts(ArrayList<AbstractRentailPrice> rentailCosts) {
		this.rentailCosts = rentailCosts;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getRentalBicycle() {
		double result = 0;
		for(AbstractRentailPrice rp : rentailCosts){
			result += rp.getRentalBicycle();
		}
		System.out.println(descripcion + " TOTAL = " + result * (1 - discount));
		return result * (1 - discount);
	}
	
}
