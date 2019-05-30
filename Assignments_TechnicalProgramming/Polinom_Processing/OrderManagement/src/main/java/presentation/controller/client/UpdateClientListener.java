package presentation.controller.client;

import bll.ClientBLL;
import model.Client;
import presentation.view.ClientsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateClientListener implements ActionListener {
    private JTextField nameTxt;
    private JTextField addressTxt;
    private JTextField emailTxt;
    private JFrame addClientFrame;
    private ClientsFrame clientsFrame;

    private ClientBLL clientBLL = new ClientBLL();
    private int idToUpdate;

    public UpdateClientListener(JTextField nameTxt, JTextField addressTxt, JTextField emailTxt, JFrame addClientFrame, ClientsFrame clientsFrame, int idToUpdate) {
        this.nameTxt = nameTxt;
        this.addressTxt = addressTxt;
        this.emailTxt = emailTxt;
        this.addClientFrame = addClientFrame;
        this.clientsFrame = clientsFrame;
        this.idToUpdate = idToUpdate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client client = new Client(nameTxt.getText(), addressTxt.getText(), emailTxt.getText());
        boolean result = clientBLL.updateClient(client, idToUpdate);
        if (result) {
            JOptionPane.showMessageDialog(null, "Client updatat cu success!");
            addClientFrame.dispose();
            clientsFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la update client!");
        }
    }
}
