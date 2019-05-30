package presentation.view;

import presentation.controller.NavigationController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 454, 126);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JButton btnShowClients = new JButton("Afiseaza Clienti");
        contentPane.add(btnShowClients, BorderLayout.WEST);
        btnShowClients.addActionListener(new NavigationController.ShowClientsBtnListener());

        JButton btnShowProducts = new JButton("Afiseaza Produse");
        contentPane.add(btnShowProducts, BorderLayout.CENTER);
        btnShowProducts.addActionListener(new NavigationController.ShowProductsBtnListener());

        JButton btnShowOrders = new JButton("Afiseaza Comenzi");
        contentPane.add(btnShowOrders, BorderLayout.EAST);
        btnShowOrders.addActionListener(new NavigationController.ShowOrdersBtnListener());

        JLabel lblGestionareComenzi = new JLabel("Gestionare Comenzi");
        lblGestionareComenzi.setHorizontalAlignment(SwingConstants.CENTER);
        lblGestionareComenzi.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        contentPane.add(lblGestionareComenzi, BorderLayout.NORTH);
    }

}
