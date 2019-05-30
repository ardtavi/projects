package bll;

import bll.validators.ProductPriceValidator;
import bll.validators.ProductStockValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ce contine metode ce ajuta la manipularea produselor prin access direct
 * la layerul de date
 * 
 * @author olarp
 *
 */
public class ProductBLL {
	// Lista de validatori pentru produs
	private List<Validator<Product>> validators;

	// Constructor care initializeaza validatorii
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductPriceValidator());
		validators.add(new ProductStockValidator());
	}

	/**
	 * Metoda care gaseste un obiect de tip Product in functie de ID
	 * 
	 * @param id
	 * @return
	 */
	public Product findProductById(int id) {
		Product st = ProductDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Metoda de inserarea a produsului
	 * 
	 * @param product
	 * @return
	 */
	public int insertProduct(Product product) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		return ProductDAO.insert(product);
	}

	/**
	 * Metoda care face update la STOCK dupa ce s-a efectuat o comanda
	 * 
	 * @param product
	 * @param id
	 * @return
	 */
	public boolean updateProduct(Product product, int id) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		return ProductDAO.update(product, id);
	}

	/**
	 * Metoda pentru stergerea unui produs in functie de id
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteProduct(int id) {
		return ProductDAO.delete(id);
	}

	/**
	 * Metoda care returneaza o lista de produse
	 * 
	 * @return
	 */
	public List<Product> getAllProducts() {
		List<Product> result = ProductDAO.getAllProducts();
		if (result.size() == 0) {
			throw new NoSuchElementException("Empty products table! Insert some data!");
		}
		return result;
	}
}
