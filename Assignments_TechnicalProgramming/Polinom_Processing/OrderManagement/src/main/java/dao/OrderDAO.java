package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (client_id, delivery_notes) VALUES (?,?)";
    private static final String insertOrderItemStatementString = "INSERT INTO order_item (order_id, product_id, quantity) VALUES (?,?,?)";
    private static final String findOrderByIdStatementString = "SELECT * FROM orders " +
            "INNER JOIN order_item ON order_item.order_id = orders.id " +
            "INNER JOIN product ON order_item.product_id = product.id " +
            "WHERE order_id = ?";
    private static final String getAllORdersStatementString = "SELECT * FROM orders";
    
    public static List<Integer> getAllOrderIds(){
        List<Integer> orderIdsList = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(getAllORdersStatementString);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                orderIdsList.add(orderId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:getAllOrdersIds " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orderIdsList;
    }

    public static List<String> getAllOrders() {
        List<String> orderList = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(getAllORdersStatementString);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                int clientId = rs.getInt("client_id");
                String deliveryNotes = rs.getString("delivery_notes");
                String order = orderId + ". " + ClientDAO.findById(clientId) + ", Notes: " + deliveryNotes;
                orderList.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:getAllOrders " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orderList;
    }

    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getClient().getId());
            insertStatement.setString(2, order.getDeliveryNotes());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        List<Product> products = order.getProductList();

        // Insert each order item
        for (Product product : products) {
            insertOrderItem(product, insertedId);
        }
        return insertedId;
    }

    private static int insertOrderItem(Product product, int orderId) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertOrderItemStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orderId);
            insertStatement.setInt(2, product.getId());
            insertStatement.setInt(3, product.getStock());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            Product previous = ProductDAO.findById(product.getId());
            int previousStock = previous.getStock();
            ProductDAO.update(new Product(product.getId(), product.getName(), previousStock - product.getStock(), product.getPrice()), product.getId());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insertOrderItem " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static Order findById(int orderId) {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findOrderByIdStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            String deliveryNotes = null;
            Client client = null;
            List<Product> productList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                deliveryNotes = rs.getString("delivery_notes");
                client = ClientDAO.findById(rs.getInt("client_id"));
                Product product = new Product(id, name, stock, price);
                productList.add(product);
            }
            toReturn = new Order(orderId, client, productList, deliveryNotes);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
