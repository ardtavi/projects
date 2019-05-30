package presentation.view;

import presentation.controller.order.InsertOrderListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddOrderFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Create the frame.
     */
    public AddOrderFrame(OrdersFrame ordersFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 240, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblIdProdus = new JLabel("Id produs");
        contentPane.add(lblIdProdus);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblIdClient = new JLabel("Id client");
        contentPane.add(lblIdClient);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblCantitate = new JLabel("Cantitate");
        contentPane.add(lblCantitate);

        textField_3 = new JTextField();
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblDetaliiComanda = new JLabel("Detalii comanda:");
        contentPane.add(lblDetaliiComanda);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JButton btnAdauga = new JButton("Adauga");
        btnAdauga.addActionListener(new InsertOrderListener(textField, textField_1, textField_2, textField_3, this, ordersFrame));
        contentPane.add(btnAdauga);
    }

}
