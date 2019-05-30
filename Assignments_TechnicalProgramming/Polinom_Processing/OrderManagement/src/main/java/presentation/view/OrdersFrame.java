package presentation.view;

import bll.OrderBLL;
import model.Order;
import model.Product;
import model.Client;
import presentation.controller.NavigationController;
import presentation.controller.order.FindOrderListener;
import presentation.controller.order.PrintOrderListener;
import start.ReflectionUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersFrame extends JFrame {

    private JPanel contentPane;
    private JTextField orderSearchTxt;
    private JTable orderList;

    /**
     * Create the frame.
     */
    public OrdersFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblClienti = new JLabel("Istoric comenzi");
        lblClienti.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        contentPane.add(lblClienti, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        initializeOrders();
        scrollPane.setViewportView(orderList);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnAddOrder = new JButton("Adaugare comanda");
        btnAddOrder.addActionListener(new NavigationController.AddOrderBtnListener(this));
        panel.add(btnAddOrder);

        JButton btnDeleteClient = new JButton("Printare comanda");
        panel.add(btnDeleteClient);

        JLabel lblCautaClient = new JLabel("Cauta comanda:");
        panel.add(lblCautaClient);

        orderSearchTxt = new JTextField();
        panel.add(orderSearchTxt);
        orderSearchTxt.setColumns(10);
        btnDeleteClient.addActionListener(new PrintOrderListener(orderSearchTxt));

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new FindOrderListener(orderSearchTxt));
        panel.add(btnOk);
    }

    private void initializeOrders() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        OrderBLL orderBLL = new OrderBLL();
        List<String> ordersList = orderBLL.getAllOrders();
        for (String order : ordersList) {
            listModel.addElement(order);
        }
        orderList = new JTable(ReflectionUtils.getOrdersMatrix(), ReflectionUtils.getOrdersHeaders(new Order(new Client(0,"A","A","A"),new ArrayList<Product>(),"A")));
    }

    public void refreshData() {
        dispose();
        NavigationController.startFrame(new OrdersFrame());
    }

}
