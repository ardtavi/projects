/**
 *  Class that extends the Queue class, to inherit it's methods and to implement 
 * some extra methods needed for the correct processing of the customers, will 
 * implement another queue, in which the customers are placed until the 
 * processing of the current queues is done, such as computing the best queue 
 * for each customer
 */
package queues;

import java.util.ArrayList;
import java.util.Random;


public class WaitingQueue extends Queue {
    private long minimumServiceTime;
    private long maximumServiceTime;
    private long minimumArrivalTime;
    private long maximumArrivalTime;
    private ArrayList<Customer> customersWaitingList;
    private ArrayList<Queue> queues;
    private int currentId;
    
    /**
     *  The constructor of the class takes as parameters the time between 
     * customers, the service time interval to generate the customers and the 
     * arrayList of queues
     * @param minST long 
     * @param maxST long
     * @param minAT long
     * @param maxAT long
     * @param qList ArrayList<Queue>
     */
    public WaitingQueue(long minST, long maxST, long minAT, long maxAT, ArrayList<Queue> qList) {
        super();//call the constructor for the super class
        this.maximumArrivalTime = maxAT;
        this.minimumArrivalTime = minAT;
        this.maximumServiceTime = maxST;
        this.minimumServiceTime = minST;
        this.queues = qList;
        this.currentId = 0;
        this.customersWaitingList = this.getCustomers();
    }
    /**
     * Method used to compute the best available queue based on the minimum 
     * service time and waiting time in the queue
     * @return the index of this queue.
     */
    private int bestQueue() {
        int best = -1; //this means that no queues are open
        long min = 999999999;
        for (int i = 0; i < this.queues.size(); i++) {//pass through the arrayList of queues
            if (this.queues.get(i).getEnabled() == true) {
                if (min > this.queues.get(i).getTotalTime()) {//compute the min time
                    min = this.queues.get(i).getTotalTime();
                    best = i;//save the index
                }
            }
        }
        return best;
    }
    /**
     * Method that will create an instance of the Customer class, based on the 
     * id passed as a parameter previously computed and the time interval being 
     * processed as a random value between the max and min values
     * @param id integer
     * @return an instance of the class Customer having set the attributes
     */
    private Customer generateCustomer(int id) {
        Random randGen = new Random();
        long time;
        time = randGen.nextInt((int)this.maximumServiceTime - (int)this.minimumServiceTime) + this.minimumServiceTime;
        Customer c = new Customer(id, time);//create a new instance of the Customer class
        return c;
    }
    
    /**
     * Inserts the instance of the customer class passed as a parameter in 
     * the waiting list of customers
     * @param c Customer
     */
    public void wPush(Customer c) {
        this.customersWaitingList.add(c);
    }
    
    /**
     * Removes the customer from  the waiting list.
     */
    private void wPop() {
        this.customersWaitingList.remove(0);
    }

    /**
     * Implements the code that will be executed once an instance of this class 
     * is created and it is started. In our case, it will compute the best 
     * queue for each step, compute the time between customers as a random 
     * value in the given bounds, pushes the customer in the selected queue, 
     * and pops it from the waiting list of customers
     */
    @Override
    public void run() {
        Random r = new Random();
        long wait;
        int bQu; // the queue with the smallest waiting time
        Customer c;
        while (true) {
            wait = r.nextInt((int)this.maximumArrivalTime - (int)this.minimumArrivalTime) + this.minimumArrivalTime;
            c = generateCustomer(currentId);
            currentId++;//update id
            this.wPush(c);//insert the customer into the waiting queue
            for (int i = 0; i < this.customersWaitingList.size(); i++) {
                bQu = bestQueue();
               // queues.get(3).push(this.customersWaitingList.get(0));
                if (!this.customersWaitingList.isEmpty() && bQu != -1) { //if queue is not empty
                    queues.get(bQu).push(this.customersWaitingList.get(0)); //insert the customer in the suitable queue
                    
                    this.wPop(); //pop the customer from the waiting queue
                }
            }
            try {
                this.sleep(wait); //create a real time delay
            }
            catch(Exception e) {
                System.out.println("Eroare in waiting: " + e.getStackTrace());
            }
        }
    }
}
