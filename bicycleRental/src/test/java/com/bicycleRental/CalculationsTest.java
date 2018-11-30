package com.bicycleRental;

import org.junit.Test;

import com.biciclyRental.calculate.AbstractRentailPrice;
import com.biciclyRental.calculate.CompositePrice;
import com.biciclyRental.calculate.SimplePrice;
import com.biciclyRentil.validatetor.AndPromotionValidator;
import com.biciclyRentil.validatetor.GreaderEqualsItmesPromotionLimitValidater;
import com.biciclyRentil.validatetor.MinorEqualsPromotionValidator;
import com.biciclyRentil.validatetor.PromotionValidatorInterface;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class CalculationsTest {
	
	@Test
	public void testBaseCalculation(){
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
		
		if(p1+p2+p3 != 205){
			result = false;
		}
		assertTrue(result);
		
		//CALCULO DE PRECIOS POR SEMANA
		day.setUnits(1);
	    p1 = day.getRentalBicycle();
		day.setUnits(3);
		p2 = day.getRentalBicycle();
		day.setUnits(6);
		p3 = day.getRentalBicycle();
		
		if(p1+p2+p3 != 200){
			result = false;
		}
		assertTrue(result);
		
		//CALCULO DE PRECIOS POR MES
		weely.setUnits(1);
	    p1 = weely.getRentalBicycle();
		weely.setUnits(3);
		p2 = weely.getRentalBicycle();
		weely.setUnits(6);
		p3 = weely.getRentalBicycle();
		
		if(p1+p2+p3 != 600){
			result = false;
		}
		assertTrue(result);
		
		//CALCULO UNA PROMOCION CON DESCUENTO
		ArrayList<AbstractRentailPrice> promotion = new ArrayList<AbstractRentailPrice>();
		promotion.add(hours);
		promotion.add(day);
		promotion.add(weely);
		
		CompositePrice promotionCalculation = new CompositePrice(promotion, 0.3, "Family Rental");
		
		//VALIDO QUE LA PROMOSION NO TENGA MENOS DE 3 Y MENOS DE 5 TIPOS DE PRECIOS
		
		GreaderEqualsItmesPromotionLimitValidater graderEqualsValidator = new GreaderEqualsItmesPromotionLimitValidater(3);
		MinorEqualsPromotionValidator minorEqualsValidator = new MinorEqualsPromotionValidator(5);
		AndPromotionValidator andValidator = new AndPromotionValidator(graderEqualsValidator, minorEqualsValidator);
		
		assertTrue(andValidator.validate(promotionCalculation));
		
		System.out.println("EL PRECIO POR 23 HORAS, 6 DIAS Y 6 SEMANA es: " + promotionCalculation.getRentalBicycle());
		
		if(promotionCalculation.getRentalBicycle() != 416.5){
			result = false;
		}
		assertTrue(result);
		
		
		/*
		 * GENERO TODAS LAS PROMOS POSIBLES BASANDOME EN LOS 3 PRECIOS BASE
		 */
		
		ArrayList<AbstractRentailPrice> promotions = new ArrayList<AbstractRentailPrice>();
		ArrayList<CompositePrice> listPromotions = new ArrayList<CompositePrice>();

		generateAllPromotions(promotion, 0, 5, andValidator, promotions, listPromotions);
		System.out.println("COMBINACION DE PROMOSIONES GENERADAS: " + listPromotions.size());
		for(CompositePrice cp : listPromotions){
			cp.getRentalBicycle();
		}
		
//		for(CompositePrice ap : allPromotions){
//			ap.get
//		}
		
		/* 
		 * CAMBIO LOS PARAMETROS Y VALIDO QUE NO TENGA MENOS DE 5 Y MAS DE 10 ESTO VA A HACER FALLAR EL VALIDADOR
		 * POR ESO EVALUO ASSERTIONFALSE
		 */
		graderEqualsValidator.setLimit(5);
		minorEqualsValidator.setLimit(10);
		
		assertFalse(andValidator.validate(promotionCalculation));
		
		graderEqualsValidator.setLimit(3);
		minorEqualsValidator.setLimit(5);
		
	}
	
	public void generateAllPromotions(ArrayList<AbstractRentailPrice> typePrice, int inicio, int corte, PromotionValidatorInterface validator, ArrayList<AbstractRentailPrice> promotion, ArrayList<CompositePrice> listPromotions){
		if(corte > inicio){
			for(AbstractRentailPrice c : typePrice){
				promotion.add(c);
				inicio++;
				generateAllPromotions(typePrice, inicio, corte, validator, promotion, listPromotions);
				ArrayList<AbstractRentailPrice> promotionClon = (ArrayList<AbstractRentailPrice>) promotion.clone();
				CompositePrice newPromotion = new CompositePrice(promotionClon, 0.3, "Family Rental");
				if(validator.validate(newPromotion)){
					listPromotions.add(newPromotion);
				}else{
					System.out.println("PROMOSION INVALIDA MENOS DE 3 ALQUILERES O MAS DE 5.");
				}
				inicio--;
				promotion.remove(c);
			}
		}
	}
}
