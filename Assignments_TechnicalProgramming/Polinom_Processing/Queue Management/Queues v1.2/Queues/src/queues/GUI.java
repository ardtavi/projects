/**
 * This class has the main priority since it has multiple roles. 
 * First of all, it creates the frame that will contain our simulation, will 
 * start the threads for each queues and for the waiting list of customers. 
 * In order to perform all these operations, this class needs to extend the 
 * JFrame class and to implement the Runnable class.
 */
package queues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


public class GUI extends JFrame implements Runnable{
	
	private static final int MINUTE = 0, SERV = 1, WAIT = 2;
	private JButton btnStart;
	private JLabel lbl11, lbl12, lbl21, lbl22, lbl31, lbl32, lbl41, lbl42, lblPH, lblTime,
     dp1, dp2, dp3, dp4, sp1, sp2;
	private JTextField arrTminM, arrTminS, arrTmaxM, arrTmaxS, servTminM, servTminS,
    servTmaxM, servTmaxS;
	private JScrollPane spQ1, spQ2, spQ3;
	private JLabel avgWQ1, avgWQ2, avgWQ3, servQ1, servQ2, servQ3, emptyQ1, emptyQ2, emptyQ3, avgSQ1, avgSQ2, avgSQ3;
    private TextArea areaQ1, areaQ2, areaQ3;
    private TextArea areaQ4;
    private JComboBox cbSimIS, cbSimIF;
    private JCheckBox ckQ1, ckQ2, ckQ3;
    private int arr1, arr2, serv1, serv2;
    private WaitingQueue wq;
    private Thread mainThread = new Thread(this);
    private ArrayList<Queue> cozi;
    private int simTime = 0, time = 0;
    private String pH;
    private int hStart = 0, hFinish = 0, maxCustInQueues = 0;
    private long eQ1 = 0, eQ2 = 0, eQ3 = 0;
    private boolean ready = false;
    private boolean[] check = {false, false, false}; //boolean array corresponding to the check boxes
    
