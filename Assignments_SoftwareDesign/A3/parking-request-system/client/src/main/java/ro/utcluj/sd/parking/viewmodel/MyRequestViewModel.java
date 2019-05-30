package ro.utcluj.sd.parking.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ro.utcluj.sd.dto.RequestDTO;

public class MyRequestViewModel {
    private ObservableList<RequestDTO> requests = FXCollections.observableArrayList();


    public ObservableList<RequestDTO> getRequests(){
        return requests;
    }
   /* public ObservableList<String> getTheData() {
        return theData;
    }

    public void setTheData(ObservableList<String> theData) {
        this.theData = theData;
    }
*/
    public void addNewElement() {
       /* String val = "Element " + counter;
        theData.add(val);
        counter++;
        lastElementAdded.setValue(val);*/
    }

   /* public StringProperty getLastElementAdded() {
        return lastElementAdded;
    }*/

    public void removeElement() {

    }
}
