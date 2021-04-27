/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;
import java.awt.Dimension;
public class Main {

    /*
      args the command line arguments
     */
    public static void main(String[] args) {
    	
        GUI g = new GUI(); // create a new object of type GUI
       
       
        
        g.setVisible(true);     // because the GUI class extends JFrame that means that g will act like a JFrame, so we can's see nothing unless we set it's visibility to true
        g.setTitle("Queues manager");     // set the title of the application window
        g.setPreferredSize(new Dimension(1000, 1000));    // a dimension which fits with our needs
        g.setResizable(false);  // lock the resize option for window
        g.pack();// let
    }
}