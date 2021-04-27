/*
 * This class represents the abstraction of the real-world concept of a customer
 * queueing for a service. The attributes of this class are the unique ID 
 * identifying a customer and the servTime 
 */
package queues;

public class Customer {
	
    private int ID;
    private long servTime;
    
    GUI g = new GUI();
    
    /*
      Constructor of the class sets the id and the serTime for each customer
      id integer
      time long
     */
    public Customer(int id, long time) {
        this.ID = id;
        this.servTime = time;
    }
    
    /*
      Maintenance method
      the current ID attribute of a customer
     */
    public int getId() {
        return this.ID;
    }
    
    /*
      Maintenance method
      the current servTime of a customer
     */
    public long getServiceTime() {
        return this.servTime;
    }
    /*
      
     the string that represents the customer in the GUI class
     */
    String str= g.rettime();
    @Override
    
    public String toString() {
        return "Customer#" + this.ID+" ";
        //+this.str;
    }
 
}
