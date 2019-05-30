package ro.utcluj.sd.parking.model;


import javafx.collections.ObservableList;
import ro.utcluj.sd.dto.RequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ObservableModel extends Observable {

    private ObservableList<RequestDTO> requests;

    public void addElement(RequestDTO requestDTO) {
        requests.add(requestDTO);
        //counter++;
        //setChanged();
        //notifyObservers(lastAddedElement);
    }

    /*public List<String> getTheData() {
        return theData;
    }*/
}
