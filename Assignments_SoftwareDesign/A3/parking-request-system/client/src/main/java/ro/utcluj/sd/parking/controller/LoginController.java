/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcluj.sd.parking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ro.utcluj.sd.parking.command.LoginCommand;

import java.io.IOException;

public class LoginController{
    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    @FXML
    public Label label;
    @FXML
    public Button login;

    public Button getLogin() {
        return login;
    }

    public void login(ActionEvent actionEvent) {
       if(new LoginCommand(label, username.getText(), password.getText()).execute().compareToIgnoreCase("admin") == 0)
           System.out.println("is admin");
       else if (new LoginCommand(label, username.getText(), password.getText()).execute().compareToIgnoreCase("user") == 0) {
           System.out.println("is user");
           login.setOnAction(e->moveOn(e));
       }
       else if (new LoginCommand(label, username.getText(), password.getText()).execute().compareToIgnoreCase("invalid") == 0)
           System.out.println("Invalid");
    }

    public void moveOn(ActionEvent actionEvent) {
        Scene scene = login.getScene();
        Parent newFxml = null;
        try {
            newFxml = FXMLLoader.load(getClass().getResource("/MyView.fxml"));
            scene.setRoot(newFxml);
            scene.getWindow().sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
