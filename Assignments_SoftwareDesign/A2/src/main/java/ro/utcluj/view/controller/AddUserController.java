package ro.utcluj.view.controller;

import static ro.utcluj.view.controller.helper.DialogsHelper.showCustomDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.view.controller.command.LoginCommand;
import ro.utcluj.view.model.parking.Request;
import ro.utcluj.view.model.users.Register;
import ro.utcluj.view.model.users.User;

public class AddUserController implements Initializable{
	 	@FXML
	    private Button register;
	 	@FXML
	    private TextField username;
		@FXML
	    private TextField password;
		@FXML
	    private TextField passwordconfirm;

	 	@Override
		public void initialize(URL location, ResourceBundle resources) {
			register.setOnAction(e -> {

				LoginCommand loginCommand = new LoginCommand(username.getText(), password.getText());
				// we should add the command to the call stack before execution. this is skipped for now.
				User user = loginCommand.execute();
				if (user != null) {
					if (user.getType() == User.Type.ADMIN) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Admin Page not implemented yet!");
						alert.showAndWait();
					} else {
						changeScene();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Invalid Login Credentials");
					alert.setHeaderText("Invalid Login Credentials");
					alert.showAndWait();
				}
			});
			register.setOnAction(e -> {
				register();
				
			});
		}

		private void changeScene() {
			try {
				Stage window = (Stage) register.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
				Parent root = loader.load();
				window.setTitle("Parking system");
				window.setScene(new Scene(root));
			} catch (IOException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error loading parking system");
				alert.setContentText(e.getMessage());
			}
		
        
		}
		private void register() {
			try {
				Stage window = (Stage) register.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
				Parent root = loader.load();
				window.setTitle("Register");
				window.setScene(new Scene(root));
			} catch (IOException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error loading parking system");
				alert.setContentText(e.getMessage());
			}
		}
}