    public GUI() {	
    	
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel(null, true);
			panel.setLayout(null);
			panel.setSize(900, 900);
			panel.setLocation(0, 0);
			this.add(panel);
			
			btnStart= new JButton();
			btnStart.setSize(120,20);
			btnStart.setLocation(420,160);
			btnStart.setText("Start");
			panel.add(btnStart);
		
			
			lbl11=new JLabel("Arrival time between customers: Min");
			lbl11.setSize(220,20);
			lbl11.setLocation(10, 10);
			panel.add(lbl11);
			
			lbl12=new JLabel("Max:");
			lbl12.setLocation(340, 10);
			lbl12.setSize(120,20);
			panel.add(lbl12);
			
			lbl21=new JLabel("Client time between customers: Min:");
			lbl21.setSize(220,20);
			lbl21.setLocation(10, 40);
			panel.add(lbl21);
			
			lbl22=new JLabel("Max:");;
			lbl22.setLocation(340, 40);
			lbl22.setSize(120,20);
			panel.add(lbl22);
			
			 lbl31 = new JLabel("Current Simulation Time: ");
			 lbl31.setLocation(400, 200);
			 lbl31.setSize(160,20);
			 panel.add(lbl31);
			 
		     lbl32 = new JLabel("Peak Hour: ");
		     lbl32.setLocation(420, 220);
			 lbl32.setSize(100,20);
			 panel.add(lbl32);
			 
		     lbl41 = new JLabel("Simulation interval: Begin ");
		     lbl41.setLocation(250, 70);
			 lbl41.setSize(220,20);
			 panel.add(lbl41);
			 
		     lbl42 = new JLabel("End: ");
		     lbl42.setLocation(480, 70);
			 lbl42.setSize(120,20);
			 panel.add(lbl42);
			 
		     lblTime = new JLabel("00 : 00");
		     lblTime.setLocation(550, 200);
		     lblTime.setSize(150,20);
			 panel.add(lblTime);
			 
		     lblPH = new JLabel("00 : 00");
		     lblPH.setLocation(490, 220);
		     lblPH.setSize(160,20);
			 panel.add(lblPH);
			 
		     dp1 = new JLabel(":");
		     dp1.setSize(20,20);
		     dp1.setLocation(270, 10); 
			panel.add(dp1);
			
		     dp2 = new JLabel(":");
		     dp2.setSize(20,20);
		     dp2.setLocation(420, 10); 
		     panel.add(dp2);
		     dp3 = new JLabel(":");
		     dp3.setSize(20,20);
		     dp3.setLocation(270, 40); 
			 panel.add(dp3);
			 
		     dp4 = new JLabel(":");
		     dp4.setSize(20,20);
		     dp4.setLocation(420, 40); 
		     panel.add(dp4);
		     
		     sp1 = new JLabel("( mm : ss )");
		     sp1.setSize(100,20);
		     sp1.setLocation(500, 10); 
		     panel.add(sp1);
		     
		     sp2 = new JLabel("( mm : ss )");
		     sp2.setSize(100,20);
		     sp2.setLocation(500, 40); 
		     panel.add(sp2);
		     
		     arrTminM = new JTextField("01", 2);
		     arrTminM.setSize(25,20);
		     arrTminM.setLocation(230, 10);
			panel.add(arrTminM);
			
		     arrTminS = new JTextField("00", 2);
		     arrTminS.setSize(25,20);
		     arrTminS.setLocation(290, 10);
			panel.add(arrTminS);
			
		     arrTmaxM = new JTextField("03", 2);
		     arrTmaxM.setSize(25,20);
		     arrTmaxM.setLocation(380, 10);
			panel.add(arrTmaxM);
			
		     
		     arrTmaxS = new JTextField("00", 2);
		     arrTmaxS.setSize(25,20);
		     arrTmaxS.setLocation(440, 10);
			panel.add(arrTmaxS);
			
		     
		     
		     servTminM = new JTextField("06", 2);
		     servTminM.setSize(25,20);
		     servTminM.setLocation(230, 40);
			panel.add(servTminM);
		     
		     servTminS = new JTextField("00", 2);
		     servTminS.setSize(25,20);
		     servTminS.setLocation(290, 40);
			panel.add(servTminS);
			
		     servTmaxM = new JTextField("07", 2);
		     servTmaxM.setSize(25,20);
		     servTmaxM.setLocation(380, 40);
			panel.add(servTmaxM);
			
		     servTmaxS = new JTextField("00", 2);
		     servTmaxS.setSize(25,20);
		     servTmaxS.setLocation(440, 40);
			panel.add(servTmaxS);
			
			 String []content = new String[24];
		     for (int i = 0; i < 24; i++)
		            content[i] = i/10 + "" + i%10;
			cbSimIS = new JComboBox(content);
			cbSimIS.setSize(40,20);
			cbSimIS.setLocation(400, 70);
			panel.add(cbSimIS);
			
	        cbSimIF = new JComboBox();
	        cbSimIF.setSize(40,20);
	        cbSimIF.setLocation(520, 70);
			panel.add(cbSimIF);
			
	        areaQ1 = new TextArea(15, 10);
	        areaQ1.setSize(260,300);
	        areaQ1.setLocation(0,420);
			panel.add(areaQ1);
	        areaQ2 = new TextArea(15, 10);
	        areaQ2.setSize(260,300);
	        areaQ2.setLocation(300,420);
			panel.add(areaQ2);
	        areaQ3 = new TextArea(15, 10);
	        areaQ3.setSize(260,300);
	        areaQ3.setLocation(600,420);
			panel.add(areaQ3);
			
	        ckQ1 = new JCheckBox(" Queue 1", false);
	        ckQ1.setSize(120,20);
	        ckQ1.setLocation(20,320);
	        panel.add(ckQ1);
	        
	        ckQ2 = new JCheckBox(" Queue 2", false);
	        ckQ2.setSize(120,20);
	        ckQ2.setLocation(320,320);
	        panel.add(ckQ2);
	        
	        ckQ3 = new JCheckBox(" Queue 3", false);
	        ckQ3.setSize(120,20);
	        ckQ3.setLocation(620,320);
	        panel.add(ckQ3);
	        
	        avgWQ1 = new JLabel("Average Waiting Time: 00 : 00");
	        avgWQ1.setSize(180,30);
	        avgWQ1.setLocation(20,330);
	        panel.add(avgWQ1);
	        
	        avgWQ2 = new JLabel("Average Waiting Time: 00 : 00");
	        avgWQ2.setSize(180,30);
	        avgWQ2.setLocation(320,330);
	        panel.add(avgWQ2);
	        
	        avgWQ3 = new JLabel("Average Waiting Time: 00 : 00");
	        avgWQ3.setSize(180,30);
	        avgWQ3.setLocation(620,330);
	        panel.add(avgWQ3);
	        
	        avgSQ1 = new JLabel("Average Serving Time: 00 : 00");
	        avgSQ1.setSize(180,30);
	        avgSQ1.setLocation(20,345);
	        panel.add(avgSQ1);
	        
	        avgSQ2 = new JLabel("Average Serving Time: 00 : 00");
	        avgSQ2.setSize(180,30);
	        avgSQ2.setLocation(320,345);
	        panel.add(avgSQ2);
	        
	        avgSQ3 = new JLabel("Average Serving Time: 00 : 00");
	        avgSQ3.setSize(180,30);
	        avgSQ3.setLocation(620,345);
	        panel.add(avgSQ3);
	        
	        servQ1 = new JLabel("Total Service Time: 00 : 00");
	        servQ1.setSize(180,30);
	        servQ1.setLocation(20,360);
	        panel.add(servQ1);
	        servQ2 = new JLabel("Total Service Time: 00 : 00");
	        servQ2.setSize(180,30);
	        servQ2.setLocation(320,360);
	        panel.add(servQ2);
	        servQ3 = new JLabel("Total Service Time: 00 : 00");
	        servQ3.setSize(180,30);
	        servQ3.setLocation(620,360);
	        panel.add(servQ3);
	        
	        emptyQ1 = new JLabel("Empty Queue Time: 00 : 00");
	        emptyQ1.setSize(180,30);
	        emptyQ1.setLocation(20,375);
	        panel.add(emptyQ1);
	        
	        emptyQ2 = new JLabel("Empty queue time: 00 : 00");
	        emptyQ2.setSize(180,30);
	        emptyQ2.setLocation(320,375);
	        panel.add(emptyQ2);
	        
	        emptyQ3 = new JLabel("Empty Queue Time: 00 : 00");
	        emptyQ3.setSize(180,30);
	        emptyQ3.setLocation(620,375);
	        panel.add(emptyQ3);
	        
	        areaQ4 = new TextArea();
	        areaQ4.setSize(350,250);
	        areaQ4.setLocation(600,20);
	        panel.add(areaQ4);
	        areaQ4.setVisible(false);
		    
		     
		     
    
	        
    class ButtonListener implements ActionListener{
        @Override
        synchronized public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                btnStart.setEnabled(false);//disable the Start button
                start();//method below to process the input 
                mainThread.start();//start the thread
                simTime = setSimTime();
            }
        }
    }
    /**
     * Implements the listeners for the combo boxes
     */
    class ComboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cbSimIS ) {
                cbSimIF.removeAllItems();//remove previous items from the second combo box
                int index = cbSimIS.getSelectedIndex() + 1;
                String[] content1 = new String[25 - index];
                for (int i = index; i < 25; i++) {//populate the second combo box with values from index on
                    content1[i - index] = i/10 + "" + i%10;
                    cbSimIF.addItem(content1[i - index]);
                }
            }
        }
    }
    /**
     * Implements the listeners for the check boxes that will enable and 
     * disable the corresponding queue
     */
    class CheckListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ckQ1) {
                check[0] = !check[0];
                if (check[0] == false)//if check box is de-selected, redistribute the clients
                    redistribute(0);
            }
            else if (e.getSource() == ckQ2) {
                check[1] = !check[1];
                if (check[1] == false)//if check box is de-selected, redistribute the clients
                    redistribute(1);
            }
            else if (e.getSource() == ckQ3) {
                check[2] = !check[2];
                if (check[2] == false)//if check box is de-selected, redistribute the clients
                    redistribute(2);
            }           
            if (ready == true)//if start button has been pressed, re-check the status of check boxes
                afterCheck();
        }
    }
    
    btnStart.addActionListener(new ButtonListener());
    cbSimIS.addActionListener(new ComboListener());
    ckQ1.addActionListener(new CheckListener());
    ckQ2.addActionListener(new CheckListener());
    ckQ3.addActionListener(new CheckListener());
}

