package com.biciclyRental.calculate;

public class SimplePrice extends AbstractRentailPrice{

	public double price;
	public double units;
	
	public SimplePrice(){
		this.price = 0;
		this.units = 0;
	}
	
	public double getRentalBicycle() {
		System.out.println(descripcion + " TIEMPO " + units + " X " + price + " = " + price * units);
		return price * units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

}
