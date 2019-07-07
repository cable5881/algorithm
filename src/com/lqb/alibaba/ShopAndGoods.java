package com.lqb.alibaba;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */
public class ShopAndGoods {

	private Shop shop;
	private double totalPrice;
	private List<Goods> goodsBag;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public ShopAndGoods() {
		totalPrice = 0;
		goodsBag = new ArrayList<>();
	}

	public List<Goods> getGoodsBag() {
		return goodsBag;
	}

	public void setGoodsBag(List<Goods> goodsBag) {
		this.goodsBag = goodsBag;
	}


	public double getTotalPrice() {

		for (Goods goods : goodsBag) {
			totalPrice += goods.getPrice();
		}

		return totalPrice;
	}
}
