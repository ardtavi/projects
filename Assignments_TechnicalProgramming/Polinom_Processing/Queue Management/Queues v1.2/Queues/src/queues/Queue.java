/**
 * This class represents the abstraction of the real-world concept of a queue 
 * consisted of customers. In order to bring the simulation even closer to the 
 * reality, this class will extend the Thread class, meaning that we will create
 * a separate thread for each queue.
 */

package queues;

import java.util.ArrayList;
import java.util.Date;


public class Queue extends Thread {

    private ArrayList<Customer> customersList;   
    private volatile boolean empty;
    private boolean nT = false, oT = false; //used to detect a new term to be inserted
    private long avgServTime;
    private long avgWaitTime;
    private long nrServedCust;
    private long totalServTime, totalWaitTime;
    private boolean isEnabled = false;
    
    public Queue() {
        this.empty = true;
        this.customersList = new ArrayList<Customer>(20);
        this.nrServedCust = 0;
        this.avgServTime = 0;
        this.avgWaitTime = 0;
        this.totalServTime = 0;
        this.totalWaitTime = 0;
        this.isEnabled = false;
    }
    /**
     * Maintenance method
     * Sets the queue to the specified state
     * @param state boolean 
     */
    public void setEnabled(boolean state) {
        this.isEnabled = state;
    }
    
    /**
     * Maintenance method
     * @return the current state of the queue
     */
    public boolean getEnabled() {
        return this.isEnabled;
    }
    
    /**
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (customersList.isEmpty())
            this.empty = true;
        else
            this.empty = false;
        return this.empty;
    }
    
    /**
     * 
     * @return the current total service time for a given queue
     */
    public long getTotalServiceTime() {
        return (this.totalServTime-400);//for synchronization with the timer in the GUI class
    }
    
    /**
     * Gets the customers from the first position from the queue out
     */
    public void pop() {
        this.customersList.remove(0);
    }
    
    /**
     * Inserts a new customer in a queue
     * @param newCust Customer
     */
    public void push(Customer newCust) {
        this.customersList.add(newCust);
    }
    
    /**
     * 
     * @return the total time of a given queue
     */
    public long getTotalTime() {
        long time = 0;
        for (int i = 0; i < this.customersList.size(); i++)//pass through the list
            time += this.customersList.get(i).getServiceTime();//add the time from each customer
        return time;
    }
    
    /**
     * Maintenance method
     * @return the current avgServTime for a queue, used in GUI
     */
    public long getAvgServTime() {
        return this.avgServTime;
    }
    
    /**
     * Maintenance method
     * @return the current avgWaitTime for a queue, used in GUI
     */
    public long getAvgWaitTime() {
        return this.avgWaitTime;
    }
    
    /**
     * Obtains a string representation of a queue
     * @return this string representation
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.customersList.size(); i++)
            s += this.customersList.get(i).toString() + "\n";
        return s;
    }
    
    /**
     * Overridden method form the Thread class, implements the code to be 
     * executed when an instance of this class is started. In our case, it will 
     * compute the times needed to be displayed in the GUI class in real 
     * simulation time.
     */
    @Override
    public void run() {
        long start = 0;
        Date timp = new Date();
        start = timp.getTime() + 10000;
        while (start > timp.getTime()) { //replace the while(true) statement with this
            if (this.isEmpty() != true) { //if it is not empty
                try {
                    nrServedCust++;
                    //compute the time attributes for the current queue
                    totalServTime += this.customersList.get(0).getServiceTime();
                    avgServTime = totalServTime / nrServedCust;
                    totalWaitTime += this.getTotalTime();
                    avgWaitTime = totalWaitTime / (this.customersList.size() + this.nrServedCust);
                    Thread.sleep(this.customersList.get(0).getServiceTime());//use the computed time to sleep the thread
                    
                    this.pop();//pop the customer out
                    this.oT = this.nT;//update the terms
                    //this.nT = !this.nT;
                }
                catch(Exception e) {
                }
            }
            
        }
    }
    /**
     * 
     * @return the list of customer objects currently in queue
     */
    public ArrayList<Customer> getCustomers() {
        return this.customersList;
    }
    
    /**
     * 
     * @return the number of customers currently in queue
     */
    public int getCustNr() {
        return this.customersList.size();
    }
    
    /**
     * 
     * @param index integer
     * @return the customer at a given position, passed as a parameter
     */
    public Customer getCustomer(int index) {
        return this.customersList.get(index);
    }

}
