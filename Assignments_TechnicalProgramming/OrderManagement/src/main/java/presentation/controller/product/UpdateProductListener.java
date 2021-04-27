package presentation.controller.product;

import bll.ProductBLL;
import model.Product;
import presentation.view.ProductsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProductListener implements ActionListener {
    private JTextField nameTxt;
    private JTextField stockTxt;
    private JTextField priceTxt;
    private JFrame addProductFrame;
    private ProductsFrame productsFrame;

    private ProductBLL productBLL = new ProductBLL();
    private int idToUpdate;

    public UpdateProductListener(JTextField nameTxt, JTextField stockTxt, JTextField priceTxt, JFrame addProductFrame, ProductsFrame productsFrame, int idToUpdate) {
        this.nameTxt = nameTxt;
        this.stockTxt = stockTxt;
        this.priceTxt = priceTxt;
        this.addProductFrame = addProductFrame;
        this.productsFrame = productsFrame;
        this.idToUpdate = idToUpdate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Product product = new Product(nameTxt.getText(), Integer.parseInt(stockTxt.getText()), Double.parseDouble(priceTxt.getText()));
        boolean result = productBLL.updateProduct(product, idToUpdate);
        if (result) {
            JOptionPane.showMessageDialog(null, "Produs updatat cu success!");
            addProductFrame.dispose();
            productsFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la update produs!");
        }
    }
}

