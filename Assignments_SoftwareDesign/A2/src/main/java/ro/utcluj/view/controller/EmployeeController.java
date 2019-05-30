package ro.utcluj.view.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.view.controller.helper.DialogsHelper;
import ro.utcluj.view.model.parking.Request;
import ro.utcluj.view.model.parking.Parking;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ro.utcluj.view.controller.helper.DialogsHelper.showCustomDialog;

public class EmployeeController implements Initializable {

    private Parking model;

    @FXML
    private TableView<Request> table;

    @FXML
    TableColumn<Request, Integer> stockColumn;

    @FXML
    private Button delete;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Button create;
   
    @FXML
    private Button back;
    
    @FXML
    private Button update;
    
    @FXML
    private Button searchByName;
    
    @FXML
    private TextField textsearch;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = Parking.getInstance();
        table.setItems(model.getRequests());

       // stockColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        delete.setOnAction(event -> deleteRequest());
        create.setOnAction(event -> createRequest());
        back.setOnAction(event -> backScene());
        update.setOnAction(event-> updateRequest());
        searchByName.setOnAction(event-> find());
    }

    private void createRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addRequest.fxml"));
            Parent parent = fxmlLoader.load();
            AddRequestController dialogController = fxmlLoader.getController();
            showCustomDialog("Add Request", parent, () -> dialogController.getRequest())
                    .ifPresent(newRequest -> {
                        model.addRequest(newRequest);
                    });

        } catch (Exception e) {
            DialogsHelper.showErrorDialog("Unable to add Request!");
            e.printStackTrace();
        }
    }

    private void deleteRequest() {
        TableView.TableViewSelectionModel<Request> selectionModel = table.getSelectionModel();
        Request selectedItem = selectionModel.getSelectedItem();
        if (selectedItem != null) {
            model.deleteRequest(selectedItem);
        }
    }
    private void updateRequest() {
        TableView.TableViewSelectionModel<Request> selectionModel = table.getSelectionModel();
        Request selectedItem = selectionModel.getSelectedItem();
        if (selectedItem != null) {
            model.deleteRequest(selectedItem);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addRequest.fxml"));
            Parent parent = fxmlLoader.load();
            AddRequestController dialogController = fxmlLoader.getController();
            showCustomDialog("Update Request", parent, () -> dialogController.getRequest())
                    .ifPresent(newRequest -> {
                        model.addRequest(newRequest);
                    });

        } catch (Exception e) {
            DialogsHelper.showErrorDialog("Unable to add Request!");
            e.printStackTrace();
        }
    }
    private void find() {
    	if(textsearch.getText()!=null)
    	{
    	Request req1=model.findRequestbyname(textsearch.getText());
    	table.setItems(null);
    	model.addRequest(req1);
    	table.setItems(table.getItems());
    	}
    	else
    	{
    		  this.model = Parking.getInstance();
    		  table.setItems(model.getRequests());
    	}
    	
   

    }
    private void backScene() {
    	try {
			Stage window = (Stage) back.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
			Parent root = loader.load();
			window.setTitle("Parking system");
			window.setScene(new Scene(root));
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error loading parking system");
			alert.setContentText(e.getMessage());
		}
	}
}
