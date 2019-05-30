package start;

import presentation.controller.NavigationController;
import presentation.view.MainFrame;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
    public static void main(String[] args) {
        // Pornim main frame
        NavigationController.startFrame(new MainFrame());
    }
}
