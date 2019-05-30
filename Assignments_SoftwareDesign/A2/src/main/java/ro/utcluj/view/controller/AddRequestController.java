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
package ro.utcluj.view.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ro.utcluj.view.model.parking.Request;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddRequestController {

    @FXML
    private Node root;

    public Request getRequest() {
        return new Request(getText("request"),
                        getText("name"),
                        getText("date"),
                        getText("status"));
    }

    private String getText(String field) {
        return ((TextField) root.lookup("#" + field)).getText();
    }
}
