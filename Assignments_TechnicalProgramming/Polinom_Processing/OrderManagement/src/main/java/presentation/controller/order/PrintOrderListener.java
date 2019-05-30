package presentation.controller.order;

import bll.OrderBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintOrderListener implements ActionListener {

    private JTextField idTxtField;
    private OrderBLL orderBLL = new OrderBLL();

    public PrintOrderListener(JTextField idTxtField) {
        this.idTxtField = idTxtField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = orderBLL.printBillToFile(Integer.parseInt(idTxtField.getText()));
        if (fileName != null) {
            JOptionPane.showMessageDialog(null, "S-a printat chitanta in fisierul: " + fileName);
        } else {
            JOptionPane.showMessageDialog(null, "Eroare la printare chitanta!");
        }
    }
}
