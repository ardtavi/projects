package presentation.controller.order;

import bll.OrderBLL;
import model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class FindOrderListener implements ActionListener {

    private JTextField idTxtField;

    private OrderBLL orderBLL = new OrderBLL();

    public FindOrderListener(JTextField idTxtField) {
        this.idTxtField = idTxtField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Order order = orderBLL.findOrderById(Integer.parseInt(idTxtField.getText()));
            JOptionPane.showMessageDialog(null, order);
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Comanda cu id " + idTxtField.getText() + " nu a fost gasit!");
        }

    }
}
