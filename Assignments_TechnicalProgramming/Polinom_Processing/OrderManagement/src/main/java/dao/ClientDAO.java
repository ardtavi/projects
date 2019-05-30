package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 *
 *         Reprezinta layerul de access la date pentru manipularea clientilor
 */
public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

	// Definm queryurile de SQL ca si constante, reprezinta un parametru pentru
	// query.
	private static final String insertStatementString = "INSERT INTO client (name,address,email) VALUES (?,?,?)";
	private static final String findStatementString = "SELECT * FROM client where id = ?";
	private static final String updateStatementString = "UPDATE client SET name = ?, address = ?, email = ? WHERE id = ?";
	private static final String deleteStatementString = "DELETE FROM client where id = ?";
	private static final String getAllStatementString = "SELECT * FROM client";

	/**
	 * Returneaza un client din baza de date bazat pe ID-ul acestuia
	 * 
	 * @param clientId
	 * @return
	 */
	public static Client findById(int clientId) {
		Client toReturn = null;

		// Obtinem conexiunea la db
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			// Pregatim sql statementul
			findStatement = dbConnection.prepareStatement(findStatementString);
			// Adaugam parametrii
			findStatement.setLong(1, clientId);
			// executam query
			rs = findStatement.executeQuery();
			rs.next();

			// Parsam raspunsul
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			// Creem obiectul de tip client cu datele luate din Db
			toReturn = new Client(clientId, name, address, email);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			// Inchidem conexiunea, result setul si query-ul
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * Insereaza un client in baza de date. Returneaza id-ul clientului proaspat
	 * inserat, sau -1 daca operatia de insertie esueaza
	 * 
	 * @param client
	 * @return
	 */
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getAddress());
			insertStatement.setString(3, client.getEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	/**
	 * Updateaza datele unui client din baza de date, pe baza unui ID. Returneaza
	 * true daca operatie reuseste, false daca esueaza
	 * 
	 * @param client
	 * @param idToUpdate
	 * @return
	 */
	public static boolean update(Client client, int idToUpdate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, client.getName());
			updateStatement.setString(2, client.getAddress());
			updateStatement.setString(3, client.getEmail());
			updateStatement.setInt(4, idToUpdate);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	/**
	 * Sterge un client din baza de date bazat pe un ID al acestuia. Returneaza true
	 * daca operatia reuseste, false daca nu
	 * 
	 * @param id
	 * @return
	 */
	public static boolean delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(deleteStatementString);
			updateStatement.setInt(1, id);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	/**
	 * Returneaza toti clientii din baza de date
	 * 
	 * @return
	 */
	public static List<Client> getAllClients() {
		List<Client> productList = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int clientId = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");
				Client toAdd = new Client(clientId, name, address, email);
				productList.add(toAdd);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClintDAO:getAllClients " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return productList;
	}
}
