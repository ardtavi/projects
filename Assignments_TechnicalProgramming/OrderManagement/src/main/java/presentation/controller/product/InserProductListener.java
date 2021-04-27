package presentation.controller.product;

import bll.ProductBLL;
import model.Product;
import presentation.view.ProductsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserProductListener implements ActionListener {

    private JTextField nameTxt;
    private JTextField stockTxt;
    private JTextField priceTxt;
    private JFrame addProductFrame;
    private ProductsFrame productsFrame;

    private ProductBLL productBLL = new ProductBLL();

    public InserProductListener(JTextField nameTxt, JTextField stockTxt, JTextField priceTxt, JFrame addProductFrame, ProductsFrame productsFrame) {
        this.nameTxt = nameTxt;
        this.stockTxt = stockTxt;
        this.priceTxt = priceTxt;
        this.addProductFrame = addProductFrame;
        this.productsFrame = productsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Product product = new Product(nameTxt.getText(), Integer.parseInt(stockTxt.getText()), Double.parseDouble(priceTxt.getText()));
        int result = productBLL.insertProduct(product);
        if (result != -1) {
            JOptionPane.showMessageDialog(null, "Produs adaugat cu success!");
            addProductFrame.dispose();
            productsFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la inserare produs!");
        }
    }
}
