package ro.utcluj.sd.parking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ro.utcluj.sd.dto.RequestDTO;
import ro.utcluj.sd.parking.command.AddElementCommand;
import ro.utcluj.sd.parking.model.ObservableModel;
import ro.utcluj.sd.parking.viewmodel.MyRequestViewModel;
import ro.utcluj.sd.parking.viewmodel.MyViewModel;

public class MyController implements Initializable, Observer {

    @FXML
    public Button create;
    @FXML
    private Button back;
    @FXML
    public TableView<RequestDTO> table;
    private MyRequestViewModel myRequestViewModel;
    private ObservableModel observableModel;

    /*public void AddElementInFirstList(ActionEvent actionEvent) {
        new AddElementCommand(myRequestViewModel).execute();
    }*/
    public void create(ActionEvent actionEvent) throws IOException {
        new AddElementCommand(myRequestViewModel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myRequestViewModel = new MyRequestViewModel();
//        table.setItems(myRequestViewModel.getRequests());
        //mvLabel.textProperty().bind(myViewModel.getLastElementAdded());

        observableModel = new ObservableModel();
        observableModel.addObserver(this);
        create.setOnAction(e -> moveOn(e));//observableModel.addElement());
        back.setOnAction(e->moveOn2(e));
    }

    @Override
    public void update(Observable o, Object arg) {
        ObservableModel myModel = (ObservableModel) o;
        System.out.println(myModel);
        //table.getItems().add(arg);
    }

    public void moveOn(ActionEvent actionEvent) {
        Scene scene = create.getScene();
        Parent newFxml = null;
        try {
            newFxml = FXMLLoader.load(getClass().getResource("/createRequest.fxml"));
            scene.setRoot(newFxml);
            scene.getWindow().sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveOn2(ActionEvent actionEvent) {
        //Scene scene = back.getScene();
        //Parent newFxml = null;
        try {
            Stage window = (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyView.fxml"));
            Parent root = loader.load();
            //scene.setRoot(newFxml);
            //scene.getWindow().sizeToScene();
            window.setTitle("Parking system");
            window.setScene(new Scene(root));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error loading parking system");
            alert.setContentText(e.getMessage());
        }
    }
    private void backScene() {
        try {
            Stage window = (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            window.setTitle("Parking system");
            window.setScene(new Scene(root));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error loading parking system");
            alert.setContentText(e.getMessage());
        }
    }
    private void createRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/createRequest.fxml"));
            Parent parent = fxmlLoader.load();
            AddElementCommand dialogController = fxmlLoader.getController();
         /*   showCustomDialog("Create R())
                    .ifPresent(newRequest ->{
                        observableModel.addElement();
                    });*/

        } catch (Exception e) {
          //  DialogsHelper.showErrorDialog("Unable to add request!");
            e.printStackTrace();
        }
    }
}
