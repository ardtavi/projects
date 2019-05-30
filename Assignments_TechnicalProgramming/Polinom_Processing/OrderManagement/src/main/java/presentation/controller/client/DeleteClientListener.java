package presentation.controller.client;

import bll.ClientBLL;
import presentation.view.ClientsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pentru butonul de delete client. Avem nevoie de o referinta la frame
 * pentru a updata lista dupa delete.
 */
public class DeleteClientListener implements ActionListener {

	// Necesar pentru a actualiza UI-ul de clients, implicit lista de clienti dupa
	// ce stergem unul
	private ClientsFrame clientsFrame;
	private JTextField idTxtField;

	private ClientBLL clientBLL = new ClientBLL();

	public DeleteClientListener(JTextField idTxtField, ClientsFrame clientsFrame) {
		this.clientsFrame = clientsFrame;
		this.idTxtField = idTxtField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean success = clientBLL.deleteClient(Integer.parseInt(idTxtField.getText()));
		if (success) {
			JOptionPane.showMessageDialog(null, "Client sters cu success!");
			// Facem refresh la lista
			clientsFrame.refreshData();
		} else {
			JOptionPane.showMessageDialog(null, "Eroare la stergere client!");
		}
	}
}
