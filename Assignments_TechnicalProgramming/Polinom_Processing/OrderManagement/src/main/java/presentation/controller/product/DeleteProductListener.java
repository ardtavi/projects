package presentation.controller.product;

import bll.ProductBLL;
import presentation.view.ProductsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductListener implements ActionListener {

    private ProductsFrame productsFrame;
    private JTextField idTxtField;

    private ProductBLL productBLL = new ProductBLL();

    public DeleteProductListener(JTextField idTxtField, ProductsFrame productsFrame) {
        this.productsFrame = productsFrame;
        this.idTxtField = idTxtField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = productBLL.deleteProduct(Integer.parseInt(idTxtField.getText()));
        if (success) {
            JOptionPane.showMessageDialog(null, "Produs sters cu success!");
            productsFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la stergere produs!");
        }
    }
}
