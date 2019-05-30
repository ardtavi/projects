package presentation.controller.client;

import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

/**
 * Listener pentru butonul de cautare client din fereastra de clienti
 */
public class FindClientListener implements ActionListener {

    private JTextField idTxtField;

    private ClientBLL clientBLL = new ClientBLL();

    public FindClientListener(JTextField idTxtField) {
        this.idTxtField = idTxtField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Accesam layerul de business pentru ca gasi clientul cu id-ul luat din view
            Client client = clientBLL.findClientById(Integer.parseInt(idTxtField.getText()));
            // Afisam rezultatul
            JOptionPane.showMessageDialog(null, client.toString());
        } catch (NoSuchElementException ex) {
            // Daca nu il gasim, afisam o eroare
            JOptionPane.showMessageDialog(null, "Clientul cu id " + idTxtField.getText() + " nu a fost gasit!");
        }

    }
}
