package ro.utcluj.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Parking Request System");
		primaryStage.setScene(new Scene(root, 500, 275));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
