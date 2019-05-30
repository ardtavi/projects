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
package ro.utcn.sd.business;

import ro.utcn.sd.dao.ParkinglotDao;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.TicketsDao;
import ro.utcn.sd.entities.Parkinglot;
import ro.utcn.sd.entities.Ticket;
import ro.utcn.sd.output.ParkinglotDTO;
import ro.utcn.sd.output.TicketDTO;

import java.util.Set;

public class ShowParkinglot {

    private final DaoFactory daoFactory;
    private       long       parkinglotId;

    public ShowParkinglot(DaoFactory daoFactory, long parkinglotId) {
        this.daoFactory = daoFactory;
        this.parkinglotId = parkinglotId;
    }

//    public ParkinglotDTO execute() {
//        ParkinglotDao parkinglotDao = daoFactory.getParkinglotsDao();
//        Parkinglot parkinglot = parkinglotDao.find(parkinglotId);
//        TicketsDao ticketsDao = daoFactory.getTicketsDao();
//        Set<Ticket> tickets = ticketsDao.findByParkinglotId(parkinglotId);
//
//        double total = 0;
//
//        String address = parkinglot.getParkingaddress();
//       
//        ParkinglotDTO result = new ParkinglotDTO(address);
//        for (Ticket ticket : tickets) {
//            //result.getTickets().add(createTicketDTO(ticket));
//            //total += ticket.getPrice();
//        	total+=1;
//        }
//        result.setTotal(total);
//        return result;
//    }

    private TicketDTO createTicketDTO(Ticket ticket) {
        return new TicketDTO(ticket.getName(), ticket.getPrice());
    }
}
