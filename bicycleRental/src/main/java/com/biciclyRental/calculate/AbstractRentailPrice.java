package com.biciclyRental.calculate;

public abstract class AbstractRentailPrice {
	public String descripcion;
	public abstract double getRentalBicycle();
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
