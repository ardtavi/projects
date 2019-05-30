package presentation.controller.order;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
import presentation.view.OrdersFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InsertOrderListener implements ActionListener {
    private JTextField productIdTxt;
    private JTextField clientIdTxt;
    private JTextField deliveryNotesTxt;
    private JTextField quantityTxt;
    private JFrame addOrderFrame;
    private OrdersFrame ordersFrame;

    private OrderBLL orderBLL = new OrderBLL();

    public InsertOrderListener(JTextField productIdTxt, JTextField clientIdTxt, JTextField deliveryNotesTxt, JTextField quantityTxt, JFrame addOrderFrame, OrdersFrame ordersFrame) {
        this.productIdTxt = productIdTxt;
        this.clientIdTxt = clientIdTxt;
        this.deliveryNotesTxt = deliveryNotesTxt;
        this.addOrderFrame = addOrderFrame;
        this.ordersFrame = ordersFrame;
        this.quantityTxt = quantityTxt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client client = new ClientBLL().findClientById(Integer.parseInt(clientIdTxt.getText()));
        Product product = new ProductBLL().findProductById(Integer.parseInt(productIdTxt.getText()));
        product.setStock(Integer.parseInt(quantityTxt.getText()));
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Order order = new Order(client, productList, deliveryNotesTxt.getText());
        int result = orderBLL.insertOrder(order);
        if (result != -1) {
            JOptionPane.showMessageDialog(null, "Comanda adaugata cu success!");
            addOrderFrame.dispose();
            ordersFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la inserare client!");
        }
    }
}
