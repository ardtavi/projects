package presentation.controller;

import presentation.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa folosita pentru a naviga intre JFrames. Contine implemntari de listeneri ce navigheaza intre ferestre.
 * Singura fereastra ce are operatie de exit on close este main frame.
 * Restul fac doar dispose la inchidere pentru a re-lua datele la re-intrare.
 */
public class NavigationController {

    public static void startFrame(final JFrame frame) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static class ShowOrdersBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new OrdersFrame());
        }
    }

    public static class ShowClientsBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new ClientsFrame());
        }
    }

    public static class ShowProductsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new ProductsFrame());
        }
    }

    public static class AddClientBtnListener implements ActionListener {

        private ClientsFrame clientsFrame;

        public AddClientBtnListener(ClientsFrame clientsFrame) {
            this.clientsFrame = clientsFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new AddClientFrame(clientsFrame));
        }
    }

    public static class UpdateClientBtnListener implements ActionListener {

        private ClientsFrame clientsFrame;
        private JTextField idTxtField;

        public UpdateClientBtnListener(ClientsFrame clientsFrame, JTextField idTxtField) {
            this.clientsFrame = clientsFrame;
            this.idTxtField = idTxtField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new AddClientFrame(clientsFrame, Integer.parseInt(idTxtField.getText())));
        }
    }


    public static class AddProductBtnListener implements ActionListener {

        private ProductsFrame productsFrame;

        public AddProductBtnListener(ProductsFrame productsFrame) {
            this.productsFrame = productsFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new AddProductFrame(productsFrame));
        }
    }

    public static class UpdateProductBtnListener implements ActionListener {

        private ProductsFrame productsFrame;
        private JTextField idTxtField;

        public UpdateProductBtnListener(ProductsFrame productsFrame, JTextField idTxtField) {
            this.productsFrame = productsFrame;
            this.idTxtField = idTxtField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new AddProductFrame(productsFrame, Integer.parseInt(idTxtField.getText())));
        }
    }

    public static class AddOrderBtnListener implements ActionListener {

        private OrdersFrame ordersFrame;

        public AddOrderBtnListener(OrdersFrame ordersFrame) {
            this.ordersFrame = ordersFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NavigationController.startFrame(new AddOrderFrame(ordersFrame));
        }
    }
}
