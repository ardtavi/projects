
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View {

    JFrame f;
    JButton b;
    JButton b2;
    TextField t;
    View(){
        f=new JFrame();//creating instance of JFrame

        b=new JButton("Medication");//creating instance of JButton
        b.setBounds(130,100,100, 40);

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        t = new TextField();
        t.setBounds(130,150,150,20);
        f.add(t);

        b2=new JButton("ADD Medication");//creating instance of JButton
        b2.setBounds(130,200,150, 40);

        f.add(b2);//adding button in JFrame


    }
    void addAdaugareListener(ActionListener x) {

        b.addActionListener(x);
    }
    void addAdaugareListener2(ActionListener x) {

        b2.addActionListener(x);
    }

    String getText()
    {
        return t.getText();
    }

}
