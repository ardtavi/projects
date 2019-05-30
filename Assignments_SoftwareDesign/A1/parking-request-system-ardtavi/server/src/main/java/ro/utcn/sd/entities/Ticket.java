package ro.utcn.sd.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

  

   @ManyToMany(mappedBy = "tickets")
   private Set<Parkinglot> parkinglot = new HashSet<>();
   
//   @ManyToOne(fetch=FetchType.LAZY)
   
//   @JoinColumn(name="user_id")
   private User user;
    
   
   
	public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Ticket()
    {
    	
    }
  

////
	public Set<Parkinglot> getParkinglots() {
        return parkinglot;
    }

}
