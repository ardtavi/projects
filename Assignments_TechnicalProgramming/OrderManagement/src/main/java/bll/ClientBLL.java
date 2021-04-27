package bll;

import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic layer pentru a obtine informatii despre clienti. Contine
 * metode ce ajuta la manipularea clientilor prin access direct la layerul de
 * date
 */
public class ClientBLL {

	// Lista de validatori pentru clienti. Se va folosi la inserare
	private List<Validator<Client>> validators;

	/**
	 * Constructor ce initializeaza validatorii
	 */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
	}

	/**
	 * Returneaza un obiect de client din baza de date pe baza unui ID
	 * 
	 * @param id
	 * @return
	 */
	public Client findClientById(int id) {
		// Accessam layerul de date
		Client st = ClientDAO.findById(id);
		// In caz ca clientul nu a fost gasit, aruncam o exceptie
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Metoda ce insereaza un client in db. Inainte de a-l trimite mai departe la
	 * layerul de date, se valideaza obiectul cu ajutorul validatorilor defiiniti
	 * 
	 * @param client
	 * @return
	 */
	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return ClientDAO.insert(client);
	}

	/**
	 * Similar cu metoda de insert, valideaza clientul cu datele noi dupa care le
	 * trimite la layerul de date
	 * 
	 * @param client
	 * @param id
	 * @return
	 */
	public boolean updateClient(Client client, int id) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return ClientDAO.update(client, id);
	}

	/**
	 * Metoda ce sterge un client din DB.
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteClient(int id) {
		return ClientDAO.delete(id);
	}

	/**
	 * Returneaza toti clientii din baza de date, accessand layer-ul de date
	 * 
	 * @return
	 */
	public List<Client> getAllClients() {
		return ClientDAO.getAllClients();
	}
}