/**
 * This method has the purpose of redistributing the customers 
 * from a recently closed queue to the other available ones. 
 * We will do this by removing the customers from the queue and 
 * inserting them into the waiting list.
 * @param which integer - index of the queue whose clients need to be redistributed
 */
public void redistribute(int which) {
    Queue q = cozi.get(which);
    int size=q.getCustomers().size();
    for (int i = 0; i < size-1; i++) {
        wq.wPush(q.getCustomer(0));//re-place them in the waitingCustomerList to be re-processed
        q.pop();//pop them from the queue
    }
}
/**
 * mMthod which handles the enabling and disabling of the check
 * boxes which control the queues
 */
public void afterCheck() {
    if (check[0] == false)
        this.cozi.get(0).setEnabled(false);
    else {
        this.cozi.get(0).setEnabled(true);
    }
    if (check[1] == false)
        this.cozi.get(1).setEnabled(false);
    else {
        this.cozi.get(1).setEnabled(true);
    }
    if (check[2] == false)
        this.cozi.get(2).setEnabled(false);
    else {
        this.cozi.get(2).setEnabled(true);
    }
}

/**
 * Method that takes the information entered by the user and converts it into milliseconds.
 * @return the simulation time in milliseconds
 */
private int setSimTime() {
    hStart = cbSimIS.getSelectedIndex();
    hFinish = hStart + cbSimIF.getSelectedIndex() + 1;
    return (hFinish - hStart) * 20000; // 20000 is the real time of 1 hour of real simulation
}

