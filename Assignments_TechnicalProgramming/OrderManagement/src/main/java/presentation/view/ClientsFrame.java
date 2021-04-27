package presentation.view;

import bll.ClientBLL;
import model.Client;
import presentation.controller.client.DeleteClientListener;
import presentation.controller.client.FindClientListener;
import presentation.controller.NavigationController;
import start.ReflectionUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ClientsFrame extends JFrame {

    private JPanel contentPane;
    private JTextField clientSearchTxt;
    private JTable clientList;

    /**
     * Create the frame.
     */
    public ClientsFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblClienti = new JLabel("Clienti");
        lblClienti.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        contentPane.add(lblClienti, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        initializeClients();
        scrollPane.setViewportView(clientList);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnAdaugaClient = new JButton("Adauga Client");
        btnAdaugaClient.addActionListener(new NavigationController.AddClientBtnListener(this));
        panel.add(btnAdaugaClient);

        JButton btnEditeazaClient = new JButton("Editeaza Client");
        panel.add(btnEditeazaClient);

        JButton btnDeleteClient = new JButton("Sterge Client");
        panel.add(btnDeleteClient);

        JLabel lblCautaClient = new JLabel("Cauta client:");
        panel.add(lblCautaClient);

        clientSearchTxt = new JTextField();
        panel.add(clientSearchTxt);
        clientSearchTxt.setColumns(10);
        btnDeleteClient.addActionListener(new DeleteClientListener(clientSearchTxt, this));
        btnEditeazaClient.addActionListener(new NavigationController.UpdateClientBtnListener(this, clientSearchTxt));

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new FindClientListener(clientSearchTxt));
        panel.add(btnOk);
    }

    private void initializeClients() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ClientBLL clientBLL = new ClientBLL();
        List<Client> clientsList = clientBLL.getAllClients();
        for (Client client : clientsList) {
            listModel.addElement(ReflectionUtils.getClientAsString(client));
        }
        clientList = new JTable(ReflectionUtils.getClientMatrix(), ReflectionUtils.getClientHeaders(new Client(0,"A","A","A")));
    }

    public void refreshData() {
        dispose();
        NavigationController.startFrame(new ClientsFrame());
    }

}
