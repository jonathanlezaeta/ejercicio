package com.bicycleRental;

import java.util.ArrayList;

import com.biciclyRental.calculate.AbstractRentailPrice;
import com.biciclyRental.calculate.CompositePrice;
import com.biciclyRental.calculate.SimplePrice;
import com.biciclyRentil.validatetor.AndPromotionValidator;
import com.biciclyRentil.validatetor.GreaderEqualsItmesPromotionLimitValidater;
import com.biciclyRentil.validatetor.MinorEqualsPromotionValidator;
import com.biciclyRentil.validatetor.PromotionValidatorInterface;

/**
 * Hello world!
 *
 */
public class App 
{
	//DSOLO CREE LOS ATRIBUTOS PARA QUE APAREZCAN EN EL DIAGRAMA UML GENERADO
	public AbstractRentailPrice rentail;
	public PromotionValidatorInterface validator;
	
    public static void main( String[] args )
    {
    	SimplePrice hours = new SimplePrice();
		hours.setDescripcion("PRECIOS POR HORA ");
		hours.setPrice(5);
		SimplePrice day = new SimplePrice();
		day.setDescripcion("PRECIO POR DIA ");
		day.setPrice(20);
		SimplePrice weely = new SimplePrice();
		weely.setDescripcion("PRECIO POR SEMANA ");
		weely.setPrice(60);
		
		double p1 = 0;
		double p2 = 0;
		double p3 = 0;
		
		boolean result = true;
		
		//CALCULO DE PRECIOS POR DIA
		hours.setUnits(6);
		p1 = hours.getRentalBicycle();
		hours.setUnits(12);
		p2 = hours.getRentalBicycle();
		hours.setUnits(23);
		p3 = hours.getRentalBicycle();
		
		//CALCULO DE PRECIOS POR SEMANA
		day.setUnits(1);
	    p1 = day.getRentalBicycle();
		day.setUnits(3);
		p2 = day.getRentalBicycle();
		day.setUnits(6);
		p3 = day.getRentalBicycle();
		
		//CALCULO DE PRECIOS POR MES
		weely.setUnits(1);
	    p1 = weely.getRentalBicycle();
		weely.setUnits(3);
		p2 = weely.getRentalBicycle();
		weely.setUnits(6);
		p3 = weely.getRentalBicycle();
		
		//CALCULO UNA PROMOCION CON DESCUENTO
		
		ArrayList<AbstractRentailPrice> promotion = new ArrayList<AbstractRentailPrice>();
		promotion.add(hours);
		promotion.add(day);
		promotion.add(weely);
		
		CompositePrice promotionCalculation = new CompositePrice(promotion, 0.3, "Family Rental");
		
		//VALIDO QUE LA PROMOSION NO TENGA MENOS DE 3 Y MENOS DE 5 TIPOS DE PRECIOS
		
		GreaderEqualsItmesPromotionLimitValidater graderEqualsValidator = new GreaderEqualsItmesPromotionLimitValidater(3);
		MinorEqualsPromotionValidator minorEqualsValidator = new MinorEqualsPromotionValidator(5);
		AndPromotionValidator validator = new AndPromotionValidator(graderEqualsValidator, minorEqualsValidator);
		
		System.out.println("EL PRECIO POR 23 HORAS, 6 DIAS Y 6 SEMANA es: " + promotionCalculation.getRentalBicycle());
		
		if(validator.validate(promotionCalculation))
			System.out.println("PROMOCION VALIDA");
		
    }
}
