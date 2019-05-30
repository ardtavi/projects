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
package ro.utcn.sd;

import ro.utcn.sd.business.ShowParkinglot;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.impl.hibernate.util.HibernateUtil;
import ro.utcn.sd.entities.User;
import ro.utcn.sd.entities.Car;
import ro.utcn.sd.entities.Parkinglot;
import ro.utcn.sd.entities.Ticket;
import ro.utcn.sd.output.ParkinglotDTO;
import ro.utcn.sd.output.UserDetailsDTO;

import java.io.IOException;

import static ro.utcn.sd.entities.builders.TicketBuilder.createTicketBuilder;

public class Main {

    public static final int CART_ID = 1;
    public static final int PARKINGLOT_ID = 1;

    /**
     * This is just a small demo.
     * <p>
     * Please also see  ShowUserDetailsTest. (Note ShowUserDetailsTest is not seen in the src folder)
     */
    public static void main(String[] args) throws IOException {
        DaoFactory daoFactory = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE);
        insertInitialData(daoFactory);
        
        ShowParkinglot transactionScript = new ShowParkinglot(daoFactory, PARKINGLOT_ID);

//        ParkinglotDTO execute = transactionScript.execute();
//
//        if (execute != null) {
//            System.out.println("Address : " + execute.getAddress());
//           
//            System.out.println("Total Requests:" + execute.getTotal());
//           
//           // System.out.println("Tickets:");
//            execute.getTickets().forEach(System.out::println);
//        }
//
//        // wait for key pressed
//        System.in.read();
//        HibernateUtil.getSessionFactory().close();
    }

    private static void insertInitialData(DaoFactory daoFactory) {
        Ticket rope = createTicketBuilder()
                .name("Request_test")
                .price(1.0)
                .build();
        Car car= new Car();
        Ticket ticket= new Ticket();
        car.setName("Bembeu");
        //car.getTicket().add(rope);
        User user = new User();
        user.setId(CART_ID);
        user.setName("Test user");
        user.setIsadmin("Yes");
        user.setUserName("testusername");
        user.setPassword("testpassword");
        rope.setUser(user);
      ;
        //ticket.setCar(car);
//      ticket.setId(CART_ID);
//      ticket.setName("Request_1");
//      ticket.setPrice(1.0);
      
      	Parkinglot parkinglot = new Parkinglot();
      	parkinglot.setId(PARKINGLOT_ID);
      	parkinglot.setParkingaddress("Marasti1");
      	parkinglot.add(rope);
        daoFactory.getParkinglotsDao().insert(parkinglot);
    }
}
