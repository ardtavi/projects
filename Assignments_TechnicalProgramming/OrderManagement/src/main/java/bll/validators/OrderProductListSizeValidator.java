package bll.validators;

import model.Order;

/**
 * Valideaza ca o comanda contine cel putin un produs
 */
public class OrderProductListSizeValidator implements Validator<Order> {
	public void validate(Order order) {
		if (order.getProductList().size() == 0) {
			throw new IllegalArgumentException("Cannot insert order without any products!");
		}
	}
}
