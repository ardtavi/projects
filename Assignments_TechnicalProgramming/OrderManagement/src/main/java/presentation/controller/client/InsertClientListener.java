package presentation.controller.client;

import bll.ClientBLL;
import model.Client;
import presentation.view.ClientsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pentru butonul de insert client. Toate detaliile vin de la view, si sunt transmise prin constructor
 */
public class InsertClientListener implements ActionListener {

    private JTextField nameTxt;
    private JTextField addressTxt;
    private JTextField emailTxt;
    private JFrame addClientFrame;
    private ClientsFrame clientsFrame;

    private ClientBLL clientBLL = new ClientBLL();

    public InsertClientListener(JTextField nameTxt, JTextField addressTxt, JTextField emailTxt, JFrame addClientFrame, ClientsFrame clientsFrame) {
        this.nameTxt = nameTxt;
        this.addressTxt = addressTxt;
        this.emailTxt = emailTxt;
        this.addClientFrame = addClientFrame;
        this.clientsFrame = clientsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Creem obiectul ce trebuie inserat
        Client client = new Client(nameTxt.getText(), addressTxt.getText(), emailTxt.getText());
        int result = clientBLL.insertClient(client);
        if (result != -1) {
            JOptionPane.showMessageDialog(null, "Client adaugat cu success!");
            // Dupa insert, updatam si lista
            addClientFrame.dispose();
            clientsFrame.refreshData();
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la inserare client!");
        }
    }
}
