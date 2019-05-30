package bll;

import bll.validators.OrderProductListSizeValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa in care v-om crea metodele de inserare pt order si de asemenea in care
 * vom crea o metoda pentru printarea facturii
 * 
 * @author olarp
 *
 */
public class OrderBLL {

	protected static final Logger LOGGER = Logger.getLogger(OrderBLL.class.getName());

	// Lista de validatori pentru orderuri. Se va folosi la inserare
	private List<Validator<Order>> validators;

	/**
	 * Constructor ce initializeaza validatorii
	 */
	public OrderBLL() {
		validators = new ArrayList<Validator<Order>>();
		validators.add(new OrderProductListSizeValidator());
	}

	/**
	 * Metoda pentru inserarea unei comenzi
	 * 
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order) {
		for (Validator<Order> v : validators) {
			v.validate(order);
		}
		return OrderDAO.insert(order);
	}

	/**
	 * Metoda care returneaza un obiect order in functie de ID
	 * 
	 * @param id
	 * @return
	 */
	public Order findOrderById(int id) {
		Order st = OrderDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The order with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Returneaza toate orderurile din baza de date
	 * 
	 * @return
	 */
	public List<String> getAllOrders() {
		return OrderDAO.getAllOrders();
	}

	/**
	 * Metoda ce printeaza intr-un fisier chitanta pentru o comanda, bazandu-se pe
	 * id-ul comenzii
	 * 
	 * @param orderId
	 * @return
	 */
	public String printBillToFile(int orderId) {
		String fileName = "bill_for_order" + orderId + ".txt";
		// Deschidem fisierul si incepem sa scriem in el
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			// obtinem comanda apeland findById
			bw.write(findOrderById(orderId).toString());
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Error writing bill to file" + e);
		}
		return fileName;
	}

	public List<Order> getAllOrderObj() {
		List<Order> result = new ArrayList<>();
		List<Integer> allOrderIds = OrderDAO.getAllOrderIds();
		for (int id : allOrderIds) {
			Order o = findOrderById(id);
			result.add(o);
		}
		return result;
	}
}
