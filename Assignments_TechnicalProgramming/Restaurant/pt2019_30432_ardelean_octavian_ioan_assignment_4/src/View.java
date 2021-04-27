import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class View {
	private static Connection connection;
	ResultSet rs=null;
	private JFrame p;
	Connect connect = new Connect();
	
	 public void grapInterf()
	{		
		 	JFrame p = new JFrame();
			p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			p.setTitle("Order");
			p.setSize(840,350);
			//p.setLocation(50, 50);
			p.setVisible(true);
			
			JPanel panel1 = new JPanel(null, true);
			panel1.setLayout(null);
			panel1.setSize(840, 300);
			//panel1.setLocation(50, 50);
			
			JButton insertButton = new JButton("Insert Item");
			insertButton.setSize(200, 20);
			insertButton.setLocation(300, 50 );
			panel1.add(insertButton);
			p.add(panel1);
			p.setVisible(true);
			
			JButton deleteButton = new JButton("Delete Item");
			deleteButton.setSize(200, 20);
			deleteButton.setLocation(300, 200);
			panel1.add(deleteButton);
			p.add(panel1);
			p.setVisible(true);
			
			JButton updateButton = new JButton("Update Item");
			updateButton.setSize(200, 20);
			updateButton.setLocation(20, 200);
			panel1.add(updateButton);
			p.add(panel1);
			p.setVisible(true);
			
			JButton stockButton = new JButton("Show Stock");
			stockButton.setSize(200, 20);
			stockButton.setLocation(20, 50);
			panel1.add(stockButton);
			p.add(panel1);
			p.setVisible(true);
		
			JTable jtable = new JTable();
			jtable.setSize(100,40);
			jtable.setLocation(50,100);
			p.add(jtable);
			p.setVisible(true);
			
			JScrollPane pane = new JScrollPane(jtable);
		     pane.setBounds(500, 0, 280, 200);

		      p.setLayout(null);    
		      p.add(pane);
			p.setVisible(true);
			

						
						
					
						insertButton.addActionListener(new ActionListener()
						{
							
							public void actionPerformed(ActionEvent e) {
								if (connection==null)
									connect.connectDatabase();
								
								try {
									PreparedStatement stmt;
									stmt = connection.prepareStatement("insert into restaurant "
											+ "(id, name, number)"
											+ "values ('14 ','Iphone 16', '450')"  );
									stmt.executeUpdate();
								} catch (Exception f) {
									// TODO Auto-generated catch block
									// TODO Auto-generated catch block
								
								}
							}
						 
						});
						
						deleteButton.addActionListener(new ActionListener()
						{
							
							public void actionPerformed(ActionEvent e) {
								if (connection==null)
									connect.connectDatabase();
								
								try {
									PreparedStatement stmt;
									stmt = connection.prepareStatement("delete from restaurant where id=5");
									stmt.executeUpdate();
								} catch (Exception f) {
									// TODO Auto-generated catch block
									// TODO Auto-generated catch block
								
								}
							}
						 
						});
						
						updateButton.addActionListener(new ActionListener()
						{
							
							public void actionPerformed(ActionEvent e) {
								if (connection==null)
									connect.connectDatabase();
								
								try {
									PreparedStatement stmt;
									stmt = connection.prepareStatement("update order set number=135 where id=6");
									stmt.executeUpdate();
								} catch (Exception f) {
									// TODO Auto-generated catch block
									// TODO Auto-generated catch block
								
								}
							}
						 
						});
						
						stockButton.addActionListener(new ActionListener()
						{
							
							public void actionPerformed(ActionEvent e) {
								if (connection==null)
									connect.connectDatabase();
								
								try {
									Statement stmt;
									String sql="select * from order";
									//stmt = connection.prepareStatement("select * from order ");
									//stmt.executeUpdate();
									stmt= connection.createStatement();
									
									rs=stmt.executeQuery(sql);
									jtable.setModel(DbUtils.resultSetToTableModel(rs));
							           
								} catch (Exception f) {
									// TODO Auto-generated catch block
									// TODO Auto-generated catch block
								
								}
							}
						 
						});

						 
						
					}
			
				

		
	
}


