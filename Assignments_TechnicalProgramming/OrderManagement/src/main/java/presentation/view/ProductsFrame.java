package presentation.view;

import bll.ProductBLL;
import model.Product;
import presentation.controller.NavigationController;
import presentation.controller.product.DeleteProductListener;
import presentation.controller.product.FindProductListener;
import start.ReflectionUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ProductsFrame extends JFrame {

    private JPanel contentPane;
    private JTextField productSearchTxt;
    private JTable productList;

    /**
     * Create the frame.
     */
    public ProductsFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblProduse = new JLabel("Produse");
        lblProduse.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        contentPane.add(lblProduse, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        initializeProducts();
        scrollPane.setViewportView(productList);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnAdaugaProdus = new JButton("Adauga Produs");
        btnAdaugaProdus.addActionListener(new NavigationController.AddProductBtnListener(this));
        panel.add(btnAdaugaProdus);

        JButton btnEditeazaProdus = new JButton("Editeaza Produs");
        panel.add(btnEditeazaProdus);

        JButton btnNewButton = new JButton("Sterge Produs");
        panel.add(btnNewButton);

        JLabel lblCautaClient = new JLabel("Cauta produs:");
        panel.add(lblCautaClient);

        productSearchTxt = new JTextField();
        panel.add(productSearchTxt);
        productSearchTxt.setColumns(10);


        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new FindProductListener(productSearchTxt));
        btnNewButton.addActionListener(new DeleteProductListener(productSearchTxt, this));
        btnEditeazaProdus.addActionListener(new NavigationController.UpdateProductBtnListener(this, productSearchTxt));
        panel.add(btnOk);
    }

    private void initializeProducts() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ProductBLL productBLL = new ProductBLL();
        List<Product> productsList = productBLL.getAllProducts();
        for (Product product : productsList) {
            listModel.addElement(ReflectionUtils.getProductAsString(product));
        }
        productList = new JTable(ReflectionUtils.getProductMatrix(), ReflectionUtils.getProductHeaders(new Product(0,"A",0,0)));
    }

    public void refreshData() {
        dispose();
        NavigationController.startFrame(new ProductsFrame());
    }

}
