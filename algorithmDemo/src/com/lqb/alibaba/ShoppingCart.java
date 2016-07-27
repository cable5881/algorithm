package com.lqb.alibaba;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<ShopAndGoods> goodsBags; 
	private double totalPriceOfGoodBags;
	
	public ShoppingCart() {
		goodsBags = new ArrayList<>();
		totalPriceOfGoodBags = 0;
	}

	public double getTotalPriceOfGoodBags(){
		
		if( totalPriceOfGoodBags == 0){
			for(ShopAndGoods goodsBag : goodsBags){
				totalPriceOfGoodBags += goodsBag.getTotalPrice();
			}
		}
		
		return totalPriceOfGoodBags;
	}
}
