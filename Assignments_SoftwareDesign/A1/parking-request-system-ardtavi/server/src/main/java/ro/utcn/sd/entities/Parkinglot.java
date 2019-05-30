package ro.utcn.sd.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Parkinglot")
public class Parkinglot {

    @Id
    // This is a small trick to make Hibernate 5 generate ID properly for MySql. Normally just @GeneratedValue is enough
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "parkinglot_id")
    private long id;

    @Column(name = "address")
    private String address;

   

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "parkinglot_ticket",
            joinColumns = @JoinColumn(name = "parkinglot_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Ticket> tickets = new HashSet<>();	

 

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   
    public String getParkingaddress() {
        return address;
    }

    public void setParkingaddress(String adress) {
        this.address = adress;
    }
    

	public boolean add(Ticket ticket) {
        ticket.getParkinglots().add(this);
        return tickets.add(ticket);
    }

    public boolean remove(Ticket ticket) {
        ticket.getParkinglots().remove(this);
        return tickets.remove(ticket);
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }
 
}
