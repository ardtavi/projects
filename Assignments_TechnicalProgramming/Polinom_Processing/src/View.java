
import java.awt.Color;

import java.awt.event.ActionListener;

import javax.swing.*;

public class View{
	 JButton SumButton = new JButton("Addition"); 
	 JTextField Polynomial1Text = new JTextField();
	 JTextField Polynomial2Text = new JTextField();
	 JTextField Monom1TextField = new JTextField();
	 JTextField Monom2TextField = new JTextField();
	 JTextField Monom3TextField = new JTextField();
	 JTextField Monom4TextField = new JTextField();
	 JTextField ResultTextField = new JTextField();
	 JButton Save1Button = new JButton("Add Monom");
	 JButton Save2Button = new JButton("Add Monom");
	 JButton DerivateButton1 = new JButton("Derivate Polynomial 1");
	 JButton DerivateButton2 = new JButton("Derivate Polynomial 2");
	 JButton IntegrateButton1 = new JButton("Integrate Polynomial 2");
	 JButton IntegrateButton2 = new JButton("Integrate Polynomial 2");
	 JButton SubButton = new JButton("Substraction"); 
	 JButton MulButton = new JButton("Multiply"); 
	 
	 String str2;
	 String str1;
	
	 public void grapInterf()
		{	
		 	
		 	JFrame frame;
		    frame = new JFrame();
			frame.setLayout(null);
			frame.setSize(1200, 800);
			frame.setTitle("Polynomial processing");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel(null, true);
			panel.setLayout(null);
			panel.setSize(1200, 700);
			panel.setLocation(50, 50);

			
			
			JLabel Monom1Label = new JLabel("Monom 1 Coeficient: ");			//Text for Monom 1
			Monom1Label.setSize(170, 20);
			Monom1Label.setLocation(20, 20);
			panel.add(Monom1Label);

						
			Monom1TextField.setSize(170, 20);
			Monom1TextField.setLocation(170, 20);
			panel.add(Monom1TextField);
			
			
			JLabel Monom2Label = new JLabel("Monom 1 Power: ");			//Text for Monom 2
			Monom2Label.setSize(170, 20);
			Monom2Label.setLocation(20, 40);
			panel.add(Monom2Label);
			
			
			Monom2TextField.setSize(170, 20);
			Monom2TextField.setLocation(170, 40);
			panel.add(Monom2TextField);
			
			JLabel Monom3Label = new JLabel("Monom 2 Coeficient: ");			//Text for Monom 1
			Monom3Label.setSize(170, 20);
			Monom3Label.setLocation(20, 140);
			panel.add(Monom3Label);

						
			Monom3TextField.setSize(170, 20);
			Monom3TextField.setLocation(170, 140);
			panel.add(Monom3TextField);
			
			
			JLabel Monom4Label = new JLabel("Monom 2 Power: ");			//Text for Monom 2
			Monom4Label.setSize(170, 20);
			Monom4Label.setLocation(20, 160);
			panel.add(Monom4Label);
			
			
			Monom4TextField.setSize(170, 20);
			Monom4TextField.setLocation(170, 160);
			panel.add(Monom4TextField);
			
			
			JLabel Polynomial1Label = new JLabel("Polynomial1: ");		
			Polynomial1Label.setSize(170, 20);//Text for Polynomial1
			Polynomial1Label.setLocation(370, 20);
			panel.add(Polynomial1Label);

							
			Polynomial1Text.setSize(170, 20);
			Polynomial1Text.setLocation(450, 20);
			panel.add(Polynomial1Text);


			
			JLabel Polynomial2Label = new JLabel("Polynomial2: ");		
			Polynomial2Label.setSize(170, 20);//Text for Polynomial2
			Polynomial2Label.setLocation(370, 160);
			panel.add(Polynomial2Label);

							
			Polynomial2Text.setSize(170, 20);
			Polynomial2Text.setLocation(450, 160);
			panel.add(Polynomial2Text);
			
			
			
			
		
			Save1Button.setSize(150, 20);
			Save1Button.setLocation(100, 60);
			panel.add(Save1Button);
			
			Save2Button.setSize(150, 20);
			Save2Button.setLocation(100, 180);
			panel.add(Save2Button);

			JLabel errorsLabel = new JLabel("errors");
			errorsLabel.setForeground(Color.red);
			errorsLabel.setSize(170, 20);
			errorsLabel.setLocation(20, 0);
			errorsLabel.setVisible(false);
			
			panel.add(SumButton);
			SumButton.setSize(100, 20);
			SumButton.setLocation(20, 280);
			panel.add(SubButton);
			SubButton.setSize(140, 20);
			SubButton.setLocation(160, 280);
			panel.add(MulButton);
			MulButton.setSize(100, 20);
			MulButton.setLocation(340, 280);
			
			DerivateButton1.setSize(200, 20);
			DerivateButton1.setLocation(20, 320);
			panel.add(DerivateButton1);
			DerivateButton2.setSize(200, 20);
			DerivateButton2.setLocation(240, 320);
			panel.add(DerivateButton2);
			IntegrateButton1.setSize(200, 20);
			IntegrateButton1.setLocation(20, 360);
			panel.add(IntegrateButton1);
			IntegrateButton2.setSize(200, 20);
			IntegrateButton2.setLocation(240, 360);
			panel.add(IntegrateButton2);

			panel.add(errorsLabel);
			
			JLabel ResultLabel = new JLabel("Result: ");		
			ResultLabel.setSize(50, 20);
			ResultLabel.setLocation(500, 240);
			panel.add(ResultLabel);
			
			panel.add(ResultTextField);
		    ResultTextField.setSize(300,20);
		    ResultTextField.setLocation(580,240);
			
			frame.add(panel);
			frame.setVisible(true);
		   
		}
	 int getter_monom1coef() {
		 return Integer.parseInt(Monom1TextField.getText());
	 }
	 int getter_monom1power() {
		 return Integer.parseInt(Monom2TextField.getText());
	 }
	 int getter_monom2coef() {
		 return Integer.parseInt(Monom3TextField.getText());
	 }
	 int getter_monom2power() {
		 return Integer.parseInt(Monom4TextField.getText());
	 }
	 public void save_pol1(ActionListener e)
	 {
	 		Save1Button.addActionListener(e);
			//public void actionPerformed(ActionEvent e) {
	 }
	 public void save_pol2(ActionListener e)
	 {
	 		Save1Button.addActionListener(e);
			//public void actionPerformed(ActionEvent e) {
	 }
	 public void setter_pol1(String s)
	 {
		 Polynomial1Text.setText(s);
	 }
	 public void setter_pol2(String s)
	 {
		 Polynomial2Text.setText(s);
	 }
	 public void setter_result(String s)
	 {
		 ResultTextField.setText(s);
	 }
	 void addPol1Listener(ActionListener listenForPolyButton){
		 Save1Button.addActionListener(listenForPolyButton);
	
		     }
	 void addPol2Listener(ActionListener listenForPolyButton){
		 Save2Button.addActionListener(listenForPolyButton);
	
		     }
	 void SumListener(ActionListener listenForPolyButton) {
	 		SumButton.addActionListener(listenForPolyButton);
	 }
	 void Derivate1Listener(ActionListener listenForPolyButton) {
	 		DerivateButton1.addActionListener(listenForPolyButton);
	 }
	 void Derivate2Listener(ActionListener listenForPolyButton) {
	 		DerivateButton2.addActionListener(listenForPolyButton);
	 }
	 void Integrate1Listener(ActionListener listenForPolyButton) {
	 		IntegrateButton1.addActionListener(listenForPolyButton);
	 }
	 void Integrate2Listener(ActionListener listenForPolyButton) {
		 IntegrateButton2.addActionListener(listenForPolyButton);
	 }
	 void SubListener(ActionListener listenForPolyButton) {
		 SubButton.addActionListener(listenForPolyButton);
		
	}
	 void MulListener(ActionListener listenForPolyButton) {
		 MulButton.addActionListener(listenForPolyButton);
			
		}
}
