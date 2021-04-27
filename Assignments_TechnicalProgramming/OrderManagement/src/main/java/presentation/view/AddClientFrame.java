package presentation.view;

import presentation.controller.client.InsertClientListener;
import presentation.controller.client.UpdateClientListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddClientFrame extends JFrame {

    /**
	 * 
	 */
	private JPanel contentPane;
    private JTextField txtNume;
    private JTextField txtEmail;
    private JTextField txtAddress;

    public AddClientFrame(ClientsFrame clientsFrame) {
        this(clientsFrame, -1);
    }

    public AddClientFrame(ClientsFrame clientsFrame, int idToUpdate) {
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

        JLabel lblAdresa = new JLabel("Adresa");
        contentPane.add(lblAdresa);

        txtAddress = new JTextField();
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        JButton btnAdauga = new JButton("Adauga");
        if (idToUpdate == -1) {
            btnAdauga.addActionListener(new InsertClientListener(txtNume, txtAddress, txtEmail, this, clientsFrame));
        } else {
            btnAdauga.setText("Actualizeaza");
            btnAdauga.addActionListener(new UpdateClientListener(txtNume, txtAddress, txtEmail, this, clientsFrame, idToUpdate));
        }
        contentPane.add(btnAdauga);
    }

}
