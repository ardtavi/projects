package start;

import java.lang.reflect.Field;
import java.util.List;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

public class ReflectionUtils {

	/**
	 * Metoda ce obtine informatii despre variabilele de instanta ale unui obiect,
	 * folosind reflection. Folosit pentru a afisa in JList un obiect de tipul
	 * Client
	 *
	 * @param object
	 * @return
	 */
	public static String getClientAsString(Object object) {
		StringBuilder result = new StringBuilder();
		// Parcurgem lista de variabile de instanta
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				// Adaugam la rezultat informatiile corespunzatoare
				value = field.get(object);
				if (field.getName().equals("id")) {
					result.append((int) value);
				} else if (field.getName().equals("name")) {
					result.append(". ").append(String.valueOf(value));
				} else if (field.getName().equals("address")) {
					result.append(", ").append(String.valueOf(value));
				} else if (field.getName().equals("email")) {
					result.append(", ").append(String.valueOf(value));
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return result.toString();
	}

	public static String[] getClientHeaders(Object object) {
		String[] result = new String[4];
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			try {
				// Adaugam la rezultat informatiile corespunzatoare
				if (field.getName().equals("id")) {
					result[0] = field.getName();
				} else if (field.getName().equals("name")) {
					result[1] = field.getName();
				} else if (field.getName().equals("address")) {
					result[2] = field.getName();
				} else if (field.getName().equals("email")) {
					result[3] = field.getName();
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String[][] getClientMatrix() {
		List<Client> clientList = new ClientBLL().getAllClients();
		String[][] result = new String[clientList.size()][4];
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			for (Field field : client.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					// Adaugam la rezultat informatiile corespunzatoare
					value = field.get(client);
					if (field.getName().equals("id")) {
						result[i][0] = String.valueOf((int) value);
					} else if (field.getName().equals("name")) {
						result[i][1] = String.valueOf(value);
					} else if (field.getName().equals("address")) {
						result[i][2] = String.valueOf(value);
					} else if (field.getName().equals("email")) {
						result[i][3] = String.valueOf(value);
					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * Metoda ce obtine informatii despre variabilele de instanta ale unui obiect,
	 * folosind reflection. Folosit pentru a afisa in JList un obiect de tipul
	 * Product
	 *
	 * @param object
	 * @return
	 */
	public static String getProductAsString(Object object) {
		StringBuilder result = new StringBuilder();
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				if (field.getName().equals("id")) {
					result.append((int) value);
				} else if (field.getName().equals("name")) {
					result.append(". ").append(String.valueOf(value));
				} else if (field.getName().equals("stock")) {
					result.append(", ").append(String.valueOf(value));
				} else if (field.getName().equals("price")) {
					result.append(", ").append(String.valueOf(value));
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return result.toString();
	}

	public static String[] getProductHeaders(Object object) {
		String[] result = new String[4];
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			try {
				// Adaugam la rezultat informatiile corespunzatoare
				if (field.getName().equals("id")) {
					result[0] = field.getName();
				} else if (field.getName().equals("name")) {
					result[1] = field.getName();
				} else if (field.getName().equals("stock")) {
					result[2] = field.getName();
				} else if (field.getName().equals("price")) {
					result[3] = field.getName();
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String[][] getProductMatrix() {
		List<Product> productList = new ProductBLL().getAllProducts();
		String[][] result = new String[productList.size()][4];
		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			for (Field field : product.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					// Adaugam la rezultat informatiile corespunzatoare
					value = field.get(product);
					if (field.getName().equals("id")) {
						result[i][0] = String.valueOf((int) value);
					} else if (field.getName().equals("name")) {
						result[i][1] = String.valueOf(value);
					} else if (field.getName().equals("stock")) {
						result[i][2] = String.valueOf(value);
					} else if (field.getName().equals("price")) {
						result[i][3] = String.valueOf(value);
					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String getOrdersAsString(Object object) {
		StringBuilder result = new StringBuilder();
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				if (field.getName().equals("id")) {
					result.append((int) value);
				} else if (field.getName().equals("client")) {
					result.append(". ").append(String.valueOf(value));
				} else if (field.getName().equals("productList")) {
					result.append(", ").append(String.valueOf(value));
				} else if (field.getName().equals("deliveryNotes")) {
					result.append(", ").append(String.valueOf(value));
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return result.toString();
	}

	public static String[] getOrdersHeaders(Object object) {
		String[] result = new String[4];
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			try {
				// Adaugam la rezultat informatiile corespunzatoare
				if (field.getName().equals("id")) {
					result[0] = field.getName();
				} else if (field.getName().equals("client")) {
					result[1] = field.getName();
				} else if (field.getName().equals("productList")) {
					result[2] = field.getName();
				} else if (field.getName().equals("deliveryNotes")) {
					result[3] = field.getName();
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String[][] getOrdersMatrix() {
		List<Order> orderList = new OrderBLL().getAllOrderObj();
		String[][] result = new String[orderList.size()][4];
		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);
			for (Field field : order.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					// Adaugam la rezultat informatiile corespunzatoare
					value = field.get(order);
					if (field.getName().equals("id")) {
						result[i][0] = String.valueOf((int) value);
					} else if (field.getName().equals("client")) {
						if (value != null) {
							result[i][1] = String.valueOf(value);
						}
					} else if (field.getName().equals("productList")) {
						if (value != null) {
							result[i][2] = String.valueOf(value);
						}
					} else if (field.getName().equals("deliveryNotes")) {
						result[i][3] = String.valueOf(value);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}