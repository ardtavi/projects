package bll.validators;

import model.Product;

/**
 * Validator pentru pretul unui produs. Acesta trebuie sa fie mai mare decat 0.5
 * RON. In caz contrar se arunca o exceptie
 */
public class ProductPriceValidator implements Validator<Product> {

	private static final double MIN_PRICE = 0.5;

	public void validate(Product t) {
		if (t.getPrice() < MIN_PRICE) {
			throw new IllegalArgumentException("Price of a product cannot be less than: " + MIN_PRICE + "!");
		}
	}
}
