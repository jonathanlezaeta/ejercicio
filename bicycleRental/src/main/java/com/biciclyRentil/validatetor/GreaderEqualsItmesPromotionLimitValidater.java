package com.biciclyRentil.validatetor;

import com.biciclyRental.calculate.CompositePrice;

public class GreaderEqualsItmesPromotionLimitValidater implements PromotionValidatorInterface{

	public int limit;
	
	public GreaderEqualsItmesPromotionLimitValidater(int limit){
		this.limit = limit;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean validate(CompositePrice promotion) {
		if(promotion.getRentailCosts().size() >= limit)
			return true;
		else
			return false;
	}


}
