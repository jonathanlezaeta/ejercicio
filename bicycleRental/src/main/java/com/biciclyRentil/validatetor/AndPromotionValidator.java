package com.biciclyRentil.validatetor;

import com.biciclyRental.calculate.CompositePrice;

public class AndPromotionValidator implements PromotionValidatorInterface{
	public PromotionValidatorInterface validatorOne;
	public PromotionValidatorInterface validatorTwo;
	
	public AndPromotionValidator(PromotionValidatorInterface validatorOne, PromotionValidatorInterface validatorTwo){
		this.validatorOne = validatorOne;
		this.validatorTwo = validatorTwo;
	}
	
	public PromotionValidatorInterface getValidatorOne() {
		return validatorOne;
	}

	public void setValidatorOne(PromotionValidatorInterface validatorOne) {
		this.validatorOne = validatorOne;
	}

	public PromotionValidatorInterface getValidatorTwo() {
		return validatorTwo;
	}

	public void setValidatorTwo(PromotionValidatorInterface validatorTwo) {
		this.validatorTwo = validatorTwo;
	}

	public boolean validate(CompositePrice promotion) {
		if(validatorOne.validate(promotion) && validatorTwo.validate(promotion))
			return true;
		else 
			return false;
	}
	
}
