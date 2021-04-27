package presentation.controller.product;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class FindProductListener implements ActionListener {
    private JTextField idTxtField;

    private ProductBLL productBLL = new ProductBLL();

    public FindProductListener(JTextField idTxtField) {
        this.idTxtField = idTxtField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Product product = productBLL.findProductById(Integer.parseInt(idTxtField.getText()));
            JOptionPane.showMessageDialog(null, product.toString());
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Produsul cu id " + idTxtField.getText() + " nu a fost gasit!");
        }

    }
}
