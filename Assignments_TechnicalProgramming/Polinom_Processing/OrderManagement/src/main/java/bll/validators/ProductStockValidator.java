package bll.validators;

import model.Product;

/**
 * Valideaza ca stockul unui produs este mai mare decat 0 si mai mic decat 1000.
 * Nu se pot tine in depoziot cantitati mai mari. In caz contrar se arunca o
 * exceptie
 */
public class ProductStockValidator implements Validator<Product> {
	private static final int MIN_STOCK = 0;
	private static final int MAX_STOCK = 1000;

	public void validate(Product t) {
		if (t.getStock() < MIN_STOCK) {
			throw new IllegalArgumentException("Product is out of stock!");
		} else if (t.getStock() > MAX_STOCK) {
			throw new IllegalArgumentException("Product is over stocked!");
		}
	}
}