/**
 * Method which processes the input provided by the user
 */
private void start() {
    //process the input from the textboxes
    arr1 = (int)makeMilliseconds(arrTminM.getText(), arrTminS.getText(), MINUTE);
    arr2 = (int)makeMilliseconds(arrTmaxM.getText(), arrTmaxS.getText(), MINUTE);
    serv1 = (int)makeMilliseconds(servTminM.getText(), servTminS.getText(), MINUTE);
    serv2 = (int)makeMilliseconds(servTmaxM.getText(), servTmaxS.getText(), MINUTE);
    cozi = new ArrayList<Queue>(4);
    for (int i = 0; i < 3; i++) {
        Queue q = new Queue();//create the instances of Queue class
        cozi.add(q);
        if (check[i] == true) 
            q.setEnabled(true); //set the status
        else
            q.setEnabled(false);
        q.start();//starts the thread
    }
    ready = true; //this means we have pushed the "Start" button
    wq = new WaitingQueue((long)serv1, (long)serv2, (long)arr1, (long)arr2, cozi);
    wq.start();//start the waiting queue thread
}
/**
 * Generates the string to be placed in text areas
 * @param q Queue
 * @return the String representation of the queue
 */

private String makeText(Queue q) {
    String res = "";
    res += q.toString();
    return res;
}
/**
 * method that implements the code that will be executed when an instance 
 * of GUI is created. In our case, this will be the task of updating the 
 * labels from the frame and check if the simulation is over in order to 
 * display the peak hour and the number of customers in queues at that time. 
 * In order to update the swing components we create a small timer which 
 * updates the current time label and handles the execution of the code, 
 * once it is updated.                           
 * @return 
 */
String str;
String str2;
@Override

