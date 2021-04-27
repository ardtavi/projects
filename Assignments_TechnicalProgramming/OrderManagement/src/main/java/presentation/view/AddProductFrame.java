package presentation.view;

import presentation.controller.product.InserProductListener;
import presentation.controller.product.UpdateProductListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddProductFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtNume;
    private JTextField txtStock;
    private JTextField txtPrice;

    public AddProductFrame(ProductsFrame productsFrame) {
        this(productsFrame, -1);
    }

    public AddProductFrame(ProductsFrame productsFrame, int idToUpdate) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 208, 187);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblAdaugaClient = new JLabel("Nume");
        contentPane.add(lblAdaugaClient);

        txtNume = new JTextField();
        contentPane.add(txtNume);
        txtNume.setColumns(10);

        JLabel lblAdresa = new JLabel("Stock");
        contentPane.add(lblAdresa);

        txtPrice = new JTextField();
        contentPane.add(txtPrice);
        txtPrice.setColumns(10);

        JLabel lblEmail = new JLabel("Pret");
        contentPane.add(lblEmail);

        txtStock = new JTextField();
        contentPane.add(txtStock);
        txtStock.setColumns(10);

        JButton btnAdauga = new JButton("Adauga");
        if (idToUpdate != -1) {
            btnAdauga.setText("Actualizeaza");
            btnAdauga.addActionListener(new UpdateProductListener(txtNume, txtPrice, txtStock, this, productsFrame, idToUpdate));
        } else {
            btnAdauga.addActionListener(new InserProductListener(txtNume, txtPrice, txtStock, this, productsFrame));
        }
        contentPane.add(btnAdauga);
    }

}
