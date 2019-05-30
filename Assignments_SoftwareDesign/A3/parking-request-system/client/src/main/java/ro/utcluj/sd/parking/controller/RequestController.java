package ro.utcluj.sd.parking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ro.utcluj.sd.parking.command.AddElementCommand;
import ro.utcluj.sd.parking.model.ObservableModel;
import ro.utcluj.sd.parking.viewmodel.MyRequestViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class RequestController  implements Initializable, Observer {
    @FXML
    public Button ok;
    /*@FXML
    public TableView<RequestDTO> table;*/

    @FXML
    public TextField year;
    @FXML
    public TextField car_id;
    private MyRequestViewModel myRequestViewModel;
    private ObservableModel observableModel;

    /*public void AddElementInFirstList(ActionEvent actionEvent) {
        new AddElementCommand(myRequestViewModel).execute();
    }*/
    public void ok(ActionEvent actionEvent) throws IOException {
        new AddElementCommand(myRequestViewModel,year.getText()).execute();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myRequestViewModel = new MyRequestViewModel();
//        table.setItems(myRequestViewModel.getRequests());
        //mvLabel.textProperty().bind(myViewModel.getLastElementAdded());

        observableModel = new ObservableModel();
        observableModel.addObserver(this);
        ok.setOnAction(e -> moveOn(e));//observableModel.addElement());
    }

    @Override
    public void update(Observable o, Object arg) {
        ObservableModel myModel = (ObservableModel) o;
        System.out.println(myModel);
        //table.getItems().add(arg);
    }

    public void moveOn(ActionEvent actionEvent) {
        System.out.println("");
        Scene scene = ok.getScene();
        Parent newFxml = null;
        AddElementCommand addElementCommand = new AddElementCommand(myRequestViewModel,year.getText());
        addElementCommand.execute();
        try {
            newFxml = FXMLLoader.load(getClass().getResource("/addRequest.fxml"));
            scene.setRoot(newFxml);
            scene.getWindow().sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addRequest.fxml"));
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