public void run() {
    Action act = new AbstractAction() {//create a new action for the timer
    	
        @Override
        public void actionPerformed(ActionEvent e) {
            if (time < simTime) { //if we haven't passed over the simulation time
                lblTime.setText(makeTime(str));//set the current time
                this.gettiem(makeTime(str));
              
                
                areaQ4.setText(makeTime(str));
                int currentCustInQueues = 0;
                currentCustInQueues +=  cozi.get(0).getCustNr() + 
                        cozi.get(1).getCustNr() + cozi.get(2).getCustNr();//compute the number of current customers in queues
                if (currentCustInQueues > maxCustInQueues) {//update the max number if necessary
                    maxCustInQueues = currentCustInQueues;
                    pH = lblTime.getText();
                }
                //display each queue and it's current information
               // this.log(makeText(cozi.get(3)).toString());
                areaQ1.setText(makeText(cozi.get(0)));
                avgWQ1.setText(makeAvgTime(0, WAIT));
                avgSQ1.setText(makeAvgTime(0, SERV));
                if (cozi.get(0).isEmpty() == true)
                    eQ1 += 100;
                emptyQ1.setText("Empty Queue Time: " + makeTime(eQ1));
                if (cozi.get(0).getTotalServiceTime()<=0) servQ1.setText("Total Service Time: 00 : 00");
                else servQ1.setText("Total Service Time: " + makeTime(cozi.get(0).getTotalServiceTime()));
                areaQ2.setText(makeText(cozi.get(1)));
                avgWQ2.setText(makeAvgTime(1, WAIT));
                avgSQ2.setText(makeAvgTime(1, SERV));
                if (cozi.get(1).isEmpty() == true)
                    eQ2 += 100;
                emptyQ2.setText("Empty Queue Time: " + makeTime(eQ2));
                if (cozi.get(1).getTotalServiceTime()<=0) servQ1.setText("Total Service Time: 00 :00");
                else servQ2.setText("Total Service Time: " + makeTime(cozi.get(1).getTotalServiceTime()));
                areaQ3.setText(makeText(cozi.get(2)));
                avgWQ3.setText(makeAvgTime(2, WAIT));
                avgSQ3.setText(makeAvgTime(2, SERV));
                if (cozi.get(2).isEmpty() == true)
                    eQ3 += 100;
                emptyQ3.setText("Empty Queue Time: " + makeTime(eQ3));
                if (cozi.get(2).getTotalServiceTime()<=0) servQ1.setText("Total Service Time: 00 : 00");
                else servQ3.setText("Total Service Time: " + makeTime(cozi.get(2).getTotalServiceTime()));
                time += 100;
            }
            else {//when simulation is over
                lblPH.setText(pH + " Customers: " + maxCustInQueues);//return the peak hour (hours and minutes) and # of customers in queues
                btnStart.setEnabled(true);//re-enable the start button
            }
        }
/*
        void log(String s){
        	areaQ4.setText(" ");
        	areaQ4.append(s);
        }
        */

		private void gettiem(String str) {
			// TODO Auto-generated method stub
			
		}
    };
    Timer t = new Timer(100, act);//create a new instance of he Timer class
    t.start();//start the timer
}
public void gettiem(String str)
{
	this.str2=str;
}
public String rettime()
{
	return this.str2;
}
/**
 * Is a method which computes the virtual simulation time from milliseconds 
 * into a format of virtual minutes and seconds to be displayed in 
 * the corresponding labels. The corresponding type is given by the integer variable
 * @param qNr integer
 * @param tip integer
 * @return the string representation of the Average Waiting time
 */
private String makeAvgTime(int qNr, int type) {
    String s = "";
    long timp = 0, min = 0, sec = 0;
    int mz, mu, sz, su;
    if (type == WAIT) {//depending on the type
        timp = cozi.get(qNr).getAvgWaitTime();
        s += "Average Waiting Time: " ;
    }
    else if (type == SERV) {
        timp = cozi.get(qNr).getAvgServTime();
        s += "Average Serving Time: " ;
    }
    min = timp / 333; //minutes
    mz = (int)(min / 10);
    mu = (int)(min % 10);
    sec = (long)((timp % 333) / 5.55);//seconds
    sz = (int)(sec / 10);
    su = (int)(sec % 10);
    s += mz + "" + mu + " : " + sz + "" + su;
    return s;
}

/**
 * Converts to simulation time the real time
 * @return the string representation of this conversion
 */
 String makeTime(String s) {
    int hrs = 0, mins = 0;
    hrs = hStart + (time / 20000);
    mins = (time % 20000) / 333;
    s=hrs / 10 + "" + hrs % 10 + " : " + mins / 10 + "" + mins % 10;
    return s;
}

/**
 * Used for the empty queue time
 * @param timp long
 * @return the string representation of the empty queue time
 */
private String makeTime(long timp) {
    String s = "";
    int hrs = 0, mins = 0;
    hrs = (int)(timp / 20000);
    mins = (int)((timp % 20000) / 333);
    s = hrs / 10 + "" + hrs % 10 + " : " + mins / 10 + "" + mins %10;
    return s;
}
/**
 * Method that returns the reverse process
 * @param s1 String
 * @param s2 String
 * @param tip integer
 * @return the milliseconds resulted after converting the two strings and the type passed as parameter
 */
private long makeMilliseconds(String s1, String s2, int type) {
    long total = 0;
    if (type == 1)
        total += Integer.parseInt(s1) * 20000 + Integer.parseInt(s2) * 333.33;//minutes
    else
        total += Integer.parseInt(s1) * 333.33 + Integer.parseInt(s2) * 5.55;//seconds
    return total;
}

}

